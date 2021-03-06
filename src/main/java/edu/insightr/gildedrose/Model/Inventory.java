package edu.insightr.gildedrose.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;


import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Inventory implements Initializable {


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private ObservableList<Item> items;
    private List<Item> soldItems;
    private List<Item> boughtItems;

    public ObservableList<Item> getItems() {
        return items;
    }

    public List<Item> getSoldItems() {
        return soldItems;
    }

    public void setItems(ObservableList<Item> it) {
        this.items = it;
    }

    public Inventory(ObservableList<Item> items) {
        this.items = items;
        soldItems = new ArrayList<>();
        boughtItems = new ArrayList<>();
    }

    public Inventory() {
        items = FXCollections.observableArrayList();
        soldItems = new ArrayList<>();
        boughtItems = new ArrayList<>();
    }

    public Inventory(String fileName) {
        items = ReaderFileJson(fileName);
        soldItems = new ArrayList<>();
        boughtItems = new ArrayList<>();
    }

    public void ChargeItems(String fileName){
        ObservableList<Item> toAdd = ReaderFileJson(fileName);
        for(Item i : toAdd)
        {
            if(!items.contains(i)) items.add(i);
        }
    }

    public void printInventory() {
        System.out.println("***************");
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("***************");
        System.out.println("\n");
    }

    public void updateInventory() {
        DebugVisitor visitor = new DebugVisitor();
        for (Item item : items) {
            item.accept(visitor);
        }
    }

    public void updateQuality() {
        for (Item item : items) {
            item.updateQuality();
        }
    }

    public int[] countItem() {
        int[] count = new int[6];
        int numAgedBrie = 0;
        int numSulfuras = 0;
        int numBackStage = 0;
        int numConjured_Mana_Cake = 0;
        int numDexterity_Vest = 0;
        int numElixir = 0;

        for (Item i : items) {
            if (i.getClass().getSimpleName().equals("Aged_Brie")) {
                numAgedBrie++;
                //System.out.println(i.getType());
            }
            if (i.getClass().getSimpleName().equals("Sulfuras")) {
                numSulfuras++;
            }
            if (i.getClass().getSimpleName().equals("Backstage_Passes")) {
                numBackStage++;
            }
            if (i.getClass().getSimpleName().equals("Conjured_Mana_Cake")) {
                numConjured_Mana_Cake++;
            }
            if (i.getClass().getSimpleName().equals("Dexterity_Vest")) {
                numDexterity_Vest++;
            }
            if (i.getClass().getSimpleName().equals("Elixir_of_the_Mongoose")) {
                numElixir++;
            }
        }
        count[0] = numAgedBrie;
        count[1] = numSulfuras;
        count[2] = numBackStage;
        count[3] = numConjured_Mana_Cake;
        count[4] = numDexterity_Vest;
        count[5] = numElixir;
        return count;
    }

    public Map<String, Integer> countSellIn(){
        Map<String, Integer> result = new HashMap<>();
        for(Item i : items)
        {
            if(result.containsKey(String.valueOf(i.getSellIn())))
            {
                result.put(String.valueOf(i.getSellIn()), result.get(String.valueOf(i.getSellIn()))+1);
            }
            else
            {
                result.put(String.valueOf(i.getSellIn()), 1);
            }
        }
        return result;

    }

    public ObservableList<Item> ReaderFileJson(String fileName) {
        JSONParser jsonParser = new JSONParser();
        ObservableList<Item> itemStorage = FXCollections.observableArrayList();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            FileReader reader = new FileReader(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
            Object obj = jsonParser.parse(reader);
            JSONArray productList = (JSONArray) obj;
            for (Object product : productList) {
                if (product instanceof JSONObject) {
                    Item nouveau = parseProductObject((JSONObject) product);
                    if (nouveau != null && !itemStorage.contains(nouveau))
                        itemStorage.add(nouveau);
                }
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return itemStorage;
    }

    private Item parseProductObject(JSONObject product) {
        Item item = null;
        JSONObject productObject = (JSONObject) product.get("product");
        String name = (String) productObject.get("name");
        int quality = Integer.parseInt(productObject.get("quality").toString());
        int sellIn = Integer.parseInt(productObject.get("sellIn").toString());
        String creation_date = productObject.get("creation_date").toString();
        switch ((String) productObject.get("type")) {
            case "Aged_Brie":
                item = new Aged_Brie(name, sellIn, quality, creation_date);
                break;
            case "Backstage_Passes":
                item = new Backstage_Passes(name, sellIn, quality, creation_date);
                break;
            case "Conjured_Mana_Cake":
                item = new Conjured_Mana_Cake(name, sellIn, quality, creation_date);
                break;
            case "Dexterity_Vest":
                item = new Dexterity_Vest(name, sellIn, quality, creation_date);
                break;
            case "Elixir_of_the_Mongoose":
                item = new Elixir_of_the_Mongoose(name, sellIn, quality, creation_date);
                break;
            case "Sulfuras":
                item = new Sulfuras(name, sellIn, quality, creation_date);
                break;
            default:
                System.out.println("Le type de l'item : " + name + " est introuvable: l'item n'a pa été ajouté!");
        }
        return item;
    }

    public  Map<String, Integer> itemCountPerDate() {
        Map<String, Integer> result = new HashMap<>();
        for(Item i : items)
        {
            if(result.containsKey(i.getCreation_date()))
            {
                result.put(i.getCreation_date(), result.get(i.getCreation_date())+1);
            }
            else
            {
                result.put(i.getCreation_date(), 1);
            }
        }
        return result;

    }

    public void SellItem(Item toDelete){
        soldItems.add(toDelete);
        items.remove(toDelete);
    }

    public List<Item> getBoughtItems() {
        return boughtItems;
    }

    public void setBoughtItems(List<Item> boughtItems) {
        this.boughtItems = boughtItems;
    }
}


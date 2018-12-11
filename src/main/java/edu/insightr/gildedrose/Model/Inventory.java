package edu.insightr.gildedrose.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Inventory implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private ObservableList<Item> items;

    public ObservableList<Item> getItems() {
        return items;
    }

    public void setItems(ObservableList<Item> it){
        this.items = it;
    }

    public Inventory(ObservableList<Item>  items) {
        super();
        this.items = items;
    }

    public Inventory() {
        super();
        items =  FXCollections.observableArrayList(
                new Dexterity_Vest(),
                new Aged_Brie(),
                new Elixir_of_the_Mongoose(),
                new Sulfuras(),
                new Backstage_Passes(),
                new Conjured_Mana_Cake())
        ;
    }

    public Inventory(String fileName){
        items = ReaderFileJson(fileName);
    }


    public void printInventory() {
        System.out.println("***************");
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("***************");
        System.out.println("\n");
    }

    public void updateInventory()
    {
        DebugVisitor visitor = new DebugVisitor();
        for(Item item : items)
        {
            item.accept(visitor);
        }
    }

    public void updateQuality() {
        for (Item item : items){
            item.updateQuality();
        }
    }

    public int[] countItem(){
        int[] count = new int[5];
        int numAgedBrie = 0;
        int numSulfuras =0;
        int numBackStage =0;
        int numConjured_Mana_Cake =0;
        int numDexterity_Vest =0;


        int size = items.size();

        for (Item i : items){
            if (i.getClass().getSimpleName().equals("Aged_Brie")) {
                numAgedBrie++;
                //System.out.println(i.getType());
            }
            if (i.getClass().getSimpleName().equals("Sulfuras")){
                numSulfuras++;
            }
            if (i.getClass().getSimpleName().equals("Backstage_Passes")){
                numBackStage++;
            }
            if (i.getClass().getSimpleName().equals("Conjured_Mana_Cake")){
                numConjured_Mana_Cake++;
            }
            if (i.getClass().getSimpleName().equals("Dexterity_Vest")){
                numDexterity_Vest++;
            }
        }
        //System.out.println(numAgedBrie);
        count[0]= numAgedBrie;
        count[1]= numSulfuras;
        count[2]= numBackStage;
        count[3]= numConjured_Mana_Cake;
        count[4]= numDexterity_Vest;
        //count.set(numSulfuras,"Sulfuras");
        return count;
    }

    public ObservableList<Item> ReaderFileJson(String fileName) {
        JSONParser jsonParser = new JSONParser();
        ObservableList<Item> itemStorage = FXCollections.observableArrayList();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            FileReader reader = new FileReader(classLoader.getResource(fileName).getFile());
            Object obj = jsonParser.parse(reader);
            JSONArray productList = (JSONArray) obj;
            for (Object product : productList) {
                if (product instanceof JSONObject) {
                    Item nouveau = parseProductObject((JSONObject) product);
                    if (nouveau != null) itemStorage.add(nouveau);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemStorage;
    }
    private Item parseProductObject(JSONObject product)
    {
        Item item = null;
        JSONObject productObject = (JSONObject) product.get("product");
        String name = (String) productObject.get("name");
        int quality = Integer.parseInt(productObject.get("quality").toString());
        int sellIn = Integer.parseInt(productObject.get("sellIn").toString());
        switch((String) productObject.get("type")){
            case "Aged_Brie":
                item = new Aged_Brie(name, sellIn, quality);
                break;
            case "Backstage_Passes":
                item = new Backstage_Passes(name, sellIn, quality);
                break;
            case "Conjured_Mana_Cake" :
                item = new Conjured_Mana_Cake(name, sellIn, quality);
                break;
            case "Dexterity_Vest" :
                item = new Dexterity_Vest(name, sellIn,quality);
                break;
            case "Elixir_of_the_Mongoose" :
                item = new Elixir_of_the_Mongoose(name, sellIn, quality);
                break;
            case "Sulfuras" :
                item = new Sulfuras(name, sellIn, quality);
                break;
            default:
                System.out.println("le type de l'item : "+ name + " est introuvable: l'item n'a pa été ajouté");
        }
        return item;
    }

    public static void main(String[] args) {
        Inventory inv = new Inventory("gildedRose.json");
        /*for (Item i : inv.getItems()){
            System.out.println(i);
        }*/

        for (int i : inv.countItem()){
            System.out.println(i);
            //if(i != "Aged_Brie"){ pos++; break;}

        }


    }
}
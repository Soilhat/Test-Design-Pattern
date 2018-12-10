package edu.insightr.gildedrose.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
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
    public ObservableList<Item> ReaderFileJson(String fileName) {
        JSONParser jsonParser = new JSONParser();
        ObservableList<Item> itemStorage = FXCollections.observableArrayList();
        try {
            FileReader reader = new FileReader(fileName);
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
}
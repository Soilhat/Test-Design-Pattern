package edu.insightr.gildedrose;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;


import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Inventory implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    ListView<String> listS;

    private ObservableList<Item> items;

    public ObservableList<Item> getItems() {
        return items;
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
    public static void ReadFileJson(){
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader("gildedRosebis.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray productList = (JSONArray) obj;
            System.out.println(productList);

            ////Iterate over employee array
            productList.forEach( emp -> parseProductObject( (JSONObject) emp ) );
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

    }
    private static void parseProductObject(JSONObject product)
    {
        JSONObject productObject = (JSONObject) product.get("product");
        String name = (String) productObject.get("name");
        System.out.println(name);
        int quality = Integer.parseInt(productObject.get("quality").toString());
        System.out.println(quality);
        int sellIn = Integer.parseInt(productObject.get("sellIn").toString());
        System.out.println(sellIn);
    }




    public static void main(String[] args) throws ParseException {
        Inventory inventory = new Inventory();
        /*for (int i = 0; i < 10; i++) {
            inventory.updateQuality();
            System.out.println(i);
            inventory.printInventory();
        }*/
        ReadFileJson();
        //inventory.updateInventory();
    }
}

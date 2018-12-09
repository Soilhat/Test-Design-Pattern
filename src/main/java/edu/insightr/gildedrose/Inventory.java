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
    public static void ReaderFileJson()  throws ParseException {
        JSONParser parser = new JSONParser();
        try {

            Object obj = parser.parse(new FileReader("C:\\Users\\nancy\\IdeaProjects\\Test-Design-Pattern\\gildedRosebis.json"));
            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject);

            String name = (String) jsonObject.get("name");

           JSONArray caracteristic = (JSONArray) jsonObject.get("Items");

           System.out.println(name);
           System.out.println("\ncaracteristic:");
            Iterator<Object> iterator = caracteristic.iterator();
            while (iterator.hasNext()) {
                //System.out.println(iterator.next());
                Iterator<Object>  item = ((JSONArray)iterator.next()).iterator();
                while(item.hasNext())
                {
                    System.out.println(item.next());
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ParseException {
        Inventory inventory = new Inventory();
        /*for (int i = 0; i < 10; i++) {
            inventory.updateQuality();
            System.out.println(i);
            inventory.printInventory();
        }*/
        ReaderFileJson();
        //inventory.updateInventory();
    }
}

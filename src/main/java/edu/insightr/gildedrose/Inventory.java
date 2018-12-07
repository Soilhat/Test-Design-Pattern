package edu.insightr.gildedrose;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Inventory {

    private Item[] items;

    public Item[] getItems() {
        return items;
    }

    public Inventory(Item[] items) {
        super();
        this.items = items;
    }

    public Inventory() {
        super();
        items = new Item[]{
                new Dexterity_Vest(),
                new Aged_Brie(),
                new Elixir_of_the_Mongoose(),
                new Sulfuras(),
                new Backstage_Passes(),
                new Conjured_Mana_Cake()
        };
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

    public static void  main(String[] args)
    {
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader("gildedRosebis.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray productList = (JSONArray) obj;
            System.out.println(productList);
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
}





package edu.insightr.gildedrose;

import org.json.simple.parser.JSONParser;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;


import java.io.*;


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

    public void updateInventory() {
        DebugVisitor visitor = new DebugVisitor();
        for (Item item : items) {
            item.accept(visitor);
        }
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (items[i].getName() != "Aged Brie"
                    && items[i].getName() != "Backstage passes to a TAFKAL80ETC concert") {
                if (items[i].getQuality() > 0) {
                    if (items[i].getName() != "Sulfuras, Hand of Ragnaros") {
                        items[i].setQuality(items[i].getQuality() - 1);
                    }
                }
            } else {
                if (items[i].getQuality() < 50) {
                    items[i].setQuality(items[i].getQuality() + 1);

                    if (items[i].getName() == "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].getSellIn() < 11) {
                            if (items[i].getQuality() < 50) {
                                items[i].setQuality(items[i].getQuality() + 1);
                            }
                        }

                        if (items[i].getSellIn() < 6) {
                            if (items[i].getQuality() < 50) {
                                items[i].setQuality(items[i].getQuality() + 1);
                            }
                        }
                    }
                }
            }

            if (items[i].getName() != "Sulfuras, Hand of Ragnaros") {
                items[i].setSellIn(items[i].getSellIn() - 1);
            }

            if (items[i].getSellIn() < 0) {
                if (items[i].getName() != "Aged Brie") {
                    if (items[i].getName() != "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].getQuality() > 0) {
                            if (items[i].getName() != "Sulfuras, Hand of Ragnaros") {
                                items[i].setQuality(items[i].getQuality() - 1);
                            }
                        }
                    } else {
                        items[i].setQuality(items[i].getQuality() - items[i].getQuality());
                    }
                } else {
                    if (items[i].getQuality() < 50) {
                        items[i].setQuality(items[i].getQuality() + 1);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        Inventory inventory = new Inventory();
        for (int i = 0; i < 10; i++) {
            inventory.updateQuality();
            inventory.printInventory();
        }
        inventory.updateInventory();
    }

    public static void CreateJsonFile() {
        JSONObject obj = new JSONObject();
        obj.put("name", "Aged Brie");
        obj.put("selIn", new Integer(7));
        obj.put("quality", new Integer(23));

        obj.put("name", "Sulfuras");
        obj.put("selIn", new Integer(15));
        obj.put("quality", new Integer(26));

        obj.put("name", "Pack Stage");
        obj.put("selIn", new Integer(5));
        obj.put("quality", new Integer(25));

        obj.put("name", "Conjured Mana Cake");
        obj.put("selIn", new Integer(5));
        obj.put("quality", new Integer(7));

        obj.put("name", "Elixi of the mongoose");
        obj.put("selIn", new Integer(0));
        obj.put("quality", new Integer(80));

        try (FileWriter file = new FileWriter("f:\\test.json")) {

            file.write(obj.toString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(obj);

    }

    public static void ReaderFileJson()  throws ParseException {

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("C:\\Users\\tsile\\Documents\\A4\\Design Pattern"));

            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject);

            String name = (String) jsonObject.get("name");
            System.out.println(name);

            int selIn = (int) jsonObject.get("selIn");
            System.out.println(selIn);

            int quality = (int) jsonObject.get("quality");
            System.out.println(quality);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

}





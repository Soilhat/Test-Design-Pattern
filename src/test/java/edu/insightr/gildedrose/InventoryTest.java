package edu.insightr.gildedrose;

import javafx.collections.ObservableList;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryTest {
    private Inventory invent;
    private int[] qualities;
    private int[] sellIn;

    @Before
    public void SetUp()
    {
        invent = new Inventory();
        int i = 0;
        qualities = new int[invent.getItems().size()];
        sellIn = new int[invent.getItems().size()];
        for(Item item : invent.getItems())
        {
            qualities[i] = item.getQuality();
            sellIn[i] = item.getSellIn();
            i++;
        }
    }

    @After
    public void TearDown(){

    }

    @Test
    public void sellPassed(){
        invent.updateQuality();
        ObservableList<Item> items = invent.getItems();
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getClass() != Sulfuras.class && sellIn[i] ==0 && qualities[i] > 2) {
                assertEquals(qualities[i]-2, items.get(0).getQuality());
            }
        }
    }

    @Test
    public void positiveQuality(){
        invent.updateQuality();
        ObservableList<Item> items = invent.getItems();
        for(int i=0; i< items.size(); i++){
            if(qualities[i]==0) {
                assertTrue(items.get(i).getQuality()>=0);
            }
        }
    }

    @Test
    public void agedBrieTest(){
        invent.updateQuality();
        ObservableList<Item> items = invent.getItems();
        for(int i=0; i< items.size(); i++){
            if(items.get(i).getName().equals("Aged Brie")) {
                assertEquals(qualities[i]+1, items.get(i).getQuality());
            }
        }
    }

    @Test
    public void Lower50Test(){
        invent.updateQuality();
        ObservableList<Item> items = invent.getItems();
        for(int i=0; i< items.size(); i++){
            if(!items.get(i).getName().contains("Sulfuras") && qualities[i] >= 50) {
                assertEquals(50, items.get(i).getQuality());
            }
        }
    }

    @Test
    public void sulfurasTest(){
        invent.updateQuality();
        ObservableList<Item> items = invent.getItems();
        for(int i=0; i< items.size(); i++){
            if(items.get(i).getName().contains("Sulfuras")) {
                assertEquals(qualities[i], items.get(i).getQuality());
                assertEquals(0, items.get(i).getSellIn());
            }
        }
    }

    @Test
    public void backstageTest(){
        invent.updateQuality();
        ObservableList<Item> items = invent.getItems();
        for(int i=0; i< items.size(); i++){
            if(items.get(i).getName().contains("Backstage passes")) {
                if(sellIn[i] <=10 && sellIn[i] > 5)
                    assertEquals(qualities[i]+2, items.get(i).getQuality());
                if(sellIn[i] <=5 && sellIn[i] > 0)
                    assertEquals(qualities[i]+3, items.get(i).getQuality());
                if(sellIn[i] ==0)
                    assertEquals(0, items.get(i).getQuality());
            }
        }
    }

    @Test
    public void qualityConjured(){
        invent.updateQuality();
        ObservableList<Item> items = invent.getItems();
        for(int i = 0; i < items.size(); i++)
        {
            if(items.get(i).getName().contains("Conjured"))
            {
                assertEquals(qualities[i]-2, items.get(i).getQuality());
            }
        }
    }

    @Test
    public void JsonReaderFile() throws ParseException {
        ObservableList<Item> items = invent.ReaderFileJson("gildedRosebis.json");
        for(Item it : items)
        {
            System.out.println(it);
        }
    }
}
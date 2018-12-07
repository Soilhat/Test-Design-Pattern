package edu.insightr.gildedrose;

import edu.insightr.gildedrose.Inventory;
import edu.insightr.gildedrose.Item;
import edu.insightr.gildedrose.Sulfuras;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
        qualities = new int[invent.getItems().length];
        sellIn = new int[invent.getItems().length];
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
    public void sellPassed() throws Exception{
        invent.updateQuality();
        Item[] items = invent.getItems();
        for(int i = 0; i < items.length; i++){
            if(items[i].getClass() != Sulfuras.class && sellIn[i] ==0 && qualities[i] > 2) {
                assertEquals(qualities[i]-2, items[0].getQuality());
            }
        }
    }

    @Test
    public void positiveQuality(){
        invent.updateQuality();
        Item[] items = invent.getItems();
        for(int i=0; i< items.length; i++){
            if(qualities[i]==0) {
                assertTrue(items[i].getQuality()>=0);
            }
        }
    }

    @Test
    public void agedBrieTest(){
        invent.updateQuality();
        Item[] items = invent.getItems();
        for(int i=0; i< items.length; i++){
            if(items[i].getName()=="Aged Brie") {
                assertEquals(qualities[i]+1, items[i].getQuality());
            }
        }
    }

    @Test
    public void Lower50Test(){
        invent.updateQuality();
        Item[] items = invent.getItems();
        for(int i=0; i< items.length; i++){
            if(!items[i].getName().contains("Sulfuras") && qualities[i] >= 50) {
                assertEquals(50, items[i].getQuality());
            }
        }
    }

    @Test
    public void sulfurasTest(){
        invent.updateQuality();
        Item[] items = invent.getItems();
        for(int i=0; i< items.length; i++){
            if(items[i].getName().contains("Sulfuras")) {
                assertEquals(qualities[i], items[i].getQuality());
                assertEquals(0, items[i].getSellIn());
            }
        }
    }

    @Test
    public void backstageTest(){
        invent.updateQuality();
        Item[] items = invent.getItems();
        for(int i=0; i< items.length; i++){
            if(items[i].getName().contains("Backstage passes")) {
                if(sellIn[i] <=10 && sellIn[i] > 5)
                    assertEquals(qualities[i]+2, items[i].getQuality());
                if(sellIn[i] <=5 && sellIn[i] > 0)
                    assertEquals(qualities[i]+3, items[i].getQuality());
                if(sellIn[i] ==0)
                    assertEquals(0, items[i].getQuality());
            }
        }
    }

    @Test
    public void qualityConjured(){
        invent.updateQuality();
        Item[] items = invent.getItems();
        for(int i = 0; i < items.length; i++)
        {
            if(items[i].getName().contains("Conjured"))
            {
                assertEquals(qualities[i]-2, items[i].getQuality());
            }
        }
    }
}
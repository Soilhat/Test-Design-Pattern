package edu.insightr.gildedrose;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class InventoryTest {
    private Inventory invent;
    private Item[] list;
    private int qual;

    @Before
    public void SetUp()
    {
        invent = new Inventory();
        list = new Item[invent.getItems().length];
        for(int i = 0; i < invent.getItems().length; i++)
            list[i] = invent.getItems()[i];
        qual = invent.getItems()[0].getQuality();
    }

    @After
    public void TearDown(){

    }

    @Test
    public void sellPassed() throws Exception{
        boolean flag = false;
        while(!flag){
        invent.updateQuality();
        Item[] items = invent.getItems();
        /*for(int i=0; i< items.length; i++){*/
            if(items[0].getClass() != Sulfuras.class && items[0].getSellIn()==0 && items[0].getQuality() > 2) {
                flag = true;
                assertEquals(list[0].getQuality()-2, items[0].getQuality());
                //break;
            }
        /*}*/
        }
    }

    @Test
    public void positiveQuality(){
        invent.updateQuality();
        Item[] items = invent.getItems();
        for(int i=0; i< items.length; i++){
            if(list[i].getQuality()==0) {
                assertTrue(items[i].getQuality()>=0);
            }
        }
    }

    @Test
    public void agedBrieTest(){
        invent.updateQuality();
        Item[] items = invent.getItems();
        for(int i=0; i< items.length; i++){
            if(list[i].getName()=="Aged Brie") {
                assertEquals(list[i].getQuality()+1, items[i].getQuality());
            }
        }
    }

    @Test
    public void Lower50Test(){
        invent.updateQuality();
        Item[] items = invent.getItems();
        for(int i=0; i< items.length; i++){
            if(list[i].getQuality() > 50) {
                assertEquals(50, items[i].getQuality());
            }
        }
    }

    @Test
    public void sulfurasTest(){
        invent.updateQuality();
        Item[] items = invent.getItems();
        for(int i=0; i< items.length; i++){
            if(list[i].getName().contains("Sulfuras")) {
                assertEquals(list[i].getQuality(), items[i].getQuality());
                assertEquals(0, items[i].getSellIn());
            }
        }
    }

    @Test
    public void backstageTest(){
        invent.updateQuality();
        Item[] items = invent.getItems();
        for(int i=0; i< items.length; i++){
            if(list[i].getName().contains("Backstage passes")) {
                if(list[i].getSellIn() <=10 && list[i].getSellIn() > 5)
                    assertEquals(list[i].getQuality()+2, items[i].getQuality());
                if(list[i].getSellIn() <=5 && list[i].getSellIn() > 0)
                    assertEquals(list[i].getQuality()+3, items[i].getQuality());
                if(list[i].getSellIn() ==0)
                    assertEquals(0, items[i].getQuality());
            }
        }
    }

    @Test
    public void qualityConjured(){
        invent.updateQuality();
        Item[] items = invent.getItems();
        Item Conjured = items[5];
        assertEquals(list[5].getQuality()-2, Conjured.getQuality());
    }
}

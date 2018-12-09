package edu.insightr.gildedrose;

public abstract class Item implements IVisitable{

    protected String name;

    public int getSellIn() {
        return sellIn;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality){ this.quality = quality;
    }

    protected int sellIn;
    protected int quality;

    public Item(String name, int sellIn, int quality) {
        super();
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public Item() {
        super();
    }

    public String getName() {
        return name;
    }

    protected void newDay(){
        // At the end of each day our system lowers both values for every item
        if(sellIn >0)
        {
            sellIn --;
            if(quality >0) quality --;
        }
    }

    public void updateQuality(){
        newDay();
        //Once the sell by date has passed, Quality degrades twice as fast
        if(sellIn == 0)
            if(quality >= 2)quality -= 2;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{name : '" + name + '\'' +
                ", sellIn : " + sellIn +
                ", quality : " + quality +
                '}';
    }

    public void setSellIn(int i){sellIn = i;}
}
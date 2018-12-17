package edu.insightr.gildedrose.Model;

public abstract class Item implements IVisitable{

    protected String name;
    private String type;
    protected String creation_date;

    public String getType() {return type;}

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

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

    public Item(String name, int sellIn, int quality, String creation_date) {
        type = this.getClass().getSimpleName();
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        this.creation_date = creation_date;
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
                ", creation date : " + creation_date +
                ", quality : " + quality +
                '}';
    }

    public void setSellIn(int i){sellIn = i;}

    @Override
    public boolean equals(Object obj) {
        boolean equality = false;
        if(obj.getClass().equals(this.getClass())){
            if(((Item) obj).getName().equals(name)) equality = true;
        }
        return equality;
    }
}
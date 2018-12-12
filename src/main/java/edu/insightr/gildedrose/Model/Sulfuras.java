package edu.insightr.gildedrose.Model;

import java.util.Date;

public class Sulfuras extends Item{


    public Sulfuras(){

        name="Sulfuras, Hand of Ragnaros";
        sellIn = 0;
        quality = 80;
    }

    public Sulfuras(String name, int sellIn, int quality, String creation_date){
        super(name, sellIn, quality, creation_date);
    }

    @Override
    protected void newDay() {

    }

    @Override
    public void updateQuality() {

    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

package edu.insightr.gildedrose;

public class Sulfuras extends Item{


    public Sulfuras(){

        name="Sulfuras, Hand of Ragnaros";
        sellIn = 0;
        quality = 80;
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

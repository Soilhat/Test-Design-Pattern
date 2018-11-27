package edu.insightr.gildedrose;

public class Backstage_Passes extends Item{

    public Backstage_Passes(){

        name = "Backstage passes to a TAFKAL80ETC concert";
        sellIn = 15;
        quality = 20;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

package edu.insightr.gildedrose;

public class Aged_Brie extends Item{

    public Aged_Brie(){
        name = "Aged Brie";
        sellIn = 2;
        quality = 0;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

package edu.insightr.gildedrose;

public class Aged_Brie extends Item implements IVisitable{

    public Aged_Brie(){
        name = "Aged Brie";
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

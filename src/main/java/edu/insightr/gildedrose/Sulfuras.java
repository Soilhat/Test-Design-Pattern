package edu.insightr.gildedrose;

public class Sulfuras extends Item implements IVisitable{


    public Sulfuras(){
        name="Sulfuras, Hand of Ragnaros";
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

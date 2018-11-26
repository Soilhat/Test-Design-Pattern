package edu.insightr.gildedrose;

public class Backstage_Passes extends Item implements IVisitable{

    public Backstage_Passes(){
        name = "Backstage passes to a TAFKAL80ETC concert";
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

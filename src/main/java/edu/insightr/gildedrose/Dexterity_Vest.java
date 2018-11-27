package edu.insightr.gildedrose;

public class Dexterity_Vest extends Item{

    public Dexterity_Vest(){
        name="+5 Dexterity Vest";
        sellIn = 0;
        quality = 20;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

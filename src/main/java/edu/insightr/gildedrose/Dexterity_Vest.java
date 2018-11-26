package edu.insightr.gildedrose;

public class Dexterity_Vest extends Item implements IVisitable{

    public Dexterity_Vest(){
        name="+5 Dexterity Vest";
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

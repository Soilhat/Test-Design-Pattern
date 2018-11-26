package edu.insightr.gildedrose;

public class Conjured_Mana_Cake extends Item implements IVisitable{

    public Conjured_Mana_Cake(){
        name = "Conjured Mana Cake";
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

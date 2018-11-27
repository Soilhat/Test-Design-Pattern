package edu.insightr.gildedrose;

public class Conjured_Mana_Cake extends Item{

    public Conjured_Mana_Cake(){
        name = "Conjured Mana Cake";
        sellIn = 3;
        quality = 6;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

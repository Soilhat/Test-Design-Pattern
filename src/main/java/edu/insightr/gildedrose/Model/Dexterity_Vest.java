package edu.insightr.gildedrose.Model;

import java.util.Date;

public class Dexterity_Vest extends Item{

    public Dexterity_Vest(){
        name="+5 Dexterity Vest";
        sellIn = 0;
        quality = 20;
    }

    public Dexterity_Vest(String name, int sellIn, int quality, String creation_date){ super(name, sellIn, quality, creation_date);}

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

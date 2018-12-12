package edu.insightr.gildedrose.Model;

import java.util.Date;

public class Conjured_Mana_Cake extends Item{

    public Conjured_Mana_Cake(){
        name = "Conjured Mana Cake";
        sellIn = 3;
        quality = 6;
    }

    public Conjured_Mana_Cake(String name, int sellIn, int quality, String creation_date){ super(name, sellIn, quality, creation_date);}

    @Override
    protected void newDay() {
        // At the end of each day our system lowers both values for every item
        //And Conjured items degrades twice as fast
        if(sellIn >0)
        {
            sellIn --;
            if(quality >0) quality -=2;
        }
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

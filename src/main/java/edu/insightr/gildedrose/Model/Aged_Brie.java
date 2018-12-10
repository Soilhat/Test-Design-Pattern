package edu.insightr.gildedrose.Model;

public class Aged_Brie extends Item{

    public Aged_Brie(){
        name = "Aged Brie";
        sellIn = 2;
        quality = 0;
    }

    public Aged_Brie(String name, int sellIn, int quality){
        super(name, sellIn, quality);
    }

    @Override
    protected void newDay() {
        // "Aged Brie" actually increases in Quality the older it gets
        if(sellIn >0)
        {
            sellIn --;
            if(quality <50) quality ++;
        }
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

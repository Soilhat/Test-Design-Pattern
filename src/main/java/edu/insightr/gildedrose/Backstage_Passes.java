package edu.insightr.gildedrose;

public class Backstage_Passes extends Item{

    public Backstage_Passes(){

        name = "Backstage passes to a TAFKAL80ETC concert";
        sellIn = 9;
        quality = 20;
    }

    public Backstage_Passes(String name, int sellIn, int quality){ super(name, sellIn, quality);}

    @Override
    protected void newDay() {
        // "Backstage passes", like aged brie, increases in Quality as it's SellIn value approaches
        if(sellIn >0)
        {
            sellIn --;
            if(quality <50) quality ++;
        }
        // Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but Quality drops to 0 after the concert
    }

    @Override
    public void updateQuality() {
        super.updateQuality();
        if(sellIn <10)
            if(sellIn < 5)
                if(sellIn == 0)
                    quality = 0;
                else quality += 2;
            else quality +=1;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

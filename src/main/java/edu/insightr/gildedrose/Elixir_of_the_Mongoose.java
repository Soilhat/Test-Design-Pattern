package edu.insightr.gildedrose;

public class Elixir_of_the_Mongoose extends Item{

    public Elixir_of_the_Mongoose(){
        name="Elixir of the Mongoose";
        sellIn = 5;
        quality = 7;
    }

    public Elixir_of_the_Mongoose(String name, int sellIn, int quality){ super(name, sellIn, quality);}

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

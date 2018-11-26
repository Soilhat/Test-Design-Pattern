package edu.insightr.gildedrose;

public class Elixir_of_the_Mongoose extends Item implements IVisitable{

    public Elixir_of_the_Mongoose(){
        name="Elixir of the Mongoose";
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

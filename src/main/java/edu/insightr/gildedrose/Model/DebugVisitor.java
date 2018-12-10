package edu.insightr.gildedrose.Model;

public class DebugVisitor implements IVisitor {

    public void visit(Aged_Brie o)
    {
        System.out.println("Name: " + o.getName());
    }

    public void visit(Backstage_Passes o){
        System.out.println("Name: " + o.getName());
    }

    public void visit(Conjured_Mana_Cake o)
    {
        System.out.println("Name: " + o.getName());
    }

    public void visit(Dexterity_Vest o)
    {
        System.out.println("Name: " + o.getName());
    }

    public void visit(Elixir_of_the_Mongoose o)
    {
        System.out.println("Name: " + o.getName());
    }

    public void visit(Sulfuras o)
    {
        System.out.println("Name: " + o.getName());
    }
    public void visit(IVisitable o)
    {
        System.out.println("Not implemented yet");
    }
}

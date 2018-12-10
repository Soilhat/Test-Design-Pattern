package edu.insightr.gildedrose.Model;

public interface IVisitor {
    void visit(IVisitable o);
    void visit(Aged_Brie o);
    void visit(Backstage_Passes o);
    void visit(Conjured_Mana_Cake o);
    void visit(Dexterity_Vest o);
    void visit(Elixir_of_the_Mongoose o);
    void visit(Sulfuras o);
}

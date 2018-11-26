package edu.insightr.gildedrose;

public class Main {
    public static void main(String[] args)
    {
        DebugVisitor visitor = new DebugVisitor();

        Aged_Brie aged = new Aged_Brie();
        /* Display = name: Aged Brie */
        aged.accept(visitor);

        Backstage_Passes back = new Backstage_Passes();
        back.accept(visitor);
        visitor.visit(back);

        Conjured_Mana_Cake conj = new Conjured_Mana_Cake();
        Dexterity_Vest vest = new Dexterity_Vest();
        Elixir_of_the_Mongoose elixir = new Elixir_of_the_Mongoose();
        Sulfuras sulfuras = new Sulfuras();

        visitor.visit(conj);
        visitor.visit(vest);
        visitor.visit(elixir);
        visitor.visit(sulfuras);
    }
}

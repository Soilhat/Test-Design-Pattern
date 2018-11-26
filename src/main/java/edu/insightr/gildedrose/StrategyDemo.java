package edu.insightr.gildedrose;

public class StrategyDemo {
    // client code here
    private static void execute(IStrategy strategy) {
        strategy.solve();
    }

    public static void main( String[] args ) {
        IStrategy[] algorithms = {new FOO(), new BAR()};
        for (IStrategy algorithm : algorithms) {
            execute(algorithm);
        }
    }
}

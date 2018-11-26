package edu.insightr.gildedrose;

@SuppressWarnings("ALL")
abstract class StrategySolution implements IStrategy{

    @Override
    public void solve() {
        start();
        while(nextTry() && !isSolution()){}
        stop();
    }

    abstract void start();
    abstract boolean nextTry();
    abstract boolean isSolution();
    abstract void stop();
}

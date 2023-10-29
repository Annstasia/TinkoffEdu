package edu.hw3.task6;

import java.util.PriorityQueue;

public class StockMarketImpl implements StockMarket {
    private final PriorityQueue<Stock> priorityQueue =
        new PriorityQueue<>((first, second) -> Integer.compare(second.cost(), first.cost()));

    @Override public void add(Stock stock) {
        priorityQueue.add(stock);
    }

    @Override public void remove(Stock stock) {
        priorityQueue.remove(stock);

    }

    @Override public Stock mostValuableStock() {
        return priorityQueue.peek();
    }
}

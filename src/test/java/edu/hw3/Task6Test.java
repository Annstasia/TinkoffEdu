package edu.hw3;

import edu.hw3.task6.Stock;
import edu.hw3.task6.StockMarket;
import edu.hw3.task6.StockMarketImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Task6Test {
    private static StockMarket market;
    @BeforeEach
    void setMarket() {
        market = new StockMarketImpl();
//        market.add(new Stock("Yandex", 2666));
//        market.add(new Stock("НеЯндекс", 2666));
//        market.add(new Stock("ОФЗ 29014", 997));
//        market.add(new Stock("О'КЕЙ выпуск 4", 998));
    }
    @Test
    void stockTest() {
        String name = "Yandex";
        int cost = 2666;
        Stock stock1 = new Stock(name, cost);
        Stock stock2 = new Stock(name, cost);
        Assertions.assertEquals(stock1.name(), name);
        Assertions.assertEquals(stock1.cost(), cost);
        Assertions.assertEquals(stock1, stock2);
    }

    @Test
    void addTest() {
        Stock stock = new Stock("Yandex", 2666);
        market.add(stock);
        Assertions.assertEquals(market.mostValuableStock(), stock);
    }
    @Test
    void addAndRemoveTest() {
        Stock stock = new Stock("Yandex", 2666);
        market.add(stock);
        market.remove(stock);
        Assertions.assertNull(market.mostValuableStock());
    }

    @Test
    void valuableTest() {
        Stock stock1 = new Stock("Yandex", 2666);
        Stock stock2 = new Stock("НеЯндекс", 2666);
        Stock stock3 = new Stock("ОФЗ 29014", 997);
        market.add(stock1);
        market.add(stock3);
        market.add(stock2);
        Stock mostValuable = market.mostValuableStock();
        Assertions.assertTrue(stock1.equals(mostValuable) || stock2.equals(mostValuable));
        market.remove(mostValuable);
        Stock secondValuable = market.mostValuableStock();
        Assertions.assertTrue(stock1.equals(secondValuable) || stock2.equals(secondValuable));
        Assertions.assertNotEquals(mostValuable, secondValuable);
        market.remove(secondValuable);
        Stock lessValuable = market.mostValuableStock();
        Assertions.assertEquals(lessValuable, stock3);
        market.remove(lessValuable);
        Assertions.assertNull(market.mostValuableStock());

    }





//    @Test
//    void addTest() {
//        Stock stock = new Stock("Yandex", 2666)
//        market.add(new Stock("Yandex", 2666));
//        AssertionError
//    }
//
//    @BeforeEach
//    void setMarket() {
//        market = new StockMarketImpl();
//        market.add(new Stock("Yandex", 2666));
//        market.add(new Stock("НеЯндекс", 2666));
//        market.add(new Stock("ОФЗ 29014", 997));
//        market.add(new Stock("О'КЕЙ выпуск 4", 998));
//    }


}

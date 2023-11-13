//package edu.hw2.task3;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import java.util.stream.IntStream;
//
//public class ConnectionManagerTest {
//    private final static FaultyConnectionManager faultyManager =
//        new FaultyConnectionManager();
//    private final static DefaultConnectionManager defaultManager
//        = new DefaultConnectionManager();
//
//    @Test
//    void DefaultConnectionManagerConnections() {
//        final int experiments = 10000;
//        int faulty = 0;
//        int stable = 0;
//        for (int i = 0; i < experiments; ++i) {
//            if (defaultManager.getConnection() instanceof StableConnection) {
//                ++stable;
//            }
//            else {
//                ++faulty;
//            }
//        }
//        Assertions.assertTrue(stable > 0 && faulty > 0);
//    }
//
//    @Test
//    void FaultyConnectionManagerConnections() {
//        final int experiments = 10000;
//        for (int i = 0; i < experiments; ++i) {
//            Assertions.assertInstanceOf(FaultyConnection.class,
//                faultyManager.getConnection());
//        }
//    }
//}

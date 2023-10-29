//package edu.hw2.task3;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//public class ConnectionTest {
//    private final static FaultyConnection faultyConnection = new FaultyConnection();
//    private final static StableConnection stableConnection = new StableConnection();
//    private final static String command = "ps -aux | grep brain";
//
//    @Test
//    void checkStableConnection() {
//        Assertions.assertDoesNotThrow(()->{
//            for (int i = 0; i < 10000; ++i) {
//                stableConnection.execute(command);
//            }
//        });
//    }
//
//    @Test
//    void checkFaultyConnection() {
//        Assertions.assertThrows(ConnectionException.class, ()->{
//            for (int i = 0; i < 10000; ++i) {
//                faultyConnection.execute(command);
//            }
//        });
//    }
//}

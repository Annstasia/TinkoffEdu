package edu.hw2.task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExecutorTest {
    private static final MockedStatic<Server> serverMock = Mockito.mockStatic(Server.class);
    private static final StableConnection stableConnection = Mockito.mock(StableConnection.class);
    private static final FaultyConnection faultyConnection = Mockito.mock(FaultyConnection.class);

    @BeforeEach
    public void setupTests() {//Если метод принимает String

        Mockito.doNothing().when(stableConnection).close();
        Mockito.doNothing().when(faultyConnection).close();
//        serverMock.when(Server::execute).thenReturn(Void);


//        Mockito.doNothing().when(serverMock).execute(Mockito.anyString()));
//        Mockito.when(serverMock.execute(Mockito.anyString())).thenReturn(Void.TYPE.);
//        when(Server.execute(String)).thenReturn();
//        when(class2.validateObject(anyObj()).thenReturn(true);
    }

    static Arguments[] zeroAttemptsExecutor() {
        return new Arguments[]{
            Arguments.of(new PopularCommandExecutor(0)),
            Arguments.of(new PopularCommandExecutor(new DefaultConnectionManager(), 0)),
            Arguments.of(new PopularCommandExecutor(new FaultyConnectionManager(), 0))
        };
    }

    static Arguments[] oneAttemptExecutor() {
        return new Arguments[]{
            Arguments.of(new PopularCommandExecutor(1)),
            Arguments.of(new PopularCommandExecutor(new DefaultConnectionManager(), 1)),
            Arguments.of(new PopularCommandExecutor(new FaultyConnectionManager(), 1))
        };
    }


    static Arguments[] thousandAttemptExecutor() {
        return new Arguments[]{
            Arguments.of(new PopularCommandExecutor(1000)),
            Arguments.of(new PopularCommandExecutor(new DefaultConnectionManager(), 1000)),
            Arguments.of(new PopularCommandExecutor(new FaultyConnectionManager(), 1000))
        };
    }



    @ParameterizedTest
    @MethodSource("zeroAttemptsExecutor")
    void testZeroAttempts(PopularCommandExecutor executor) {
        Assertions.assertThrows(ConnectionException.class, executor::updatePackages);
    }

    @ParameterizedTest
    @MethodSource("oneAttemptExecutor")
    void testExceedMaxAttempts(PopularCommandExecutor executor) {
        // вероятность failed = 0 для DefaultConnectionManager(для него она самая низка):
        // вероятность выпадения stableConnection или faultyConnection,
        // но с выполняющимся соединением:
        // 99/100 + 1/100*99/100
        // в степени 1000000 очень мало.
        int succeed = 0;
        int failed = 0;
        for (int i = 0; i < 1000000; ++i) {
            try {
                executor.updatePackages();
                ++succeed;
            } catch (ConnectionException e) {
                Assertions.assertNotNull(e.getCause());
                ++failed;
            }
        }
        Assertions.assertTrue(failed > 0);
        Assertions.assertTrue(succeed > 0);
    }

    @ParameterizedTest
    @MethodSource("thousandAttemptExecutor")
    void testThousandAttemptsSucceed(PopularCommandExecutor executor) {
        int succeed = 0;
        int failed = 0;
        for (int i = 0; i < 1000000; ++i) {
            try {
                executor.updatePackages();
                ++succeed;
            } catch (ConnectionException e) {
                Assertions.assertNotNull(e.getCause());
                ++failed;
            }
        }
        Assertions.assertEquals(0, failed);
    }
}

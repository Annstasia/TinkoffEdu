package edu.hw8.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientServerTest {
    List<String> expectedAnswers = List.of(
        "Не переходи на личности там, где их нет",
        "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "Чем ниже интеллект, тем громче оскорбления"
    );
    List<String> keyWords = List.of(
        "личности",
        "оскорбления",
        "глупый",
        "интеллект"
    );

    @Test
    void testClientServerInteraction() throws IOException, InterruptedException {
        Server server = new Server();
        Thread runServer = new Thread(server::run);
        runServer.start();
        try (ExecutorService clientExecutor = Executors.newFixedThreadPool(6)){
            int threadCount = 1000;
            CountDownLatch latch = new CountDownLatch(threadCount);
            for (int i = 0; i < threadCount; ++i) {
                String expected = expectedAnswers.get(i % expectedAnswers.size());
                String keyWord = keyWords.get(i % keyWords.size());
                clientExecutor.execute(()->{
                    Assertions.assertEquals(expected, Client.curse(keyWord));
                    latch.countDown();
                });
            }
        }
    }
}

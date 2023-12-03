package edu.hw8;

import edu.hw8.task2.FixedThreadPool;
import edu.hw8.task2.ThreadPool;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicInteger;

public class Task2Test {
    // На моем устройстве тест выполняется за 25 секунд.
    // Запускается 1000 задач по 100 миллисекунд каждая
    // Без многопоточности выполнялось бы хотя бы 100 секунд
    @Test
    void testFixedThreadPool() throws Exception {
        AtomicInteger handledTaskCount = new AtomicInteger(0);
        ThreadPool pool = new FixedThreadPool(4);
        pool.start();
        long startTime = System.currentTimeMillis();
        int taskCount = 1000;
        int sleepMillis = 100;
        for (int i = 0; i < taskCount; ++i) {
            pool.execute(()->{
                handledTaskCount.incrementAndGet();
                try {
                    Thread.sleep(sleepMillis);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        pool.close();
        long endTime = System.currentTimeMillis();
        Assertions.assertEquals(taskCount, handledTaskCount.get());
        Assertions.assertTrue(endTime - startTime < taskCount * sleepMillis);
    }
}

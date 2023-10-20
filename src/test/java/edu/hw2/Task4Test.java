package edu.hw2;

import edu.hw2.task4.Task4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task4Test {
    static class Helper {
        public static Task4.CallingInfo help() {
            return Task4.callingInfo();
        }
    }

    @Test
    void testCallingInfo() {
        Task4.CallingInfo call = Helper.help();
        Assertions.assertEquals(call.className(), Helper.class.getName());
        Assertions.assertEquals(call.methodName(), "help");
    }
}

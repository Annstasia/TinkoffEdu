package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private final static int PORT = 8081;
    private final static int SLEEP_TIME = 100;

    private Client() {
    }

    public static String curse(String keyWorld) {
        String answer = "";
        try (Socket client = new Socket(InetAddress.getByName("localhost"), PORT);
             PrintWriter writer1 =
                 new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
             BufferedReader reader1 = new BufferedReader(
                 new InputStreamReader(client.getInputStream()))) {
            writer1.println(keyWorld);
            Thread.sleep(SLEEP_TIME);
            writer1.flush();
            answer = reader1.readLine();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
        return answer;
    }
}

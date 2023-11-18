package edu.hw6.task5;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class HackerNews {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        System.out.println(Arrays.toString(new HackerNews().hackerNewsTopStories()));
    }
    long[] hackerNewsTopStories() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request =HttpRequest.newBuilder(
            new URI("https://hacker-news.firebaseio.com/v0/topstories.json")
                                        )
                                        .GET()
                                        .build();
        long[] answer = new long[0];
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String stringAnswer = response.body();
            if (stringAnswer.length() > 2) {
                answer =
                    Arrays.stream(
                        response.body().substring(1, stringAnswer.length() - 1)
                                .split(",")).mapToLong(Long::valueOf).toArray();
            }
        }
        return answer;
    }
}

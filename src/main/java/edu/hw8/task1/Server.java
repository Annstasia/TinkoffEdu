package edu.hw8.task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {
    private final static int PORT = 8081;
    final static int THREAD_COUNT = 4;
    private final static Logger LOGGER = LogManager.getLogger();
    private final CurseGenerator curseGenerator;
    private final ServerSocketChannel serverChannel;
    private final static int BUFFER_SIZE = 10240;

    public Server() throws IOException {
        curseGenerator = new MVPFileCurseGenerator();
        serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress("localhost", PORT));
    }

    private static void accept(SelectionKey key, Selector selector) throws IOException {
        SocketChannel client = null;
        try {
            client = ((ServerSocketChannel) key.channel()).accept();
            if (client != null) {
                client.configureBlocking(false);
                client.register(selector, SelectionKey.OP_READ);
            }
        } catch (IOException e) {
            if (client != null) {
                client.close();
            }
            throw e;
        }
    }

    private static void read(SelectionKey key, CurseGenerator generator) throws IOException {
        try (SocketChannel channel = (SocketChannel) key.channel()) {
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            channel.read(buffer);
            buffer.flip();
            String answer = generator.curse(StandardCharsets.UTF_8.decode(buffer).toString().strip());
            ByteBuffer output = ByteBuffer.wrap(answer.getBytes());
            channel.write(output);
        }
    }

    public void run() {
        Runnable intersection = () -> {
            try (Selector selector = SelectorProvider.provider().openSelector()) {
                serverChannel.register(selector, SelectionKey.OP_ACCEPT);
                while (true) {
                    try {
                        selector.select();
                        Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                        while (keyIterator.hasNext()) {
                            SelectionKey key = keyIterator.next();
                            if (key.isAcceptable()) {
                                accept(key, selector);
                            }
                            if (key.isReadable()) {
                                read(key, curseGenerator);
                            }
                            keyIterator.remove();
                        }
                    } catch (IOException e) {
                        LOGGER.error(e);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
        try (ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT)) {
            for (int i = 0; i < THREAD_COUNT; ++i) {
                executorService.execute(intersection);
            }
        }
    }
}

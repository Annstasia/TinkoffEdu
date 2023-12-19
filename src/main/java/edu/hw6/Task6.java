package edu.hw6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task6 {
    private static final String TCP_STRING = "TCP port ";
    private static final String UDP_STRING = "UDP port ";
    private static final String BUSY = " is busy";
    private static final String FREE = " is free";

    private static final int AVAILABLE_PORTS_COUNT = 49151;

    private static final Logger LOGGER = LogManager.getLogger();
    @SuppressWarnings("MagicNumber")
    static Set<PortInfo> portInfos = Set.of(
        new PortInfo(53, "system-rd", "UDP")
    );

    private Task6() {
    }

    public static void task6() {
        for (int i = 0; i < AVAILABLE_PORTS_COUNT; ++i) {
            try (ServerSocket server = new ServerSocket(i)) {
                LOGGER.info(TCP_STRING + i + FREE);
            } catch (IOException e) {
                LOGGER.error(TCP_STRING + i + BUSY);
            }
            try (DatagramSocket server = new DatagramSocket(i)) {
                LOGGER.info(UDP_STRING + i + FREE);
            } catch (IOException ignore) {
                LOGGER.error(UDP_STRING + i + BUSY);
            }

        }
    }

    record PortInfo(int port, String protocol, String service) {
    }
}

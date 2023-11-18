package edu.hw6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Task6 {
    static record PortInfo(int port, String protocol, String service)

    static Set<PortInfo> portInfos = Set.of(
        new PortInfo()
    );
    public static void main(String[] args){
        for (int i = 0; i < 49151; ++i) {
            try (ServerSocket server = new ServerSocket(i)) {
            } catch (IOException e) {
                System.out.println("TCP port " + i + "is busy");
            }
            try (DatagramSocket server = new DatagramSocket(i)) {
            } catch (IOException ignore) {
                System.out.println("UDP port " + i + "is busy");
            }



        }
    }
}

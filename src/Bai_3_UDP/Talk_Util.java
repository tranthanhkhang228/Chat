package Bai_3_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Talk_Util {
    static byte[] buffer = new byte[6000];

    public static DatagramPacket receive(DatagramSocket ds) {

        DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
        try {
            ds.receive(incoming);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return incoming;
    }

    public static void send(DatagramSocket ds, byte[] data, int length, InetAddress address, int port) {
        DatagramPacket outsending = new DatagramPacket(data, length, address, port);
        try {
            ds.send(outsending);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

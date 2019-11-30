package Bai_3_UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveMessage extends Thread {
    protected DatagramSocket ds;

    public ReceiveMessage(DatagramSocket ds) {
        this.ds = ds;
    }

    public void run() {
        String message;
        do {
            DatagramPacket incoming = Talk_Util.receive(this.ds);
            message = new String(incoming.getData(), 0, incoming.getLength());
            System.out.println(message);
        } while (!message.equals("bye"));
    }
}

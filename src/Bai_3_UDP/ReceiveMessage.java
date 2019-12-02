package Bai_3_UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveMessage extends Thread {
    protected DatagramSocket ds;
    private String userName;

    public ReceiveMessage(DatagramSocket ds, String userName) {
        this.ds = ds;
        this.userName = userName;
    }

    public void run() {
        String message;
        do {
            DatagramPacket incoming = Talk_Util.receive(this.ds);
            message = new String(incoming.getData(), 0, incoming.getLength());

            System.out.println("\n" + message);
            System.out.print(userName + ": ");
        } while (!message.equals("bye"));
    }
}

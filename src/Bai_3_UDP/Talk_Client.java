package Bai_3_UDP;

import java.io.IOException;
import java.net.*;

public class Talk_Client {
    private int serverPort;
    private InetAddress address;

    public Talk_Client(int serverPort, InetAddress address) {
        this.serverPort = serverPort;
        this.address = address;
    }

    public void execute() {
        try {
            DatagramSocket ds = new DatagramSocket();

            new SendMessage(ds, this.address, serverPort).start();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    public static void main(String[] args) {
        int serverPort = 6969;
        InetAddress address = null;
        try {
             address = InetAddress.getByName("localhost");

        } catch (UnknownHostException e) {
            System.out.println(e);
        }

        Talk_Client client = new Talk_Client(serverPort, address);
        client.execute();
    }

}

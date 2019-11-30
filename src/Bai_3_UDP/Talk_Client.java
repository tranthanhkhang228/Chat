package Bai_3_UDP;

import java.io.IOException;
import java.net.*;

public class Talk_Client {
    private int serverPort;

    public Talk_Client(int serverPort) {
        this.serverPort = serverPort;
    }

    public void execute() {
        try {
            DatagramSocket ds = new DatagramSocket();
            InetAddress server = InetAddress.getByName("localhost");

            new SendMessage(ds, server, serverPort).start();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        int serverPort = 6969;

        Talk_Client client = new Talk_Client(serverPort);
        client.execute();
    }

}

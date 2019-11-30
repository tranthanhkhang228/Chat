package Bai_3_UDP;

import java.io.IOException;
import java.net.*;
import java.util.HashSet;
import java.util.Set;

public class Talk_Server {
    private int port;

    public Talk_Server(int port) {
        this.port = port;
    }

    private Set<User> userList = new HashSet<>();

    public Set<User> getUserList() {
        return userList;
    }

    public void execute() {
        try {
            DatagramSocket ds = new DatagramSocket(this.port);

            new S_ReceiveMessage(ds, this).start();
            new S_SendMessage(ds, this).start();

            // duy tri server
            while (true) {}
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        int port = 6969;

        Talk_Server server = new Talk_Server(port);
        server.execute();
    }

}

package Bai_3_UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Iterator;

public class S_Send extends Thread {
    private DatagramSocket ds;
    private DatagramPacket incoming;
    private Talk_Server server;

    public S_Send(DatagramSocket ds, DatagramPacket incoming, Talk_Server sv) {
        this.ds = ds;
        this.incoming = incoming;
        this.server = sv;
    }

    public void run() {
        Iterator<User> userList;

        while (true) {
            DatagramPacket message = Talk_Util.receive(this.ds);

            userList = this.server.getUserList().iterator();
            while (userList.hasNext()) {
                User user = userList.next();

                if (user.getPort() != this.incoming.getPort() && user.getAddress() != incoming.getAddress()) {
                    Talk_Util.send(this.ds, message.getData(), message.getLength(), user.getAddress(), user.getPort());

                }
            }
        }
    }
}

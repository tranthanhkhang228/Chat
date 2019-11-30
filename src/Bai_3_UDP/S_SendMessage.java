package Bai_3_UDP;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class S_SendMessage extends Thread {
    private Talk_Server server;
    private DatagramSocket ds;

    public S_SendMessage(DatagramSocket ds, Talk_Server server) {
        this.server = server;
        this.ds = ds;
    }

    public void run() {
        Iterator<User> userList;
        String message = "";

        // gui tin nhan
        while (true) {
            userList = this.server.getUserList().iterator();

            while (userList.hasNext()) {
                User user = userList.next();

                synchronized (user) {
                    Map<String, Integer> listMessage = user.getReceiveMessage();

                    if (!listMessage.isEmpty()) {

                        Set<String> set = listMessage.keySet();
                        for (String key : set) {
                            message += key;
                        }

                        listMessage.clear();

                        InetAddress address = null;
                        try {
                            address = InetAddress.getByName("localhost");
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        }

                        Talk_Util.send(ds, message.getBytes(), message.length(), address, user.getPort());
                        message = "";

                    }
                }
            }
        }
    }
}

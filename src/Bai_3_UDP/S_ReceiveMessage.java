package Bai_3_UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class S_ReceiveMessage extends Thread {
    private Talk_Server server;
    private DatagramSocket ds;

    public S_ReceiveMessage(DatagramSocket ds, Talk_Server server) {
        this.ds = ds;
        this.server = server;
    }

    public void run() {
        String message;
        Iterator<User> userList;
        while (true) {
            DatagramPacket incoming = Talk_Util.receive(this.ds);
            message = new String(incoming.getData(), 0, incoming.getLength());

            // kiem tra xem la dang nhap hay gui tin nhan thong qua chuoi ky tu dau : "new"
            if (message.startsWith("new")) {
                // lay ten dang nhap tu goi tin
                String userName = message.substring(3);

                // kiem tra xem user co trong danh sach chua
                boolean check = false;
                userList = this.server.getUserList().iterator();
                while (userList.hasNext()) {
                    User temp = userList.next();
                    if (userName.equals(temp.getUserName())) {
                        // neu co roi thi cap nhat lai port cho user
                        check = true;
                        temp.setPort(incoming.getPort());
                        break;
                    }
                }

                // neu chua co thi them user vao danh sach
                if (!check) {
                    User newUser = new User(userName, incoming.getPort());
                    synchronized (this.server.getUserList()) {
                        this.server.getUserList().add(newUser);
                    }
                }

                // thong bao user da ket noi toi server
                System.out.println(userName + " connected");

                // gui danh sach user dang ket noi cho user moi
                message = "Users: ";
                userList = this.server.getUserList().iterator();
                while (userList.hasNext()) {
                    User temp = userList.next();
                    message += temp.getUserName() + ", ";
                }
                Talk_Util.send(ds, message.getBytes(), message.length(), incoming.getAddress(), incoming.getPort());

            }
            // nhan tin nhan
            else {
                int port = incoming.getPort();

                userList = this.server.getUserList().iterator();
                while (userList.hasNext()) {
                    User temp = userList.next();
                    if (temp.getPort() != port) {
                        temp.addMessage(message, port);
                    }
                }

            }
        }
    }
}

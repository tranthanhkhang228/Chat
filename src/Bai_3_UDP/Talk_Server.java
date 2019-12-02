package Bai_3_UDP;

import java.io.IOException;
import java.net.*;
import java.util.HashSet;
import java.util.Iterator;
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
            String userName = "";

            while (true) {
                DatagramPacket incoming = Talk_Util.receive(ds);
                String message = new String(incoming.getData(), 0, incoming.getLength());

                userName = message;

                // kiem tra xem user co trong danh sach chua
                boolean check = false;
                Iterator<User> userList;
                userList = this.userList.iterator();
                while (userList.hasNext()) {
                    User temp = userList.next();
                    if (userName.equals(temp.getUserName())) {
                        // neu co roi thi cap nhat lai port cho user
                        check = true;
                        temp.setPort(incoming.getPort());
                        temp.setAddress(incoming.getAddress());
                        break;
                    }
                }

                // neu chua co thi them user vao danh sach
                if (!check) {
                    User newUser = new User(userName, incoming.getPort(), incoming.getAddress());
                    synchronized (this.userList) {
                        this.userList.add(newUser);
                    }
                }

                // thong bao user da ket noi toi server
                System.out.println(userName + " connected");

                DatagramSocket temp_ds = new DatagramSocket();
                String notice = "Dang nhap thanh cong";
                Talk_Util.send(temp_ds, notice.getBytes(), notice.length(), incoming.getAddress(), incoming.getPort());

                new S_Send(temp_ds, incoming, this).start();
            }
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

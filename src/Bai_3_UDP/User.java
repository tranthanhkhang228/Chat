package Bai_3_UDP;

import java.net.InetAddress;

public class User {
    private String userName;
    private int port;
    private InetAddress address;

    public User(String userName, int port, InetAddress address) {
        this.userName = userName;
        this.port = port;
        this.address = address;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }


    public String getUserName() {
        return userName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}


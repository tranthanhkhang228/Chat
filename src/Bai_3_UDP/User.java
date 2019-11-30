package Bai_3_UDP;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String userName;
    private int port;
    private Map<String, Integer> receiveMessage = new HashMap<>();

    public User(String userName, int port) {
        this.userName = userName;
        this.port = port;
    }

    public Map<String, Integer> getReceiveMessage() {
        return receiveMessage;
    }

    public void setReceiveMessage(Map<String, Integer> receiveMessage) {
        this.receiveMessage = receiveMessage;
    }

    public void addMessage(String message, int userSend) {
        this.receiveMessage.put(message, userSend);
    }

    public void removeMessage(String key) {
        this.receiveMessage.remove(key);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}


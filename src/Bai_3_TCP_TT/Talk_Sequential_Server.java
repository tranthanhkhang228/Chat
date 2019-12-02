package Bai_3_TCP_TT;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Talk_Sequential_Server {
    private final static int defaultPort = 69;

    public static void main(String[] args) {
        ServerSocket ss = null;
        BufferedReader is;
        BufferedWriter os;
        Socket s;
        String request; // bien dinh danh client nao dang ket noi
        String message1 = ""; // luu tin nhan tu client 1 gui len
        String message2 = ""; // luu tin nhan tu client 2 gui len

        // tao ServerSocket
        try {
            ss = new ServerSocket(defaultPort);
        } catch (IOException e) {
            System.out.println("Server Creation Error: " + e);
            System.exit(1);
        }

        // lien tuc don nhan cac yeu cau ket noi tu cac client
        while (true) {
            try {
                System.out.println("Server is waiting to accept user...");
                s = ss.accept();

                System.out.println("Accepted a user!"); // thong bao ket noi duoc voi 1 client

                os = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                is = new BufferedReader(new InputStreamReader(s.getInputStream()));

                // nhan gia tri dinh danh gui tu client len
                // xac dinh client nao dang ket noi voi server
                request = is.readLine();

                if (request.equals("C1")) {
                    // gui tin nhan tu client 2 cho client 1
                    if (message2.isEmpty()) {
                        os.write("Khong co tin nhan");
                    } else {
                        os.write(message2);
                    }
                } else {
                    if (message2.isEmpty()) {
                        os.write("Khong co tin nhan");
                    } else {
                        os.write(message1);
                    }
                }
                os.newLine();
                os.flush();

                // nhan tin nhan tu client gui len
                if (request.equals("C1")) {
                    message1 = is.readLine(); // luu tin nhan tu client 1
                } else {
                    message2 = is.readLine(); // luu tin nhan tu client 2
                }

            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }
}

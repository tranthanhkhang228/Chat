package Bai_3_TCP_TT;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Talk_Test_1 {
    public static void main(String[] args) {
        Socket s;
        BufferedReader is;
        BufferedWriter os;
        Scanner sc = new Scanner(System.in);
        String request;
        String response;

        while (true) {
            try {
                s = new Socket("127.0.0.1", 69);

                os = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

                is = new BufferedReader(new InputStreamReader(s.getInputStream()));

            } catch (IOException e) {
                System.out.println("Client Creation Error " + e);
                return;
            }

            try {
                // gui bien dinh danh cho server
                os.write("C1");
                os.newLine();
                os.flush();

                // nhan tin nhan tu client 2
                response = is.readLine();
                if(response != null){
                    System.out.println(response);
                }

                // gui tin nhan cho client 2
                System.out.print("C1: ");
                request = sc.nextLine();
                os.write("C1 " + request);
                os.newLine();
                os.flush();

                s.close();
            } catch (IOException e) {
                System.out.println(e);
            }

        }
    }
}

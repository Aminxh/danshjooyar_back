package Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Login {
    public static void main(String[] args) {

        try {
            ServerSocket inputDataUserInfo = new ServerSocket(7777);
            while (true){
            Socket accepter = inputDataUserInfo.accept();
            System.out.println("connected");
            LoginHandler log = new LoginHandler(accepter);
            log.run();
            }

        } catch (IOException e) {
        }
    }
}


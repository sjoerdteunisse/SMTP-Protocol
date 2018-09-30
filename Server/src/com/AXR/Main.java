package com.AXR;

import com.AXR.Server.SMTP.Context.SMTPContext;

import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) {

        int port = 1111;
        System.out.println("Listening on port: "  + port);

        try(ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                new SMTPContext(serverSocket.accept()).start();
            }
        }catch (Exception e){
            System.err.println("Failed to listen on " + port);
            System.exit(-1);
        }
    }
}

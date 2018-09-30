package src.nl.avans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SMPTClient {
    public static void main(String[] args) throws IOException {


        String hostName = "192.168.86.55";
        int portNumber = 1111;

        try (
            Socket socket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                String fromServer;
                String fromUser;


            while ((fromServer = in.readLine()) != null) {
                System.out.println(fromServer);

                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    out.println(fromUser);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }
}
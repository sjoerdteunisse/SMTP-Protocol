package com.AXR.Server.SMTP.Context;

import com.AXR.DomainLayer.Mail;
import com.AXR.Server.SMTP.States.WelcomeState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SMTPContext extends Thread {

    private SMTPState currentState;
    private SMTPState newState;

    private Socket socket;
    private PrintWriter printWriter;

    private Mail mailObject;


    public SMTPContext(Socket socket){
        super("ContextThread");
        this.socket = socket;
        mailObject = new Mail();

        CreateOutputPr();
        currentState= new WelcomeState(this);

        run();
    }

    public void CreateOutputPr(){
        try{
            printWriter = new PrintWriter(socket.getOutputStream(), true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void setState(SMTPState newState){
        //set newState
        this.newState = newState;
    }

    private void setNewState(){
        //Check if newState isn't null;
        if(newState != null){
            //set currentState to our new State
            currentState = newState;
            //set our newState to null
            setState(null);
        }
    }



    public void run(){
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))){

            String fromClient;

            while ((fromClient = bufferedReader.readLine()) != null){
                //Pass to our State, so it can process the data.
                currentState.handle(fromClient);
                //Print our data from client
                System.out.println("C: " + fromClient);
                //If statement equals Exit.
                if(fromClient.equals("QUIT"))
                    break;
                //Set the new state.
                setNewState();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void SendData(String data){
        printWriter.println(data);
        System.out.println("S: " + data);
    }

    public void Disconnect(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Set and Get from MailObj
    public void SetHost(String hostname) {
        this.mailObject.setHostName(hostname);
    }

    public String GetHost() {
        return this.mailObject.getHostName();
    }

    public void SetMailFrom(String mailFrom) {
        this.mailObject.setMailFrom(mailFrom);
    }

    public String GetMailFrom() {
        return this.mailObject.getMailFrom();
    }

    public void AddRecipient(String recipient){
        mailObject.setRecipientTo(recipient);
    }

    public void AddTextToBody(String text) {
        mailObject.setBody(text + "\n");
    }

    public StringBuilder GetTextBodyData(){
        return mailObject.getBody();
    }
}

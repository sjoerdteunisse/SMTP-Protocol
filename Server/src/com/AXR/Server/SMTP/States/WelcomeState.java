package com.AXR.Server.SMTP.States;

import com.AXR.Server.SMTP.Context.SMTPContext;
import com.AXR.Server.SMTP.Context.SMTPState;

public class WelcomeState implements SMTPState {

    private SMTPContext smtpContext;

    public WelcomeState(SMTPContext smtpContext){
        this.smtpContext = smtpContext;
        smtpContext.SendData("220 smtp.axr.com Welcome to SMTP server!");
    }

    @Override
    public void handle(String data) {


        if(data.startsWith("HELO ")){
            smtpContext.SetHost(data.substring(5));
            smtpContext.SendData("250 Hello " + smtpContext.GetHost() + ", I am glad to meet you");

            //Set new state and Pas by Ref to WaitForMailFromState.
            smtpContext.setState(new WaitForMailFromState(smtpContext));
            return;
        }

        if(data.toUpperCase().startsWith("QUIT")) {
            smtpContext.SendData("221 Bye");
            smtpContext.Disconnect();
            return;
        }

        //On Error
        smtpContext.SendData("503 Error");
    }
}

package com.AXR.Server.SMTP.States;

import com.AXR.Server.SMTP.Context.SMTPContext;
import com.AXR.Server.SMTP.Context.SMTPState;

public class WaitForRcptToState  implements SMTPState {

    private SMTPContext smtpContext;

    public WaitForRcptToState(SMTPContext smtpContext){
        this.smtpContext = smtpContext;
    }

    @Override
    public void handle(String data) {


        if(data.toUpperCase().startsWith("RCPT TO: ")) {
            smtpContext.AddRecipient(data.substring(("RCPT TO: ").length()));
            smtpContext.SendData("Added RCPT");
            smtpContext.setState(new WaitForRcptToOrDataState(smtpContext));
            return;
        }

        if(data.toUpperCase().startsWith("QUIT")) {
            smtpContext.SendData("221 Bye");
            smtpContext.Disconnect();
            return;
        }

        //On Error
        smtpContext.SendData("503 Error ");


        //handle "RCPT TO: <user@domain.nl>" Command & TRANSITION TO NEXT STATE
        //handle "QUIT" Command
        //Generate "503 Error on invalid input"
    }
}

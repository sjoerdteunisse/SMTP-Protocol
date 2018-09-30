package com.AXR.Server.SMTP.States;

import com.AXR.Server.SMTP.Context.SMTPContext;
import com.AXR.Server.SMTP.Context.SMTPState;

public class WaitForRcptToOrDataState implements SMTPState {

    private SMTPContext smtpContext;

    public WaitForRcptToOrDataState(SMTPContext smtpContext){
        this.smtpContext = smtpContext;
    }

    @Override
    public void handle(String data) {

            if(data.toUpperCase().startsWith("RCPT TO: ")) {
                smtpContext.AddRecipient(data.substring(("RCPT TO: ").length()));
                smtpContext.SendData("Added RCPT");
            } else if (data.toUpperCase().startsWith("DATA")){
                smtpContext.setState(new ReceivingDataState(smtpContext));
            }

        //smtpContext.setState(new ReceivingDataState(smtpContext));
        ///handle "MAIL FROM: <user@domain.nl>" Command & TRANSITION TO NEXT STATE
        //handle "DATA" Command & TRANSITION TO NEXT STATE
        //handle "QUIT" Command
        //Generate "503 Error on invalid input"






    }
}

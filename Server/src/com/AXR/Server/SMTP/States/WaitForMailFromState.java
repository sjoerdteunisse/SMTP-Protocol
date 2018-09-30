package com.AXR.Server.SMTP.States;

import com.AXR.Server.SMTP.Context.SMTPContext;
import com.AXR.Server.SMTP.Context.SMTPState;

public class WaitForMailFromState  implements SMTPState {
    private SMTPContext smtpContext;

    public WaitForMailFromState(SMTPContext smtpContext){
        this.smtpContext = smtpContext;
    }

    @Override
    public void handle(String data) {

        if (data.startsWith("MAIL FROM: ")){
            smtpContext.SetMailFrom(data.substring(("MAIL FROM: ").length()));
            smtpContext.SendData("Mail from: " + smtpContext.GetMailFrom());
            smtpContext.setState(new WaitForRcptToState(smtpContext));
        }
    }
}

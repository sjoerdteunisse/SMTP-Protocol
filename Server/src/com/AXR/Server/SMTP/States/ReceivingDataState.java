package com.AXR.Server.SMTP.States;

import com.AXR.Server.SMTP.Context.SMTPContext;
import com.AXR.Server.SMTP.Context.SMTPState;

import java.util.Random;

public class ReceivingDataState implements SMTPState {

    private SMTPContext smtpContext;
    private StringBuilder stringBuilder;

    public ReceivingDataState(SMTPContext smtpContext) {
        this.smtpContext = smtpContext;
        this.stringBuilder = new StringBuilder();

        smtpContext.SendData("354 End data with <CR><LF>.<CR><LF>");
    }

    @Override
    public void handle(String data) {
        //Needs enter

        if(data != null) {
            //stringBuilder.append(data);
            smtpContext.SendData("");
            smtpContext.AddTextToBody(data);
        }

        if(smtpContext.GetTextBodyData() != null) {
            StringBuilder sb;

            sb = smtpContext.GetTextBodyData();

            if(sb.length() > 3) {
                String lastSegment = sb.substring(sb.length() - 3);

                if (lastSegment.equals("\n.\n")) {
                    smtpContext.SendData("250 OK: Queued as " + new Random().nextInt(200));
                    //smtpContext.Disconnect();
                    smtpContext.SendData("Bye");
                }
            }
        }

    }
}

package edu.pdx.cs410J.jonerik;


import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;


/**
 *
 * Created by jonerik13 on 7/13/15.
 */
public class TextDumper implements PhoneBillDumper {

    private DataOutputStream call;

    public TextDumper(DataOutputStream call) {
        this.call = call;
    }


    public void dump(AbstractPhoneBill var1) throws IOException {

        call.writeBytes(String.valueOf(var1.getCustomer()));
        call.writeBytes("\n");

        List <PhoneCall> temp = (List<PhoneCall>) var1.getPhoneCalls();
        for ( AbstractPhoneCall each : temp) {
            String parse = String.valueOf(each);

            String[] parseCall = parse.split(" ");
            call.writeBytes(parseCall[3] + "," + parseCall[5] + "," + (parseCall[7] + " " + parseCall[8]) +
                    "," + (parseCall[10] + " " + parseCall[11] + "\n")); //.substring(0, (parseCall[11].length() - 1))));
        }

    }
}

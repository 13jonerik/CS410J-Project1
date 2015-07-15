package edu.pdx.cs410J.jonerik;


import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Collection;

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
          call.writeBytes(String.valueOf(var1.getPhoneCalls()));

    }
}

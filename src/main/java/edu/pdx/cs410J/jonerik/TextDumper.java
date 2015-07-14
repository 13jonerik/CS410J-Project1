package edu.pdx.cs410J.jonerik;


import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * Created by jonerik13 on 7/13/15.
 */
public class TextDumper implements PhoneBillDumper{

    private DataOutputStream call;

    public TextDumper(DataOutputStream call) {
        this.call = call;
    }


    public void dump(AbstractPhoneBill var1) throws IOException {

          call.writeBytes(var1.toString());

    }
}

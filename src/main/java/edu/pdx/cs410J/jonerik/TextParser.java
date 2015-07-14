package edu.pdx.cs410J.jonerik;

import edu.pdx.cs410J.PhoneBillParser;
import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.ParserException;


import java.io.*;

/**
 *
 * Created by jonerik13 on 7/13/15.
 */
public class TextParser implements PhoneBillParser {

    DataInputStream call;



    public TextParser (DataInputStream call) {
        this.call = call;
    }


    public AbstractPhoneBill parse() throws ParserException {
        AbstractPhoneBill Bill = null;

        try {
            InputStream temp = call;
            BufferedReader reader = new BufferedReader(new InputStreamReader(temp));
            String singleCall;

            while((singleCall = reader.readLine()) != null) {
                String[] split = singleCall.split(",");

                Bill.addPhoneCall(new PhoneCall(split[0], split[1], split[2],
                                    split[3]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return Bill;
    }


}



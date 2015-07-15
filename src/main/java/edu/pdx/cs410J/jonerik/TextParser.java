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
        AbstractPhoneBill phoneBill = null;

        try {
            InputStream temp = call;
            BufferedReader reader = new BufferedReader(new InputStreamReader(temp));
            String customer;
            String singleCall;

            customer = reader.readLine();
            //System.out.println(customer);
            //if (!(customer.matches("[a-zA-Z\\s*'-=!@#$%^&?_1234567890,.]+"))) { return phoneBill; }

            PhoneBill bill = new PhoneBill(customer);

            while((singleCall = reader.readLine()) != null) {
                String[] split = singleCall.split(",");
                //if (!checkCall(split[0], split[1], split[2], split[3])) { return bill; }

                bill.addPhoneCall(new PhoneCall(split[0], split[1], split[2], split[3]));

            }

            phoneBill = bill;

        } catch (IOException e) {
            System.err.println("Error in IOException: " + e.getMessage());
        }

        return phoneBill;
    }

    public boolean checkCall (String callerNumber, String calleeNumber, String startTime, String endTime) {

        if (!callerNumber.matches("[0-9]{3}[-][0-9]{3}[-][0-9]{4}")) { return false; }
        if (!calleeNumber.matches("[0-9]{3}[-][0-9]{3}[-][0-9]{4}")) { return false; }
        if (!startTime.matches("[0-9][0-9]{0,1}[/][0-9][0-9]{0,1}[/][0-9]{4}[\\s*][0-9][0-9]{0,1}[:][0-5][0-9]")) { return false; }
        if (!endTime.matches("[0-9][0-9]{0,1}[/][0-9][0-9]{0,1}[/][0-9]{4}[\\s*][0-9][0-9]{0,1}[:][0-5][0-9]")) { return false; }
        return true;

    }


}



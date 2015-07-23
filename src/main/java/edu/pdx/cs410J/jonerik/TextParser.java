package edu.pdx.cs410J.jonerik;

import edu.pdx.cs410J.PhoneBillParser;
import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.ParserException;


import java.io.*;

/**
 * The TextParse class is responsible for
 * taking the contents of a file and then
 * parsing it and adding it to a phone bill.
 * If a file does not exist or is empty, the
 * bill will be null when returned. If the
 * incoming arguments are not well formed, a
 * parser exception is thrown and the program
 * will stop execution.
 */
public class TextParser implements PhoneBillParser {

    DataInputStream call;


    /**
     * Constructor for TextParser class. Takes
     * in a DataInputStream to parse the file.
     */
    public TextParser (DataInputStream call) {
        this.call = call;
    }


    /**
     * The parse method will have a DataInputStream and
     * read the contents of a text file, checking if they
     * are well formed, and then add the customer name and
     * the phone call fields to a phone bill.
     */
    public AbstractPhoneBill parse() throws ParserException {
        AbstractPhoneBill phoneBill = null;

        try {
            InputStream temp = call;
            BufferedReader reader = new BufferedReader(new InputStreamReader(temp));
            String customer;
            String singleCall;

            try {

                customer = reader.readLine();
                if (!(customer.matches("[a-zA-Z\\s*'-=!@#$%^&?_1234567890,.]+"))) {
                    return phoneBill;
                }

                PhoneBill bill = new PhoneBill(customer);

                while ((singleCall = reader.readLine()) != null) {
                    String[] split = singleCall.split(",");
                    if (split.length != 4) {
                        return bill;
                    }
                    if (!checkCall(split[0], split[1], split[2], split[3])) {
                      throw new ParserException("The text file is malformatted. Please check the file" +
                                " format and try running again.");
                    }

                    bill.addPhoneCall(new PhoneCall(split[0], split[1], split[2], split[3]));
                }

                phoneBill = bill;

            } catch (ParserException e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }


        } catch (IOException e) {
            throw new ParserException("Error in opening file. Please check the file and try again", e);
        }

        return phoneBill;
    }

    /**
     * Helper function to help validate the incoming arguments from
     * the text file. Return false if the arguments do not match the
     * same requirements for the command line arguments. Return true
     * if the incoming arguments are all well formed.
     */
    public boolean checkCall (String callerNumber, String calleeNumber, String startTime, String endTime) {

        if (!callerNumber.matches("[0-9]{3}[-][0-9]{3}[-][0-9]{4}")) { return false; }
        if (!calleeNumber.matches("[0-9]{3}[-][0-9]{3}[-][0-9]{4}")) { return false; }
        if (!startTime.matches("[0-9][0-9]{0,1}[/][0-9][0-9]{0,1}[/][0-9]{2}([0-9]{2})?[\\s*][0-9][0-9]{0,1}[:][0-5][0-9][\\s*][a-zA-Z]{2}")) { return false; }
        if (!endTime.matches("[0-9][0-9]{0,1}[/][0-9][0-9]{0,1}[/][0-9]{2}([0-9]{2})?[\\s*][0-9][0-9]{0,1}[:][0-5][0-9][\\s*][a-zA-Z]{2}")) { return false; }
        return true;

    }


}



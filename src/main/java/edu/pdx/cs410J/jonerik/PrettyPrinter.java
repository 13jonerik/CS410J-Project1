package edu.pdx.cs410J.jonerik;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by jonerik13 on 7/21/15.
 */
public class PrettyPrinter implements PhoneBillDumper {

    private DataOutputStream call;

    /**
     * Constructor for the PrettyPrinter
     * @param call takes in a stream from
     * Project3 and uses the stream in dump
     */
    public PrettyPrinter(DataOutputStream call) {
        this.call = call;
    }


    @Override
    public void dump(AbstractPhoneBill bill) throws IOException {


        call.writeBytes(String.valueOf(bill.getCustomer()));
        call.writeBytes("\n");

        List<PhoneCall> temp = (List<PhoneCall>) bill.getPhoneCalls();

        int length = temp.size();

        temp = removeDuplicates(temp, length);

        Collections.sort(temp);


        //System.out.println(temp.get(0).getStartTimeString());
        for (AbstractPhoneCall each : temp) {

            String parse = String.valueOf(each);
            System.out.println(parse);
            String[] parseCall = parse.split(" ");
            call.writeBytes(each.getCaller() + "," + each.getCallee() + "," + each.getStartTimeString() +
                    /*parseCall[10].substring(0, 5) + */"," + each.getEndTimeString() + /*" " +  parseCall[17].substring(0, 5) */ "\n");
            //"," + (parseCall[10] + " " + parseCall[11] + "\n")); //.substring(0, (parseCall[11].length() - 1))));
        }

    }

    public List<PhoneCall> removeDuplicates(List<PhoneCall> temp, int length) {
        if (length > 1) {
            for (int i = 0; i < length; i++) {
                for (int j = i + 1; j < length; j++) {
                    if (temp.get(i).compareTo(temp.get(j)) == 0) {
                        temp.remove(temp.get(i));
                        --length;
                        removeDuplicates(temp, length);
                        return temp;
                    }
                }
            }
            return temp;
        } else {
            return temp;
        }
    }




}


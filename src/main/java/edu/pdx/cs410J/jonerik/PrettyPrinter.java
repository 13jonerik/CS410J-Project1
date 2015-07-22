package edu.pdx.cs410J.jonerik;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.lang.String;
import java.util.concurrent.TimeUnit;


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

        String formatString = "%55s";

        call.writeBytes(String.format(formatString, String.valueOf(bill.getCustomer()) + "'s Phone Bill"));
        call.writeBytes("\n");
        call.writeBytes("********************************************************************************************\n");

        List<PhoneCall> temp = (List<PhoneCall>) bill.getPhoneCalls();

        int length = temp.size();

        temp = removeDuplicates(temp, length);

        Collections.sort(temp);

        formatString = "%s\t|\t\t%s\t|\t\t%s\t|\t\t%s%n";

        call.writeBytes(String.format(formatString, "\tCaller\t", "\tCallee\t", "\tStart Time\t", "\tEnd Time\t"));

        for (AbstractPhoneCall each : temp) {

            String parse = String.valueOf(each);

            Long duration = Math.abs(each.getStartTime().getTime() - each.getEndTime().getTime());

            Long durationHours      = TimeUnit.MILLISECONDS.toHours(duration);
            Long durationMins       = TimeUnit.MILLISECONDS.toMinutes(duration);

            durationMins = durationMins % 60;

            System.out.println(duration);

            call.writeBytes(String.format(formatString, "*  " + each.getCaller(), each.getCallee(),
                    each.getStartTimeString() + "\t", each.getEndTimeString() + "\t" +
                    " -> Duration: " + durationHours + " Hours and " + durationMins + " minutes   *"));

        }

        call.writeBytes("************************************************************************************\n");

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


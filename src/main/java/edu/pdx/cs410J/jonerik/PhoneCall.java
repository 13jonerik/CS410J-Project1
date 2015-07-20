package edu.pdx.cs410J.jonerik;

import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.Date;
import java.text.DateFormat;

/**
 * Created by jonerik13 on 7/6/15.
 * The PhoneCall class extends the AbstractPhoneCall
 * interface and adds all functionality. A PhoneCall
 * has a to and from number, and a
 * start and end time.
 */
public class PhoneCall extends AbstractPhoneCall{


    private String callerNumber;
    private String calleeNumber;
    private Date startTime;
    private Date endTime;


    /**
     * Constructor for the PhoneCall
     */
    public PhoneCall(String callerNumber, String calleeNumber,
                     String callTime, String endTime) {


        this.callerNumber   = callerNumber;
        this.calleeNumber   = calleeNumber;
        this.startTime      = formatDate(callTime);
        this.endTime        = formatDate(endTime);

    }

    public String getCaller() {
        return callerNumber;
    }

    public String getCallee() {
        return calleeNumber;
    }

    public Date getStartTime() {
        return getStartTime();
    }

    public String getStartTimeString() {
        return startTime.toString();
    }

    public Date getEndTime() {
        return getEndTime();
    }

    public String getEndTimeString(){
        return endTime.toString();
    }

    /**
     * The toString method is used in the -print argument for Project2
     * file to print the phone call information to the console.
     */
    public String toString() {
        return "Phone call from " + this.getCaller() + " to " + this.getCallee() + " from " + this.getStartTimeString() + " to " + this.getEndTimeString();
    }

    /**
     * Helper function used to save the startTime and
     * endTime as dates, without changing how the dates
     * are read in from the command line. The constructor
     * takes in strings as arguments and this function
     * will format into a date object in the constructor
     */
    public static Date formatDate(String date) {
        DateFormat d = DateFormat.getDateInstance(DateFormat.SHORT);

        try {
            Date myDate = d.parse(date);
            return myDate;
        } catch(java.text.ParseException e) {
            System.err.println("ERROR IN DATE PARSE");
            System.exit(1);
        }
        return null;
    }


}

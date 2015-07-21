package edu.pdx.cs410J.jonerik;

import edu.pdx.cs410J.AbstractPhoneCall;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jonerik13 on 7/6/15.
 * The PhoneCall class extends the AbstractPhoneCall
 * interface and adds all functionality. A PhoneCall
 * has a to and from number, and a
 * start and end time.
 */
public class PhoneCall extends AbstractPhoneCall implements Comparable<PhoneCall> {


    private String callerNumber;
    private String calleeNumber;

    private Date startTime;
    private Date endTime;

    //public Comparator<PhoneCall> phoneCallComparator = Comparator.comparing(PhoneCall::getStartTime)
    //     .thenComparing(PhoneCall::getCaller);


    /**
     * Constructor for the PhoneCall
     */
    public PhoneCall(String callerNumber, String calleeNumber,
                     String callTime, String endTime) {

        this.callerNumber       = callerNumber;
        this.calleeNumber       = calleeNumber;

        this.startTime          = formatDate(callTime);
        this.endTime            = formatDate(endTime);

    }

    public String getCaller() {
        return callerNumber;
    }

    public String getCallee() {
        return calleeNumber;
    }

    public Date getStartTime() {
        return startTime;
    }

    public String getStartTimeString() {
        String shortDate = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(startTime);
        //System.out.println(shortDate);
        return shortDate;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getEndTimeString(){
        String shortDate = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(endTime);
        //System.out.println(shortDate);
        return shortDate;
    }


    /**
     * The toString method is used in the -print argument for Project2
     * file to print the phone call information to the console.
     */
    public String toString() {
        return "Phone call from " + this.getCaller() + " to " + this.getCallee() + " beginning on " + this.getStartTimeString() + " and ending " + this.getEndTimeString();
    }

    /**
     * Helper function used to save the startTime and
     * endTime as dates, without changing how the dates
     * are read in from the command line. The constructor
     * takes in strings as arguments and this function
     * will format into a date object in the constructor
     */
    public static Date formatDate(String d) {

        try {
            Date day = new SimpleDateFormat("MM/dd/yy hh:mm a").parse(d);

            System.out.println(day);

            return day;

        } catch(java.text.ParseException e) {
            System.err.println("Error in formatting date: " + e);
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }


    public int compareTo(PhoneCall other){
        return getStartTime().compareTo(other.getStartTime());

    }



}

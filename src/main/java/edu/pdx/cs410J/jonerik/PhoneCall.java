package edu.pdx.cs410J.jonerik;

import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.Date;

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
    private String startTime;
    private String endTime;


    /**
     * Constructor for the PhoneCall
     */
    public PhoneCall(String callerNumber, String calleeNumber,
                     String callTime, String endTime) {


        this.callerNumber   = callerNumber;
        this.calleeNumber   = calleeNumber;
        this.startTime      = callTime;
        this.endTime        = endTime;

    }

    public String getCaller() {
        return callerNumber;
    }

    public String getCallee() {
        return calleeNumber;
    }

    public Date getStartTime() {
        return null;
    }

    public String getStartTimeString() {
        return startTime;
    }

    public Date getEndTime() {
        return null;
    }

    public String getEndTimeString(){
        return endTime;
    }

    /**
     * The toString method is used in the -print argument for Project1
     * file to print the phone call information to the console.
     */
    public String toString() {
        return "Phone call from " + this.getCaller() + " to " + this.getCallee() + " from " + this.getStartTimeString() + " to " + this.getEndTimeString();
    }

}

package edu.pdx.cs410J.jonerik;

import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.Date;

/**
 * Created by jonerik13 on 7/6/15.
 */
public class PhoneCall extends AbstractPhoneCall{


    private String customer;
    private String callerNumber;
    private String calleeNumber;
    private String startTime;
    private String endTime;

    public PhoneCall(){
        super();
    }

    public PhoneCall(String customer, String callerNumber, String calleeNumber,
                     String callTime, String endTime) {

        this.customer     = customer;
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

    public String toString() {
        return "Phone call from " + this.getCaller() + " to " + this.getCallee() + " from " + this.getStartTimeString() + " to " + this.getEndTimeString();
    }

}

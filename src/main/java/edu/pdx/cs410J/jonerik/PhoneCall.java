package edu.pdx.cs410J.jonerik;

import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.Date;

/**
 * Created by jonerik13 on 7/6/15.
 */
public class PhoneCall extends AbstractPhoneCall{


    private String callerName;
    private String callerNumber;
    private String startTime;

    private String calleeName;
    private String calleeNumber;
    private String endTime;

    public PhoneCall(){
        super();
    }

    public PhoneCall(String callName, String callNumber, String callTime, String answerName,
                     String answerNumber, String answerTime) {

        this.callerName     = callName;
        this.callerNumber   = callNumber;
        this.startTime      = callTime;

        this.calleeName     = answerName;
        this.calleeNumber   = answerNumber;
        this.endTime        = answerTime;

    }

    public String getCaller() {
        return callerName;
    }

    public String getCallee() {
        return calleeName;
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

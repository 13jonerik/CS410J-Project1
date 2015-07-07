package edu.pdx.cs410J.jonerik;

import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.Date;

/**
 * Created by jonerik13 on 7/6/15.
 */
public class PhoneCall extends AbstractPhoneCall{


    public PhoneCall() {
    }

    public String getCaller();

    public String getCallee();

    public Date getStartTime() {
        return null;
    }

    public String getStartTimeString();

    public Date getEndTime() {
        return null;
    }

    public String getEndTimeString();

    public String toString() {
        return "Phone call from " + this.getCaller() + " to " + this.getCallee() + " from " + this.getStartTimeString() + " to " + this.getEndTimeString();
    }

}

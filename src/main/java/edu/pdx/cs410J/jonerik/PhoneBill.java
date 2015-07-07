package edu.pdx.cs410J.jonerik;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.Collection;

/**
 * Created by jonerik13 on 7/6/15.
 */
public class PhoneBill extends AbstractPhoneBill {

    public PhoneBill() {
    }

    public String getCustomer();

    public void addPhoneCall(AbstractPhoneCall var1);

    public Collection getPhoneCalls();

    public String toString() {
        return this.getCustomer() + "\'s phone bill with " + this.getPhoneCalls().size() + " phone calls";
    }


}

package edu.pdx.cs410J.jonerik;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.Collection;

/**
 * Created by jonerik13 on 7/6/15.
 */
public class PhoneBill extends AbstractPhoneBill {

    private String customer;
    private PhoneCall call;
    private Collection <AbstractPhoneCall> calls;



    public PhoneBill(String customer, PhoneCall call) {
        this.customer   = customer;
        this.call       = call;
    }

    public String getCustomer() {
        return customer;
    }

    public void addPhoneCall(AbstractPhoneCall var1) {
        calls.add(var1);
    }

    public Collection getPhoneCalls(){
        return calls;
    }

    public String toString() {
        return this.getCustomer() + "\'s phone bill with " + this.getPhoneCalls().size() + " phone calls";
    }


}

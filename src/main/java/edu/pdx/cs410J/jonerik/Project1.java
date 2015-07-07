package edu.pdx.cs410J.jonerik;

import edu.pdx.cs410J.AbstractPhoneBill;

/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project1 {

  public static void main(String[] args) {
    Class c = AbstractPhoneBill.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath

    // if command line args aren't good, print this and exit gracefully
      //System.err.println("Missing command line arguments");

    PhoneCall incoming   = new PhoneCall(args[0], args[1], args[2], args[3], args[4], args[5]);
    PhoneBill addNewCall = new PhoneBill(args[0], incoming);

    // addNewCall.addPhoneCall(addNewCall)
    // System.out.println(addNewCall.getPhoneCalls());

    for (String arg : args) {
      System.out.println(arg);
    }
    System.exit(1);
  }

}
package edu.pdx.cs410J.jonerik;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.ParserException;

import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;


/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project1 {

    public static void main(String[] args) {
        Class c = AbstractPhoneBill.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath

        ArrayList arguments = new ArrayList<String>(Arrays.asList(args));

        checkZeroArgs(arguments);
        checkForReadMe(arguments);
        checkNumArgs(arguments);
        arguments = checkForFileOption(arguments);
        arguments = checkForPrint(arguments);
        validateCall(arguments);

        //String textFile     = "phoneBill1.txt";
        String customer     = (String) arguments.get(0);
        String callerNumber = (String) arguments.get(1);
        String calleeNumber = (String) arguments.get(2);
        String startTime    = arguments.get(3) + " " + arguments.get(4);
        String endTime      = arguments.get(5) + " " + arguments.get(6);




        PhoneCall firstCall = new PhoneCall(callerNumber, calleeNumber,       // Create the phone call
                startTime, endTime);

        PhoneBill firstBill = new PhoneBill(customer);                       //  Add the first call to a bill
        firstBill.addPhoneCall(firstCall);




        System.exit(0);
    }


    private static void checkZeroArgs(ArrayList args){

        if (args.size() == 0) {
            System.err.println("Missing command line arguments");
            System.exit(1);
        }
    }


    /**
     * checkNumArgs is designed to take the command line arguments and 
     * check to make sure that a valid number of arguments are passed.
     * There are 5 required args and two optional args, however dates
     * are not considered one arg in the command line, they are two.
     * So for this check the range of acceptable CL args is 7 - 9. 
     * If 0 args are passed, or the arguments are less than 7 or greater
     * than 9, the program prints an error and exits with 1. 
     */
    private static void checkNumArgs (ArrayList args) {

        if (args.size() < 7) {
            System.err.println("Missing command line arguments");
            System.exit(1);
        }

        if( args.size() > 11) {
            System.err.println("Too many command line arguments");
            System.exit(1);
        }
    }

    /**
     * Search the command line arguments for an optional arg.
     * The two optional arguments are -README, which prints
     * information about the program to the console, and the 
     * -print arg which validates the command line args and 
     * prints the call info to the console. -README takes
     * precedence over -print.
     */
    private static void checkForReadMe(ArrayList args) {
        String first = (String) args.get(0);
        if (first.startsWith("-")) {
            if (args.contains("-README")) {
                readMe();
                System.exit(0);

            }
        }
    }

    private static ArrayList checkForPrint(ArrayList args) {

        if (args.contains("-print")) {
            args.remove(args.indexOf("-print"));
            if (validateCall(args)) {
                printCall(args);

            }
        }
        return args;
    }


    public static ArrayList checkForFileOption(ArrayList arguments) {

        if (arguments.contains("-textFile")) {
            //validateCall(arguments);




            int i = 1;
            String fileName = (String) arguments.get(i++);




            if (arguments.contains("-print")) {
                i++;
            }

            String customer = (String) arguments.get(i++);
            String callerNumber = (String) arguments.get(i++);
            String calleeNumber = (String) arguments.get(i++);
            String startTime = arguments.get(i++) + " " + arguments.get(i++);
            String endTime = arguments.get(i++) + " " + arguments.get(i);



            /*
            PhoneCall firstCall = new PhoneCall(callerNumber, calleeNumber,       // Create the phone call
                    startTime, endTime);

            PhoneBill firstBill = new PhoneBill(customer);                       //  Add the first call to a bill
            firstBill.addPhoneCall(firstCall);
            */





            arguments.remove(arguments.indexOf("-textFile"));
            arguments.remove(arguments.indexOf(fileName));

            readPhoneBill(fileName);
            return arguments;
        }

        return arguments;

    }



    public static void readPhoneBill(String fileName) {

        DataOutputStream    stream;
        DataInputStream     stream2;

        try {
            stream  = new DataOutputStream(new FileOutputStream("proj2test2.txt"));
            stream2 = new DataInputStream(new FileInputStream(fileName));


            TextDumper dumper = new TextDumper(stream);
            //dumper.dump(firstBill);


            TextParser parser = new TextParser(stream2);
            try {
                AbstractPhoneBill bill = parser.parse();
                System.out.println(bill.toString());
                dumper.dump(bill);

            } catch (ParserException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * validateCall is the important implementation for project 1.
     * This function takes the ArrayList of the command line args
     * and casts the arguments back into strings to be compared to
     * regular expressions. The startTime and endTime args are also
     * concatenated here. The function has a 5 clause if-statement
     * that checks each arg to the expected regular expression,
     * and throws an error if not.
     */
    private static boolean validateCall(ArrayList callInfo){


        String customer     = (String) callInfo.get(0);
        String callerNumber = (String) callInfo.get(1);
        String calleeNumber = (String) callInfo.get(2);
        String startTime    = callInfo.get(3) + " " + callInfo.get(4);
        String endTime      = callInfo.get(5) + " " + callInfo.get(6);

        if (!customer.matches("[a-zA-Z\\s*' - - ! @ # $ % ^ & * ? 1 2 3 4 5 6 7 8 9 0 , .]+")) {         // "[\"][a-zA-Z]+[\"]"
            System.err.println("Customer Name Invalid");
            System.exit(1);
        } else if (!callerNumber.matches("[0-9]{3}[-][0-9]{3}[-][0-9]{4}")) {
            System.err.println("Caller Number Invalid");
            System.exit(1);
        } else if (!calleeNumber.matches("[0-9]{3}[-][0-9]{3}[-][0-9]{4}")) {
            System.err.println("Callee Number Invalid");
            System.exit(1);
        } else if (!startTime.matches("[0-9][0-9]{0,1}[/][0-9][0-9]{0,1}[/][0-9]{4}[\\s*][0-9][0-9]{0,1}[:][0-5][0-9]")) {
            System.err.println("Start Time Invalid");
            System.exit(1);
        } else if (!endTime.matches("[0-9][0-9]{0,1}[/][0-9][0-9]{0,1}[/][0-9]{4}[\\s*][0-9][0-9]{0,1}[:][0-5][0-9]")) {
            System.err.println("End Time Invalid");
            System.exit(1);
        }

        return true;

    }


    /**
     * This function is responsible for printing the README text to the console.
     * Could later move to an external file, but for simplicity its written locally.
     * The function displays the current project version and the current date. This
     * function is called in the checkForOptional function when -README is passed
     * from the command line.
     */
    private static void readMe(){

        DateFormat currDate = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();

        System.out.println("\nREADME:\n\n" +
                            "Phone Bill Application for Project 1 version 1.0. " + currDate.format(date) + "\n\n" +
                            "This application is designed to record a call to a customers phone bill using\n" +
                            "command line arguments to pass necessary information. The main functionality\n" +
                            "in this version is parsing and validating each of the arguments to ensure a valid\n" +
                            "phone call. The order of the arguments and the corresponding types are as follows:\n\n" +
                                "   customer:           Person whose phone bill we’re modeling\n" +
                                "   callerNumber:       Phone number of caller\n" +
                                "   calleeNumber:       Phone number of person who was called\n" +
                                "   startTime:          Date and time call began (24-hour time)\n" +
                                "   endTime:            Date and time call ended (24-hour time)\n" +
                                "   options are (options may appear in any order):\n" +
                                "   -textFile file      where to read/write the phone bill\n" +
                                "   -print:             Prints a description of the new phone call\n" +
                                "   -README:            Prints a README for this project and exits\n" +
                                "   Date and time should be in the format: mm/dd/yyyy hh:mm"
                            );

    }


    /**
     * printCall function turns the ArrayList of command line
     * args back into strings and fixes the startTime and 
     * endTime string into a correctly formed date. Then the 
     * function creates a phoneCall and calls the toString() 
     * method in the PhoneCall class to print info to console. 
     */
    private static void printCall(ArrayList callInfo){

        String callerNumber = (String) callInfo.get(1);
        String calleeNumber = (String) callInfo.get(2);
        String startTime    = callInfo.get(3) + " " + callInfo.get(4);
        String endTime      = callInfo.get(5) + " " + callInfo.get(6);

        PhoneCall firstCall = new PhoneCall(callerNumber, calleeNumber,
                startTime, endTime);

        System.out.println(firstCall.toString());

    }

}

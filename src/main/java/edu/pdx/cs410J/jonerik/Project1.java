package edu.pdx.cs410J.jonerik;

import edu.pdx.cs410J.AbstractPhoneBill;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project1 {

    public static void main(String[] args) {
        Class c = AbstractPhoneBill.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath

        ArrayList<String> arguments = new ArrayList<String>(Arrays.asList(args));

        checkNumArgs(arguments);
        checkForOptional(arguments);
        validateCall(arguments);

        System.exit(0);
    }

    /**
     * checkNumArgs is designed to take the command line arguments and 
     * check to make sure that a valid number of arguments are passed.
     * There are 5 required args and two optional args, however dates
     * are not considered one arg in the command line, they are two.
     * So for this check the range of acceptable CL args is 7 - 9. 
     * If 0 args are passed, or the arguments are less than 7 or greater
     * than 9, the program prints an error and exits with 1. 
     * /
    private static void checkNumArgs (ArrayList args) {

        if (args.size() == 0) {
            System.err.println("Missing command line arguments");
            System.exit(1);
        }

        if (args.size() < 7) {
            System.err.println("Missing command line arguments");
            System.exit(1);
        }

        if( args.size() > 9) {
            System.err.println("Too many command line arguments");
            System.exit(1);
        }
    }


    private static void checkForOptional(ArrayList args) {
//        String first = (String) args.get(0);
//        if (first.startsWith("-")) {
            if (args.contains("-README")) {
                readMe();
                System.exit(0);
            } else if (args.contains("-print")) {
                //check this to make print method work
                args.remove(args.indexOf("-print"));
                if (validateCall(args)) {
                    printCall(args);
                    System.exit(0);
                }
           // } else {
                //exitWithError();
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

        if (!customer.matches("[a-zA-Z\\s*'-]+")) {         // "[\"][a-zA-Z]+[\"]"
            System.err.println("Customer Name Invalid");
            System.exit(1);
        } else if (!callerNumber.matches("[0-9]{3}[-][0-9]{3}[-][0-9]{4}")) {
            System.err.println("Caller Number Invalid");
            System.exit(1);
        } else if (!calleeNumber.matches("[0-9]{3}[-][0-9]{3}[-][0-9]{4}")) {
            System.err.println("Callee Number Invalid");
            System.exit(1);
        } else if (!startTime.matches("[0-3][0-9]{0,1}[/][0-9][0-9]{0,1}[/][0-9]{4}[\\s*][0-9][0-9]{0,1}[:][0-5][0-9]")) {
            System.err.println("Start Time Invalid");
            System.exit(1);
        } else if (!endTime.matches("[0-3][0-9]{0,1}[/][0-9][0-9]{0,1}[/][0-9]{4}[\\s*][0-9][0-9]{0,1}[:][0-5][0-9]")) {
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
                                "   -print:             Prints a description of the new phone call\n" +
                                "   -README:            Prints a README for this project and exits\n" +
                                "   Date and time should be in the format: mm/dd/yyyy hh:mm"
                            );

    }


    private static void printCall(ArrayList callInfo){
        //System.out.println("\nCall from " + callInfo.get(1) + " at " + callInfo.get(2) + "\n" +
        //                            "Received by " + callInfo.get(3) + "\n" +
        //                            "Duration: " + callInfo.get(4) + " - " + callInfo.get(5) + "\n");


        String customer     = (String) callInfo.get(0);
        String callerNumber = (String) callInfo.get(1);
        String calleeNumber = (String) callInfo.get(2);
        String startTime    = callInfo.get(3) + " " + callInfo.get(4);
        String endTime      = callInfo.get(5) + " " + callInfo.get(6);

        PhoneCall firstCall = new PhoneCall(customer, callerNumber, calleeNumber,
                startTime, endTime);

        System.out.println(firstCall.toString());

    }


    private static void exitWithError(){
        System.err.println("Missing command line arguments");
        System.exit(1);
    }



}

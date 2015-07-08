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


        for (String arg : args) {
            System.out.println(arg);
        }
        System.exit(1);
    }

    private static void checkNumArgs (ArrayList args) {
        if (args.size() < 5 || args.size() > 7) {
            exitWithError();
            System.exit(0);
        }
    }


    private static void checkForOptional(ArrayList args) {
        String first = (String) args.get(0);
        if (first.startsWith("-")) {
            if (args.contains("-README")) {
                readMe();
                System.exit(1);
            } else if (first.equals("-print")) {
                printCall(args);
                System.exit(1);
            } else {
                exitWithError();
                System.exit(0);
            }
        }
    }


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
        System.out.println("\nCall from " + callInfo.get(1) + " at " + callInfo.get(2) + "\n" +
                                    "Received by " + callInfo.get(3) + "\n" +
                                    "Duration: " + callInfo.get(4) + " - " + callInfo.get(5) + "\n");
    }


    private static void validateCall(ArrayList callInfo){
        String customer = (String) callInfo.get(0);
        System.out.println(customer);

        if (customer.matches("[a-zA-Z\\s*'-]+")){         // "[\"][a-zA-Z]+[\"]"
            System.out.println("SUCCESS");

        }



    }







    private static void exitWithError(){
        System.err.println("Missing command line arguments");
    }






}
package edu.pdx.cs410J.jonerik;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * The main class for the CS410J Phone Bill Project
 *
 * This iteration of the project allows the user to pass in
 * a file name with a phoneBill, and then add another call
 * via the command line to the bill. The required format of
 * the text file is:
 * customer name
 * callerNumber,calleeNumber,startTime,endTime
 * callerNumber,calleeNumber,startTime,endTime
 *
 * etc.
 *
 */


public class Project2 {

    static String prettyFile   = "";
    static boolean prettify    = false;

    public static void main(String[] args) {
        Class c = AbstractPhoneBill.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath


        ArrayList arguments = new ArrayList<String>(Arrays.asList(args));
        if (arguments.contains("-pretty")) {
            prettyFile  = (String) arguments.get(1);
            prettify    = true;
            arguments.remove(0);
            arguments.remove(0);
        }


        checkZeroArgs(arguments);
        checkForReadMe(arguments);
        checkNumArgs(arguments);
        arguments = checkForFileOption(arguments);
        arguments = checkForPrint(arguments);

        System.exit(0);
    }


    /**
     * Check to see if the user forgot to enter commandline args
     */
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

        if (args.size() < 9) {
            System.err.println("Missing command line arguments");
            System.exit(1);
        }

        if( args.size() > 13) {
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

    /**
     * Check for the print command in the arrayList of args
     * @param args
     * @return
     */
    private static ArrayList checkForPrint(ArrayList args) {

        if (args.contains("-print")) {
            args.remove(args.indexOf("-print"));
            if (validateCall(args)) {
                printCall(args);

            }
        }
        return args;
    }


    /**
     * If the file option is in the command line arguments, create
     * a call and pass that call, along with the file name to
     * the readPhoneBill method. Take careful note if a -print
     * argument also exists within the arguments.
     */
    public static ArrayList checkForFileOption(ArrayList arguments) {

        if (arguments.contains("-textFile")) {

            int i = 1;
            String fileName = (String) arguments.get(i++);

            if (arguments.contains("-print")) {
                ++i;
            }

            String customer = (String) arguments.get(i++);
            String callerNumber = (String) arguments.get(i++);
            String calleeNumber = (String) arguments.get(i++);
            String startTime = arguments.get(i++) + " " + arguments.get(i++) + " " + arguments.get(i++);
            String endTime = arguments.get(i++) + " " + arguments.get(i++) + " " + arguments.get(i);


            PhoneCall firstCall = new PhoneCall(callerNumber, calleeNumber,       // Create the phone call
                    startTime, endTime);


            arguments.remove(arguments.indexOf("-textFile"));
            arguments.remove(arguments.indexOf(fileName));

            // validate args without throwing whole program
            validateCall(arguments);

            readPhoneBillFile(fileName, firstCall, arguments);

            return arguments;
        }

        return arguments;

    }


    /**
     * This function is responsible for checking if a file exists and then executing
     * the parseAndDump function. If a file is not found, the program is not
     * interrupted but instead creates a new file and writes a new phoneBill
     * to the empty file.
     */
    public static void readPhoneBillFile(String fileName, PhoneCall addCall, ArrayList args) {

        int i = 0;
        if(args.contains("-print")) { ++i; }

        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            if (file.length() <= 0) {
                writeToEmptyFile(file, fileName, addCall, args.get(i).toString());
                return;
            }

            parseAndDump(fileName, addCall, args.get(i).toString());

        } catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Open a text file with the fileName from the command line and add the
     * customer name and call from the file to a phoneBill on success. Make
     * sure that the name on the phoneBill matches the name from the CL and
     * then dump the contents of the file, along with the new call back to
     * the given file.
     * @param fileName
     * @param addCall
     * @param customer
     */
    public static void parseAndDump(String fileName, PhoneCall addCall, String customer) {

        DataOutputStream    stream;
        DataInputStream     stream2;
        DataOutputStream    prettyPrintStream;

        try {

            stream2 = new DataInputStream(new FileInputStream(fileName));
            TextParser parser = new TextParser(stream2);
            AbstractPhoneBill bill;

            try {

                bill = parser.parse();
                stream2.close();

                bill.addPhoneCall(addCall);

                if (!bill.getCustomer().equals(customer)) {
                    throw new ParserException("The name on the phoneBill and the name" +
                            " passed into the program don't match. Please check the file" +
                            " and try running again. ");
                }

                stream = new DataOutputStream(new FileOutputStream(fileName));
                TextDumper dumper = new TextDumper(stream);
                dumper.dump(bill);
                stream.close();

                if (prettify) {
                    prettyPrintStream = new DataOutputStream(new FileOutputStream(prettyFile));
                    PrettyPrinter printer = new PrettyPrinter((prettyPrintStream));
                    printer.dump(bill);
                    prettyPrintStream.close();
                }

            } catch (ParserException e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }

        } catch (IOException e) {
            System.err.println("Error in opening file: " + e);
        }


    }

    /**
     * If the file either does not exist, or is empty, use this function to
     * create a file and dump the command line call into the file, along
     * with the name in the correct format.
     * @param file
     * @param fileName
     * @param addCall
     * @param args
     */
    public static void writeToEmptyFile(File file, String fileName, PhoneCall addCall, String args) {

        PhoneBill newBill = new PhoneBill(args);

        DataOutputStream stream;
        DataOutputStream prettyPrintStream;

        try {
            try {
                file.createNewFile();

                newBill.addPhoneCall(addCall);


                stream = new DataOutputStream(new FileOutputStream(fileName));
                TextDumper dumper = new TextDumper(stream);
                dumper.dump(newBill);
                stream.close();

                if (prettify) {
                    prettyPrintStream = new DataOutputStream(new FileOutputStream(prettyFile));
                    PrettyPrinter printer = new PrettyPrinter((prettyPrintStream));
                    printer.dump(newBill);
                    prettyPrintStream.close();
                }

            } catch (IOException e) {
                throw new ParserException("Error in writing to empty file.", e);
            }
        } catch (ParserException e) {
            e.getMessage();
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

        int i = 0;
        if (callInfo.contains("-print")) {
            //callInfo.remove(callInfo.indexOf("-print"));
            i += 1;
        }

        String customer     = (String) callInfo.get(i++);
        String callerNumber = (String) callInfo.get(i++);
        String calleeNumber = (String) callInfo.get(i++);
        String startTime    = callInfo.get(i++) + " " + callInfo.get(i++) + " " + callInfo.get(i++);
        String endTime      = callInfo.get(i++) + " " + callInfo.get(i++) + " " + callInfo.get(i);

        if (!customer.matches("[a-zA-Z\\s*' - - ! @ # $ % ^ & * ? 1 2 3 4 5 6 7 8 9 0 , .]+")) {         // "[\"][a-zA-Z]+[\"]"
            System.err.println("Customer Name Invalid");
            System.exit(1);
        } else if (!callerNumber.matches("[0-9]{3}[-][0-9]{3}[-][0-9]{4}")) {
            System.err.println("Caller Number Invalid");
            System.exit(1);
        } else if (!calleeNumber.matches("[0-9]{3}[-][0-9]{3}[-][0-9]{4}")) {
            System.err.println("Callee Number Invalid");
            System.exit(1);
        } else if (!startTime.matches("[0-9][0-9]{0,1}[/][0-9][0-9]{0,1}[/][0-9]{2}([0-9]{2})?[\\s*][0-9][0-9]{0,1}[:][0-5][0-9][\\s*][a-zA-Z]{2}")) {
            System.err.println("Start Time Invalid");
            System.exit(1);
        } else if (!endTime.matches("[0-9][0-9]{0,1}[/][0-9][0-9]{0,1}[/][0-9]{2}([0-9]{2})?[\\s*][0-9][0-9]{0,1}[:][0-5][0-9][\\s*][a-zA-Z]{2}")) {
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
                            "Jon-Erik Svenson\n" +
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

        int i = 1;
        if (callInfo.contains("-fileName")){
            i += 2;
        }

        String callerNumber = (String) callInfo.get(i++);
        String calleeNumber = (String) callInfo.get(i++);
        String startTime    = callInfo.get(i++) + " " + callInfo.get(i++) + " " + callInfo.get(i++);
        String endTime      = callInfo.get(i++) + " " + callInfo.get(i++) + " " + callInfo.get(i);

        PhoneCall firstCall = new PhoneCall(callerNumber, calleeNumber,
                startTime, endTime);

        System.out.println(firstCall.toString());

    }

}

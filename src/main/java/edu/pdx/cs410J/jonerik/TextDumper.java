package edu.pdx.cs410J.jonerik;


import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


/**
 * The text dumper class has a DataOutputStream. It is
 * responsible for taking in a phoneBill, and then writing
 * the contents of that phoneBill to the file.
 *
 */
public class TextDumper implements PhoneBillDumper {

    private DataOutputStream call;

    /**
     * Constructor for the TextDumper
     * @param call
     */
    public TextDumper(DataOutputStream call) {
        this.call = call;
    }


    /**
     * The dump method first writes the phoneBill customer name
     * to the file, and then splits the phoneCalls, which is
     * a collection maintained by the phoneBill class into a
     * new list. It then iterates through the calls and dumps
     * them to the file in the correct format.
     * @param var1
     * @throws IOException
     */
    public void dump(AbstractPhoneBill var1) throws IOException {

        call.writeBytes(String.valueOf(var1.getCustomer()));
        call.writeBytes("\n");

        List <PhoneCall> temp = (List<PhoneCall>) var1.getPhoneCalls();

        int length = temp.size();

        temp = removeDuplicates(temp, length);

        Collections.sort(temp);




        // remove duplicate calls from PhoneBill
        //List <PhoneCall> removeDup = new ArrayList();


        //System.out.println(temp.get(0).getStartTimeString());
        for ( AbstractPhoneCall each : temp) {

            String parse = String.valueOf(each);
            System.out.println(parse);
            String[] parseCall = parse.split(" ");
            call.writeBytes(each.getCaller() + "," + each.getCallee() + "," + each.getStartTimeString() +
                    /*parseCall[10].substring(0, 5) + */"," + each.getEndTimeString() + /*" " +  parseCall[17].substring(0, 5) */ "\n");
                    //"," + (parseCall[10] + " " + parseCall[11] + "\n")); //.substring(0, (parseCall[11].length() - 1))));
        }

    }


    public List<PhoneCall> removeDuplicates(List<PhoneCall> temp, int length) {
      if (length > 1) {
          for (int i = 0; i < length; i++) {
              for (int j = i + 1; j < length; j++) {
                  if (temp.get(i).compareTo(temp.get(j)) == 0) {
                      temp.remove(temp.get(i));
                      --length;
                      removeDuplicates(temp, length);
                      return temp;
                  }
              }
          }
          return temp;
      } else {
          return temp;
      }
    }



}

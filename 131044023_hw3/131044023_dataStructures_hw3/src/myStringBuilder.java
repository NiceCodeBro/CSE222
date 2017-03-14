import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

/**
 * Created by MSD on 8.03.2017.
 * myStringBuilder class which works like the StringBuilder class.
 * class  have:
 * A single list, 3 to string method and append methods that exist in StringBuilder.
 */
public class myStringBuilder{

    private mySingleLinkedList myLinkedList ;

    /**
     * no parameter constructor
     */
    public myStringBuilder()
    {
        myLinkedList = new mySingleLinkedList<>();
    }


    /**
     * Append to the linked list boolean. true or false
     * @param b, true or false
     * @return
     */
    public myStringBuilder append(boolean b)
    {
        if( b == true)
            myLinkedList.add("true");
        else
            myLinkedList.add("false");
        return this;
    }

    /**
     * Append to the linked list char array between offset and len
     * @param str
     * @param offset
     * @param len
     * @return
     */
    public myStringBuilder  append(char[] str, int offset, int len)
    {
        String tempString = "";
        if(str.length > len && offset < len)
        {
            for (int i = offset ; i < str.length ; ++i)
                tempString = tempString + str[i];
            myLinkedList.add(tempString);
        }
        else
            throw new IndexOutOfBoundsException ();

        return this;

    }

    /**
     * Append to the linked list CharSequence
     * @param s
     * @return
     */
    public myStringBuilder append(CharSequence s)
    {
        if (s == null)
            s = "null";
        myLinkedList.add((String) s);
        return this;
    }

    /**
     * Append to the linked list CharSequence between offset and len
     * @param s
     * @param start
     * @param end
     * @return
     */
    public myStringBuilder append(CharSequence s, int start, int end)
    {
        if (s == null)
            s = "null";
        if ((start < 0) || (end < 0) || (start > end) || (end > s.length()))
            throw new IndexOutOfBoundsException( "start " + start + ", end " + end + ", s.length() " + s.length());
        int len = end - start;
        if (len == 0)  return this;
        String tempString = "";
        for (int i = start; i < end; i++)
            tempString =  tempString + s.charAt(i);
        myLinkedList.add(tempString);

        return  this;
    }

    /**
     * Append char to the linked list
     * @param c
     * @return
     */
    public myStringBuilder append(char c)
    {
        myLinkedList.add("" + c);
        return this;
    }

    /**
     * Append double to the linked list
     * @param d
     * @return
     */
    public myStringBuilder append(double d)
    {
        myLinkedList.add("" + d );
        return this;
    }
    /**
     * Append float to the linked list
     * @param f
     * @return
     */
    public myStringBuilder append(float f)
    {
        myLinkedList.add("" + f );
        return this;
    }
    /**
     * Append long int to the linked list
     * @param lng
     * @return
     */
    public myStringBuilder append(long lng)
    {
        myLinkedList.add("" + lng);
        return this;
    }

    /**
     * Append StringBuffer to the linked list
     * @param sb
     * @return
     */
    public myStringBuilder append(StringBuffer sb)
    {
        String tempString = "";
        for (int i=0; i < sb.length(); i++)
            tempString =  tempString + sb.charAt(i);
        myLinkedList.add(tempString);

        return this;
    }

    /**
     * Append integer to the linked list
     * @param i
     * @return
     */
    public myStringBuilder append(int i)
    {
        myLinkedList.add("" + i);
        return this;
    }

    /**
     * Append String to the linked list
     * @param s
     * @return
     */
    public myStringBuilder append(String s)
    {
        myLinkedList.add(s);
        return this;
    }

    /**
     * Append object to the linked list
     * @param obj
     * @return
     */
    public myStringBuilder append(Object obj)
    {
        myLinkedList.add(obj.toString());
        return this;
    }

    /**
     * Append char array to the linked list
     * @param str
     * @return
     */
    public myStringBuilder append(char[] str)
    {
        String tempString = "";

        for(int i = 0; i < str.length; ++i)
        {
            tempString = tempString + str[i];
        }
        myLinkedList.add(tempString);
        return this;
    }

    /**
     * To string method that uses linked list's get method
     * @return tempString
     */
    public String toString1()
    {
        String tempString = "";
        for(int i = 0; i < myLinkedList.getSize(); ++i)
        {
            tempString = tempString + myLinkedList.get(i).getElement() + "->";
        }
        return tempString;
    }

    /**
     * To string method that uses iterator that defined on linked list
     * @return tempString
     */
    public String toString2()
    {
        String tempString = "";
        if(myLinkedList.getSize() > 0)
        {
            Iterator iter = myLinkedList.iterator();

            while (iter.hasNext())
            {
                tempString = tempString + iter.next() + "->";

            }
        }
        return tempString;
    }

    /**
     * To string method that calls the linked list's to string method.
     * @return myLinkedList.toString()
     */
    public String toString3()
    {
        return myLinkedList.toString();
    }

    /**
     * Method fill the file with 100.000 random number
     * @param fileNameToFillNumber
     */
    public void fillFileFileWithRandomNumbers(String fileNameToFillNumber)
    {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileNameToFillNumber));
            Random rand = new Random();
            int number = 0;
            while (number < 100000)
            {
                ++number;
                int tempNumber = rand.nextInt(75);
                out.write(""+ tempNumber);
                if(number < 100000) out.newLine();
            }
            out.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Method read file and fill the linked list with append method
     * @param fileNameToReadFromFile
     */
    public void fillTheLinkedListFromFile(String fileNameToReadFromFile)
    {
        try {
            File file = new File(fileNameToReadFromFile);
            Scanner input  = new Scanner(file);

            while (input.hasNextLine()) {
                String line = input.nextLine();
                append(line);
            }
            input.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Main method for testing.
     • Will read 100.000 integers from the numbers.txt file
     • Uses myStringBuilder to create a string using 3 different toString methods
     • Prints their output to reult1.txt, result2.txt and result3.txt files
     • Analyzes and compares performances of 3 toString methods
     * @param args
     */
    public static final void main(String[] args)
    {
        long startTime, endTime, seconds;
        final String numberFileName = "numbers.txt";
        final String outputFileName1 = "result1.txt";
        final String outputFileName2 = "result2.txt";
        final String outputFileName3 = "result3.txt";
        myStringBuilder stringBuilder = new myStringBuilder();
        stringBuilder.fillFileFileWithRandomNumbers(numberFileName);
        stringBuilder.fillTheLinkedListFromFile(numberFileName);
        System.out.println("100.000 random numbers filled to file and read all and filled linkedlist.\n");
        System.out.println("Test started for the first to string method.");
        try {

            BufferedWriter out1 = new BufferedWriter(new FileWriter(outputFileName1));
            BufferedWriter out2 = new BufferedWriter(new FileWriter(outputFileName2));
            BufferedWriter out3 = new BufferedWriter(new FileWriter(outputFileName3));

            startTime = System.currentTimeMillis();
            out1.write( stringBuilder.toString1() );
            endTime = System.currentTimeMillis();
            seconds = (int)(endTime - startTime)/1000; // milisecond to second
            System.out.printf("The first to string method that uses indexes and get method took %d seconds to work\n\n", seconds);
            out1.close();

            System.out.println("Test started for the second to string method.");
            startTime = System.currentTimeMillis();
            out2.write( stringBuilder.toString2() );
            endTime = System.currentTimeMillis();
            seconds = (int)(endTime - startTime)/1000; // milisecond to second
            System.out.printf("The first to string method that uses indexes and get method took %d seconds to work\n\n", seconds);
            out2.close();


            System.out.println("Test started for the third to string method.");
            startTime = System.currentTimeMillis();
            out3.write( stringBuilder.toString3() );
            endTime = System.currentTimeMillis();
            seconds = (int)(endTime - startTime)/1000; // milisecond to second
            System.out.printf("The first to string method that uses indexes and get method took %d seconds to work\n\n", seconds);
            out3.close();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}

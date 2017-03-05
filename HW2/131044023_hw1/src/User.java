import java.io.*;
import java.util.Scanner;

/**
 * Created by MSD on 22.02.2017.
 * User class
 * This class has a few methods for manupulating persons
 *
 */
public class User extends PersonAbstract{

    /**
     * protected void printOperationsHistory()
     * this is a abstract methods for polimorfic calls
     * when print operation history, print only user logs
     */
    @Override
    protected void printOperationsHistory() {
        for (int i = 0; i < getOperationHistory().size(); ++i)
        {
            System.out.println(getOperationHistory().get(i));
        }
    }

    /**
     * when user return book, this methods calling
     * first control that the book is not available or not
     * secondly control that the book barrowed before
     * and then add operation with operation name
     * @param bookName
     * @param bookStatus
     * @return
     */
    public String returnBook(String bookName,String bookStatus)
    {
        if(bookStatus.equals("false"))
        {
            for (int i = 0; i < super.getOperationHistory().size(); ++i)
            {
                String []tempString = super.getOperationHistory().get(i).toString().split("_");
                String tempString2 =  tempString[2] + "_" + tempString[3];
                if( tempString2.equals("Barrowed" + "_" + bookName))
                {
                    super.getOperationHistory().add(bookReturning(bookName));
                    return "true";
                }
            }
        }
        return "false";
    }

    /**
     * read logs file and fill logsArray (only member logs)
     * @param allLogsFileName
     */
    @Override
    public void allLogsToArrHistoryList(String allLogsFileName) {
        try {
            File file = new File(allLogsFileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String []tempString = line.split(",");
                String []tempString2 = tempString[0].split("_");
                if(tempString2[0].equals("member") && tempString2[1].equals(super.getUsername()))
                    operationHistory.add(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Logs File does not exist.");
        }
    }

    /**
     * write all logs to file from logsArray
     * @param allLogFileName
     * @throws FileNotFoundException
     */
    public void writeBackToLogFile(String allLogFileName) throws FileNotFoundException {
        String tempOperationLogs = "";
        int numberOfUserLog=0;
        Scanner scanner = new Scanner(new File(allLogFileName));

        while(scanner.hasNextLine()){
            String tempString = scanner.nextLine();
            String [] tempString1 = tempString.split(",");
            tempOperationLogs = tempOperationLogs + tempString1[0] + "\n" ;
            String [] tempString2 = tempString1[0].split("_");
            if(tempString2[0].equals("member"))
                ++numberOfUserLog;
        }
        scanner.close();

        try {
            File file = new File(allLogFileName);
            Writer writer = new PrintWriter(file);

            try {
                for (int i = numberOfUserLog; i < super.getOperationHistory().size(); ++i) {
                    tempOperationLogs = tempOperationLogs + super.getOperationHistory().get(i) + ",\n";
                }

                writer.write(tempOperationLogs);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Log file does not exist.");
        }
    }

    /**
     * bookReturningToString(String bookName)
     * return string that will write to log file when book returning
     * @param bookName
     * @return
     */
    public String bookReturning(String bookName)
    {
        return super.getLoginType()+"_"+ super.getUsername() +"_Returned_" + bookName ;
    }
    /**
     * bookBarrowing(String bookName)
     * return string that will write to log file when book barrowing
     * @param bookName
     * @return
     */
    public String bookBarrowing(String bookName)
    {
        return  super.getLoginType()+"_"+ super.getUsername() +"_Barrowed_" + bookName ;
    }
    /**
     * toString()
     * return string for write to logs message
     * @return
     */
    @Override
    public String toString() {
        return "member," + super.getUsername() + "," + super.getPassword();
    }
}


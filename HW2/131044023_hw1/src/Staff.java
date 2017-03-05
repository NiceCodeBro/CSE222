import java.io.*;
import java.util.Scanner;

/**
 * Created by MSD on 22.02.2017.
 * Staff class
 * This class has a few methods for manupulating persons
 */
public class Staff extends PersonAbstract {

    /**
     * protected void printOperationsHistory()
     * this is a abstract methods
     * when print operation history, print only staff logs
     */
    @Override
    protected void printOperationsHistory() {
        for (int i = 0; i < getOperationHistory().size(); ++i)
        {
            System.out.println(getOperationHistory().get(i));
        }
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
                operationHistory.add(tempString[0]);
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
        Scanner scanner = new Scanner(new File(allLogFileName));
        int numberOfUserLog=0;

        while(scanner.hasNextLine()){
            String tempString = scanner.nextLine();
            String [] tempString1 = tempString.split(",");
            tempOperationLogs = tempOperationLogs + tempString1[0] + "\n" ;
            String [] tempString2 = tempString1[0].split("_");
            if(tempString2[0].equals("member") || tempString2[0].equals("root"))
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
     * addNewUserBookToString(String newUser)
     * return string that will write to log file when root add new user
     * @param newUser
     * @return
     */
    public String addNewUserBookToString(String newUser)
    {
        return "root_" + super.getUsername() +"_" + "registered user named " + newUser;
    }

    /**
     * removeBookToString(String bookName)
     * return string that will write to log file when root removed book
     * @param bookName
     * @return
     */
    public String removeBookToString(String bookName)
    {
        return "root_" + super.getUsername() +"_book(" + bookName +") removed";
    }

    /**
     * addNewBookToString(String bookName)
     * return string that will write to log file when root add new book
     * @param bookName
     * @return
     */
    public String addNewBookToString(String bookName)
    {
        return "root_" + super.getUsername() +"_new book(" + bookName +") added";
    }

    /**
     * toString()
     * return string for write to logs message
     * @return
     */
    @Override
    public String toString() {
        return "root," + super.getUsername() + "," + super.getPassword();
    }
}

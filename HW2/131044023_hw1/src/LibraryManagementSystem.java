import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by MSD on 22.02.2017.
 * keep al record and manipulate method
 */
public class LibraryManagementSystem {
    private ArrayList<User> userArrayList;
    private ArrayList<Staff> staffArrayList;
    private ArrayList<Book> booksArrayList;
    private LinkedList<Book> bookLinkedList ; //linkled list structure update
    private Book[] bookArray;
    private int numberOfBooks;
    private int counterOfAvailableBooks;
    private int userIndexInArrayList, staffIndexInArrayList;

    /**
     * Constructor LibraryManagementSystem()
     * initializing members and allocating place from memory
     */
    LibraryManagementSystem()
    {
        userArrayList = new ArrayList<User>();
        staffArrayList = new ArrayList<Staff>();
        booksArrayList = new ArrayList<Book>();
        bookLinkedList = new LinkedList<>(); //linkled list structure update
        this.numberOfBooks = 0;
        this.counterOfAvailableBooks = 0;
        this.userIndexInArrayList = 0;
        this.staffIndexInArrayList = 0;
    }

    /**
     *  getNumberOfBooks()
     * @return numberOfBooks
     */
    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    /**
     * setNumberOfBooks(int numberOfBooks)
     * @param numberOfBooks
     */
    public void setNumberOfBooks(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }
    /**
     * getUserIndexInArrayList(
     * @return userIndexInArrayList
     */
    public int getUserIndexInArrayList() {        return userIndexInArrayList;    }

    /**
     * setUserIndexInArrayList(int userIndexInArrayList)
     * @param userIndexInArrayList
     */
    public void setUserIndexInArrayList(int userIndexInArrayList) {   this.userIndexInArrayList = userIndexInArrayList;    }

    /**
     * getStaffIndexInArrayList()
     * @return staffIndexInArrayList
     */
    public int getStaffIndexInArrayList() {        return staffIndexInArrayList;    }

    /**
     * setStaffIndexInArrayList(int staffIndexInArrayList)
     * @param staffIndexInArrayList
     */
    public void setStaffIndexInArrayList(int staffIndexInArrayList) {   this.staffIndexInArrayList = staffIndexInArrayList;   }

    /**
     * getCounterOfAvailableBooks()
     * @return counterOfAvailableBooks
     */
    public int getCounterOfAvailableBooks() {        return counterOfAvailableBooks;    }

    /**
     * getUserArrayList()
     * @return userArrayList
     */
    public ArrayList<User> getUserArrayList() {        return userArrayList;    }

    /**
     *  getStaffArrayList()
     * @return staffArrayList
     */
    public ArrayList<Staff> getStaffArrayList() {        return staffArrayList;    }

    /**
     * getBooksArrayList()
     * @return booksArrayList
     */
    public ArrayList<Book> getBooksArrayList() {        return booksArrayList;    }

    /**
     * getStatusOfBook(String bookName)
     * @param bookName
     * @return bookStatus
     */
    public String getStatusOfBook(String bookName)
    {
        for(int i = 0; i < this.getBooksArrayList().size(); ++i)
        {
            if(bookName.equals(this.getBooksArrayList().get(i).getBookName()))
                return this.getBooksArrayList().get(i).getBookStatus();
        }
        return "-1";
    }

    /**
     * getIndexOfBook(String bookName)
     * @param bookName
     * @return
     */
    public int getIndexOfBook(String bookName)
    {
        for(int i = 0; i < this.getBooksArrayList().size(); ++i)
        {
            if(bookName.equals(this.getBooksArrayList().get(i).getBookName()))
                return i;
        }
        return -1;
    }

    /**
     * determineUserOrStaff(String nameSurname, String password)
     * return 0 if only login is user
     * return 1 if only login is staff
     * return -1 if only login is not user or staff
     * @param nameSurname
     * @param password
     * @return
     */
    public int determineUserOrStaff(String nameSurname, String password)
    {
        for(int i = 0; i < this.getUserArrayList().size(); ++i)
        {
            if(nameSurname.equals(this.getUserArrayList().get(i).getUsername()) &&
                    password.equals(this.getUserArrayList().get(i).getPassword()) )
            {
                this.setUserIndexInArrayList(i);
                return 0;
            }
        }
        for(int i = 0; i < this.getStaffArrayList().size(); ++i)
        {
            if(nameSurname.equals(this.getStaffArrayList().get(i).getUsername()) &&
                    password.equals(this.getStaffArrayList().get(i).getPassword()) )
            {
                this.setStaffIndexInArrayList(i);
                return 1;
            }
        }
        return -1;
    }

    /**
     * usernameIsUnique(String userName)
     * if staff add new user, this method check if username is unique or not
     * @param userName
     * @return
     */
    public boolean usernameIsUnique(String userName)
    {
        for(int i = 0; i < this.getUserArrayList().size(); ++i)
        {
            if(this.getUserArrayList().get(i).getUsername().equals(userName))
                return false;
        }
        return true;
    }

    /**
     * rewriteRegisteredMembersLogsToFile(String registeredMembersFileName)
     * write to file that all registered members
     * @param registeredMembersFileName
     */
    public void rewriteRegisteredMembersLogsToFile(String registeredMembersFileName) {
        String tempUserLogs = "";
        try {
            File file = new File(registeredMembersFileName);
            Writer writer = new PrintWriter(file);
            tempUserLogs = tempUserLogs + this.getStaffArrayList().get(0).toString()+ "\n";
            try {
                for (int i = 0; i < this.getUserArrayList().size(); ++i) {
                    String tempString = this.getUserArrayList().get(i).toString();
                    tempUserLogs = tempUserLogs + tempString + "\n";
                }
                writer.write(tempUserLogs);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Book information file does not exist.");
        }
    }

    /**
     * rewriteBookLogsToFile(String bookInformationsFileName)
     * write to file that all book logs
     * @param bookInformationsFileName
     */
    public void rewriteBookLogsToFile(String bookInformationsFileName)
    {
        String tempBookLogs = "";
        try {
            File file = new File(bookInformationsFileName);
            Writer writer = new PrintWriter(file);

            try {
                for (int i = 0; i < this.getBooksArrayList().size(); ++i) {
                    tempBookLogs = tempBookLogs + this.getBooksArrayList().get(i).writeFileBack() + "\n";
                }
                writer.write(tempBookLogs);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Book information file does not exist.");
        }
    }


    /**
     * bookSearch(String bookName)
     * using array list
     * this methods search in the book logs array list, and print some message to matching
     * @param bookName
     */
    public void bookSearch(String bookName)
    {
        boolean flag = false;
        for(int i = 0; i < this.getNumberOfBooks() && !flag; ++i)
        {
            if(this.booksArrayList.get(i).getBookName().toLowerCase().equals(bookName.toLowerCase()))
            {
                if(this.booksArrayList.get(i).getBookStatus().equals("true"))
                    System.out.printf("The book is available named '%s'.\n",bookName);
                else if(this.booksArrayList.get(i).getBookStatus().equals("false"))
                    System.out.printf("The book is not available named '%s'.\n",bookName);

                return;
            }
        }
        System.out.printf("There is no book in our library system named '%s'.\n", bookName);
        return;
    }



    /**
     * bookSearch(String bookName)
     * using linked list
     * this methods search in the book logs link list, and print some message to matching
     * @param bookName
     */
    public void bookSearchWithLinkedList(String bookName)
    {
        boolean flag = false;
        for(int i = 0; i < this.getNumberOfBooks() && !flag; ++i)
        {
            if(this.bookLinkedList.get(i).getBookName().toLowerCase().equals(bookName.toLowerCase()))
            {
                if(this.bookLinkedList.get(i).getBookStatus().equals("true"))
                    System.out.printf("The book is available named '%s'.\n",bookName);
                else if(this.bookLinkedList.get(i).getBookStatus().equals("false"))
                    System.out.printf("The book is not available named '%s'.\n",bookName);
                return;
            }
        }
        System.out.printf("There is no book in our library system named '%s'.\n", bookName);
        return;
    }

    /**
     * bookSearch(String bookName)
     * using array
     * this methods search in the book logs array, and print some message to matching
     * @param bookName
     */
    public void bookSearchWithArray(String bookName)
    {
        boolean flag = false;
        for(int i = 0; i < this.getNumberOfBooks() && !flag; ++i)
        {
            if(this.bookArray[i].getBookName().toLowerCase().equals(bookName.toLowerCase()))
            {
                if(this.bookArray[i].getBookStatus().equals("true"))
                    System.out.printf("The book is available named '%s'.\n",bookName);
                else if(this.bookArray[i].getBookStatus().equals("false"))
                   System.out.printf("The book is not available named '%s'.\n",bookName);
                return;
            }
        }
        System.out.printf("There is no book in our library system named '%s'.\n", bookName);
        return;
    }



    /**
     * bookIsUnique(String bookName)
     * check the book name is unique or not
     * @param bookName
     * @return false if book not unique
     * @return true if book unique
     */
    public boolean bookIsUnique(String bookName)
    {
        for(int i = 0; i < this.getNumberOfBooks(); ++i)
        {
            if(this.booksArrayList.get(i).getBookName().equals(bookName.toLowerCase()))
            {
                if(this.booksArrayList.get(i).getBookName().equals(bookName.toLowerCase()))
                    return false;
            }
        }
        return true;
    }


    /**
     * return all added book in the library system
     * @return allBooks
     */
    @Override
    public String toString() {
        String tempString="Books that recorded our the library system are;\n";

        for (int i = 0; i < this.getBooksArrayList().size(); ++i)
            tempString = tempString + (i+1) + ")" + this.getBooksArrayList().get(i).toString() + "\n";
        return tempString;
    }

    /**
     * readFile(String registeredMembersFileName, String bookInformationsFileName, String allLogsFileName)
     * read all .csv files and fill array lists
     * @param registeredMembersFileName
     * @param bookInformationsFileName
     * @param allLogsFileName
     */
    public void readFile(String registeredMembersFileName, String bookInformationsFileName, String allLogsFileName)
    {
        try {
            File file = new File(registeredMembersFileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if(parts[0].equals("member"))
                {
                    User tempUser = new User();
                    tempUser.setUsername(parts[1]);
                    tempUser.setPassword(parts[2]);
                    tempUser.setLoginType("member");
                    userArrayList.add(tempUser);
                }
                else if(parts[0].equals("root"))
                {
                    Staff tempStaff = new Staff();
                    tempStaff.setUsername(parts[1]);
                    tempStaff.setPassword(parts[2]);
                    tempStaff.setLoginType("root");
                    staffArrayList.add(tempStaff);
                }
            }

            scanner.close();

        }catch (FileNotFoundException e) {
            System.err.println("Registered Members File does not exist.");
        }


        try {
            int numberOfBooks = 0;
            File file = new File(bookInformationsFileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                ++numberOfBooks;
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                Book tempBook = new Book();

                tempBook.setBookName( parts[0]);
                tempBook.setBookAuthorName( parts[1] );
                tempBook.setPageNumber( parts[2]);
                tempBook.setBookStatus( parts[3] );

                booksArrayList.add(tempBook);
                bookLinkedList.add(tempBook);//linkled list structure update
                if(parts[3].equals("true")) ++this.counterOfAvailableBooks;

            }
            scanner.close();
            this.setNumberOfBooks(numberOfBooks);

            bookArray = new Book[numberOfBooks];  //Array update
            for (int i = 0;  i < numberOfBooks; ++i )
            {
                bookArray[i] = new Book();
                bookArray[i].setBookStatus( getBooksArrayList().get(i).getBookStatus() );
                bookArray[i].setPageNumber( getBooksArrayList().get(i).getPageNumber() );
                bookArray[i].setBookAuthorName( getBooksArrayList().get(i).getBookAuthorName() );
                bookArray[i].setBookName( getBooksArrayList().get(i).getBookName() );
            }
        } catch (FileNotFoundException e) {
            System.err.println("Book Informations File does not exist.");
        }

    }

    /**
     * registerNewUser(String registeredMembersFileName)
     * when staff register new user, call this method
     * take nameSurname and pass and save logs to arraylist
     * @param registeredMembersFileName
     */
    public void registerNewUser(String registeredMembersFileName)
    {
        Scanner newUserScanner = new Scanner(System.in);
        System.out.print("Please write name the user that will register the system: ");
        User tempNewUser = new User();
        tempNewUser.setUsername(newUserScanner.nextLine());

        if(usernameIsUnique(tempNewUser.getUsername()))
        {
            System.out.print("Please write password of new user: ");
            tempNewUser.setPassword(newUserScanner.nextLine());
            tempNewUser.setLoginType("member");
            getUserArrayList().add(tempNewUser);
            rewriteRegisteredMembersLogsToFile(registeredMembersFileName);
            System.out.printf("The user named '%s' added to system successfully\n.",tempNewUser.getUsername());

            getStaffArrayList().get(0).getOperationHistory().add(getStaffArrayList().get(0).addNewUserBookToString(tempNewUser.getUsername()));
        }
        else {
            System.out.println("You can not register a registered person.");
        }
    }

    /**
     * removeBookProcces(String bookInformationFileName)
     * when staff remove book the system, call this method
     * take book nummber that will removing the system and remove it from the system
     * @param bookInformationFileName
     */
    public void removeBookProcces(String bookInformationFileName)
    {
        if(getBooksArrayList().size() == 0)
        {
            System.out.println("There is no book on the library system.");
        }
        else
        {
            Scanner removeBook = new Scanner(System.in);
            System.out.println( toString() );

            try {
                int number;

                System.out.print("Please choose book list number that will remove the system: ");
                number = Integer.parseInt( removeBook.nextLine() );

                if(number > 0 && number <= getBooksArrayList().size())
                {
                    if(getBooksArrayList().get(number-1).getBookStatus().equals("true"))
                    {
                        String tempBookName = getBooksArrayList().get(number-1).getBookName();
                        getBooksArrayList().remove( getBooksArrayList().get(number-1) );
                        bookLinkedList.remove( getBooksArrayList().get(number-1) ); //linked list update
                        setNumberOfBooks( getNumberOfBooks()-1 );
                        rewriteBookLogsToFile(bookInformationFileName);

                        System.out.printf("Book that named '%s' removed the system successfully. \n",tempBookName);
                        getStaffArrayList().get(0).getOperationHistory().add(getStaffArrayList().get(0).removeBookToString(tempBookName));

                        Book [] tempBookArray = new Book[ getBooksArrayList().size()];
                       // bookArray = new Book[getBooksArrayList().size()]; //Array update
                        for(int i = 0; i < getBooksArrayList().size(); ++i)
                        {
                            tempBookArray[i] = new Book();
                            tempBookArray[i] = getBooksArrayList().get(i);
                        }

                        bookArray = tempBookArray;

                        System.out.printf("???%d??", bookArray.length);
                    }
                    else
                    {
                        System.out.println("You can not remove the book that is borrowed.");
                    }
                }
                else {
                    System.out.println("Please write valid a number.");
                }
            }catch (Exception e)
            {
                System.out.println("Please write valid a number.");
            }
        }
    }

    /**
     * addNewBookProcess(String bookInformationFileName )
     * when staff add new book to the system, call this method
     * take name of the book tat will add and add it if its name is unique
     * @param bookInformationFileName
     */
    public void addNewBookProcess(String bookInformationFileName )
    {
        Book tempBook = new Book();
        Scanner addNewBook = new Scanner(System.in);
        System.out.print("Please write name of the book that will add to the library system: ");
        tempBook.setBookName(addNewBook.nextLine());

        if(bookIsUnique(tempBook.getBookName()))
        {
            System.out.print("Please write author name of the book that will add to the library system: ");
            tempBook.setBookAuthorName(addNewBook.nextLine());

            setNumberOfBooks(getNumberOfBooks()+1);
            tempBook.setBookStatus("true");
            System.out.print("Please write number of the book that will add to the library system: ");
            tempBook.setPageNumber(addNewBook.nextLine());

            booksArrayList.add(tempBook);
            bookLinkedList.add(tempBook); //linked list update
            bookArray = new Book[getBooksArrayList().size() ];
            for(int i = 0; i < bookArray.length; ++i)
            {
                bookArray[i] = new Book();
                bookArray[i] = booksArrayList.get(i);
            }


            rewriteBookLogsToFile(bookInformationFileName);
            System.out.printf("The book named %s has successfully added.\n", tempBook.getBookName());
            getStaffArrayList().get(0).getOperationHistory().add(getStaffArrayList().get(0).addNewBookToString(tempBook.getBookName()));
        }
        else {
            System.out.printf("The book that named '%s' has already added. You can not add again.\n",tempBook.getBookName());
        }
    }

    /**
     * bookReturningProcess(String bookInformationFileName)
     * when user return book, call this methods
     * take name of the book that will return to system check that book barrowed user same or not with returner
     * @param bookInformationFileName
     */
    public void bookReturningProcess(String bookInformationFileName)
    {
        String tempBookName;
        System.out.print("Please write name of the book that you want to return: ");
        Scanner returnBookScanner = new Scanner(System.in);
        tempBookName = returnBookScanner.nextLine();
        String returnBookStatus = getUserArrayList().get(getUserIndexInArrayList()).
                returnBook(tempBookName, getStatusOfBook(tempBookName) );

        if(returnBookStatus.equals("true") && getIndexOfBook(tempBookName) != -1)
        {
            getBooksArrayList().get(getIndexOfBook(tempBookName)).setBookStatus("true");
            bookLinkedList.get(getIndexOfBook(tempBookName)).setBookStatus("true"); //linked list update
            bookArray[ getIndexOfBook(tempBookName) ].setBookStatus("true");  //array list update
            System.out.printf("The book returned named '%s'.\n", tempBookName);
            rewriteBookLogsToFile(bookInformationFileName);
        }
        else if(getIndexOfBook(tempBookName) == -1)
        {
            System.out.printf("The book named '%s' is not saved on the library system.\n",tempBookName);
        }
        else if(returnBookStatus.equals("false"))
        {
            System.out.printf("The book named '%s' is not available now. \n", tempBookName);
        }

    }

    /**
     * barrowingProcess(String bookInformationFileName)
     * when user barrow book, call this methods
     * check the book that desired book is available or not
     * @param bookInformationFileName
     */
    public void barrowingProcess(String bookInformationFileName)
    {
        if(getNumberOfBooks() == 0)
        {
            System.out.println("There are no books registered on the system. Please contact with library staff.");
        }
        else if(getCounterOfAvailableBooks() == 0)
        {
            System.out.println("There are no books available on the system. All books that we have are barrowed.");
        }
        else
        {
            try {

                boolean flag = false;
                int choiceWillBarrowBookNumber;
                System.out.println( toString() );
                do{
                    System.out.printf("Write '-1' to exit.\nYou can choose available books between 1 and %s: ",
                            getNumberOfBooks());

                    Scanner choiceOfUser2 = new Scanner(System.in);
                    choiceWillBarrowBookNumber = choiceOfUser2.nextInt();

                    if (choiceWillBarrowBookNumber != -1 &&
                            !(choiceWillBarrowBookNumber > 0 && choiceWillBarrowBookNumber <= getNumberOfBooks())) {
                        System.out.println(">>>Please write '-1' to exit or choose a available a book.<<<");
                    } else if (choiceWillBarrowBookNumber > 0 && choiceWillBarrowBookNumber <= getNumberOfBooks()) {
                        if (getBooksArrayList().get(choiceWillBarrowBookNumber - 1).getBookStatus().equals("true"))
                        {
                            getBooksArrayList().get(choiceWillBarrowBookNumber - 1).setBookStatus("false");
                            bookLinkedList.get(choiceWillBarrowBookNumber - 1).setBookStatus("false"); // Lİnked list update
                            bookArray[(choiceWillBarrowBookNumber - 1)].setBookStatus("false"); // Array update
                            rewriteBookLogsToFile(bookInformationFileName);

                            String bookName = getBooksArrayList().get(choiceWillBarrowBookNumber - 1).getBookName();
                            getUserArrayList().get(getUserIndexInArrayList()).getOperationHistory().add
                                    ( getUserArrayList().get(getUserIndexInArrayList()).bookBarrowing(bookName) );
                            flag = true;
                            System.out.printf("The book named '%s' added the system successfully.\n",bookName);
                        } else {
                            System.out.println("The book is not available to choose.\n");
                        }
                    }
                }while (choiceWillBarrowBookNumber != -1 && !flag);
            }catch (InputMismatchException e)
            {
                System.out.print("Exception:Input Mismatch. You should only write an integer.\n ");
            }
        }
    }
}
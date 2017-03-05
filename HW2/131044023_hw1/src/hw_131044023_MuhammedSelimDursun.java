import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by MSD on 21.02.2017.
 * main method for manupulating all class and hierarchy
 */
public class hw_131044023_MuhammedSelimDursun {
    public static void main(String[] args) throws FileNotFoundException {
         final String bookInformationFileName = "BookInformations.csv";
         final String registeredMembersFileName = "RegisteredMembers.csv";
         final String allLogsFileName = "allLogs.csv";

        System.out.println("Welcome to GTU Library Management System..\n");

        LibraryManagementSystem library = new LibraryManagementSystem();
        library.readFile(registeredMembersFileName, bookInformationFileName, allLogsFileName);
        String choice ;

        do{
            System.out.print("Menu-----------------------------------------------------");
            System.out.println("\n1)Log In\n2)View Books\n3)Book Search\n4)Exit The System\n");
            do{
                System.out.print("You can choose 1 to 4: ");
                Scanner choiceOfUser = new Scanner(System.in);
                choice = choiceOfUser.next();
            } while(!(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") )) ;

            if(choice.equals("1"))
            {
                String nameSurname = "";
                String password = "";
                System.out.print("Please write your name and surname to enter the system: ");
                Scanner scannerName = new Scanner(System.in);
                nameSurname = scannerName.nextLine();
                System.out.print("Please write your password to enter the system: ");
                Scanner scannerPass = new Scanner(System.in);
                password = scannerPass.nextLine();

                if(library.determineUserOrStaff(nameSurname,password) == 0 ) //determine login as user
                {
                    System.out.printf("\nLogin is successful. Login type: Member.\nWelcome %s.\n", nameSurname);

                    String choiceInLoginAsMember;
                    library.getUserArrayList().get(library.getUserIndexInArrayList()).allLogsToArrHistoryList(allLogsFileName);
                    do{
                        System.out.print("Menu-----------------------------------------------------");
                        System.out.println("\n1)Barrow Book\n2)Book Return\n3)Procedure History\n4)View Books\n5)Book Search\n6)Exit\n");
                        System.out.print("You can choose 1 to 6: ");
                        Scanner choiceOfUser = new Scanner(System.in);
                        choiceInLoginAsMember = choiceOfUser.next();

                        if(choiceInLoginAsMember.equals("1")) //barrowing process
                        {
                            library.barrowingProcess(bookInformationFileName);
                        }
                        else if(choiceInLoginAsMember.equals("2")) //Book returning process
                        {
                            library.bookReturningProcess(bookInformationFileName);
                        }
                        else if(choiceInLoginAsMember.equals("3")) //polimorfic calling, procedure history print process
                        {
                            PersonAbstract PA = library.getUserArrayList().get(library.getUserIndexInArrayList());
                            PA.printOperationsHistory();
                        }
                        else if(choiceInLoginAsMember.equals("4")) //print all book that has saved to system
                        {
                            System.out.println( library.toString() );
                        }
                        else if(choiceInLoginAsMember.equals("5")) //searching book by name in the system
                        {
                            String namedBookToSeach;
                            System.out.print("Please write name of the book that will search: ");
                            Scanner choiceOfMember = new Scanner(System.in);
                            namedBookToSeach = choiceOfMember.nextLine();
                            library.bookSearchWithArray(namedBookToSeach);
                        }
                    }while (!choiceInLoginAsMember.equals("6"));
                    library.getUserArrayList().get(library.getUserIndexInArrayList()).writeBackToLogFile(allLogsFileName); //save all logs to files
                    break;
                }
                else if(library.determineUserOrStaff(nameSurname,password) == 1 )//determine login as staff
                {
                    System.out.printf("\nLogin is successful. Login type: Root.\nWelcome %s.\n", nameSurname);
                    library.getStaffArrayList().get(library.getStaffIndexInArrayList()).allLogsToArrHistoryList(allLogsFileName);

                    Staff tempStaff = new Staff();
                    tempStaff.setUsername(nameSurname);
                    tempStaff.setPassword(password);
                    tempStaff.setLoginType("root");
                    library.getStaffArrayList().add(tempStaff);
                    String choiceInLoginAsRoot;
                    do {
                        System.out.print("Menu-----------------------------------------------------");
                        System.out.println("\n1)Add New Book\n2)Remove Book\n3)Register New Library User \n4)Procedure History\n5)View Books\n6)Book Search\n7)Exit\n");
                        System.out.print("You can choose 1 to 7: ");
                        Scanner choiceOfUser = new Scanner(System.in);
                        choiceInLoginAsRoot = choiceOfUser.next();

                        if (choiceInLoginAsRoot.equals("1")) { //adding new book proccess
                            library.addNewBookProcess(bookInformationFileName);
                        } else if (choiceInLoginAsRoot.equals("2")) { //remove book process
                            library.removeBookProcces(bookInformationFileName);
                        } else if (choiceInLoginAsRoot.equals("3")) { //adding new book process
                            library.registerNewUser(registeredMembersFileName);
                        } else if (choiceInLoginAsRoot.equals("4")) { //polimorfic calling, procedure history print process
                            PersonAbstract PA = library.getStaffArrayList().get(0);
                            PA.printOperationsHistory();
                        } else if (choiceInLoginAsRoot.equals("5")) { //view books process
                            System.out.println( library.toString() );
                        }
                        else if (choiceInLoginAsRoot.equals("6")) { //book search process
                            String namedBookToSeach;
                            System.out.print("Please write name of the book that will search: ");
                            Scanner bookNameScanner = new Scanner(System.in);
                            namedBookToSeach = bookNameScanner.nextLine();

                            library.bookSearchWithLinkedList(namedBookToSeach);
                        }
                    } while (! choiceInLoginAsRoot.equals("7"));
                    library.getStaffArrayList().get(0).writeBackToLogFile(allLogsFileName); //save all logs to files
                    break;
                }
                else{
                    System.out.println("Login failed! You directed to the main menu.");
                }
            }
            else if(choice.equals("2")) //print all book that has saved to system
            {
                System.out.println( library.toString() );
            }
            else if(choice.equals("3")) //searching book by name in the system
            {
                String namedBookToSeach;
                System.out.print("Please write name of the book that will search.");
                Scanner choiceOfUser = new Scanner(System.in);
                namedBookToSeach = choiceOfUser.nextLine();
                library.bookSearch(namedBookToSeach);
            }
        }while(!choice.equals("4"));
        System.out.println("Thanks for visited GTU Library System..\n");
        System.out.println("@MuhammedSelimDursun");
    }
}

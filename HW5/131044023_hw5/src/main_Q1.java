import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Created by MSD on 31.03.2017.
 * test class for q1
 */
public class main_Q1 {
    public static void main(String [] args)
    {
        BinaryTree binaryTree = new BinaryTree();
        BinarySearchTree binarySearchTree = new BinarySearchTree();

        try {
            File file = new File("test.txt");
            Scanner scanner = new Scanner(file);

            if(scanner.hasNextLine())
            {
                String [] tempString = scanner.nextLine().split(" ");

                for(int i = 0; i < tempString.length; ++i)
                {
                    binaryTree.addElement(Integer.parseInt(tempString[i]));
                    binarySearchTree.addElement(Integer.parseInt(tempString[i]));
                }
                System.out.println("Content of the file:");
                for (int i = 0; i < tempString.length; ++i  )
                {
                    System.out.print(tempString[i] + " ");
                }
                System.out.println("\nPre order traversing:");
                binaryTree.traverseTheTree();
                System.out.println("\nLevel order traversing:");
                binarySearchTree.traverseTheTree();
            }
        }
        catch (FileNotFoundException e)
        {
            System.err.println("File could not open.");
        }


    }
}

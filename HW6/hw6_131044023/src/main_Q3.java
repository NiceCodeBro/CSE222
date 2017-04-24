import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by MSD on 6.04.2017.
 * test class for q2
 */
public class main_Q3 {

    public static void main(String [] args)
    {
        ArrayList arr = new ArrayList();
        try{
            File file = new File("family.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine())
            {

                arr.add(scanner.nextLine());
            }
            FamilyTree ft = new FamilyTree(arr.get(0).toString());

            for(int i = 1; i < arr.size(); ++i)
            {
                String[] tempString = arr.get(i).toString().split(",");
                ft.addMember(tempString[0],tempString[1],tempString[2]);
            }
            ft.traverseTheTree();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            System.err.println(e);
        }

    }
}

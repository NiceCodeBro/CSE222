import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Created by MSD on 21.03.2017.
 * class testing for Q1 (StackA, StackB, StackC, StackD )
 */
public class q1_main {
    public static void main(String [] args) throws Exception {
        try {
            //file proecess
            File readFile = new File("test.csv");
            File writeFile = new File("testResult_1.csv");
            Scanner scanner = new Scanner(readFile);
            FileWriter writer = new FileWriter(writeFile);

            //creating new arraylist
            ArrayList tempArr = new ArrayList();

            StackA<String> stackA = new StackA<>();
            StackB<String> stackB = new StackB<>();
            StackC<String> stackC = new StackC<>();
            StackD<String> stackD = new StackD<>();

            while (scanner.hasNext())
            {
                String [] arrStackA = scanner.nextLine().split(",");
                for(int i = 0; i < arrStackA.length; ++i)
                {
                    stackA.push(arrStackA[i]);
                    stackB.push(arrStackA[i]);
                    stackC.push(arrStackA[i]);
                    stackD.push(arrStackA[i]);
                }
                //filling stacks
                tempArr.add(stackA.toString());
                tempArr.add(stackB.toString());
                tempArr.add(stackC.toString());
                tempArr.add(stackD.toString());
            }
            //writing to file
            for(int i = 0; i < tempArr.size(); ++i)
            {
                writer.write( ""+ tempArr.get(i) );
                writer.flush();
            }
            writer.close();
        }catch (Exception e)
        {
            throw new Exception("File could not found.");
        }
    }
}

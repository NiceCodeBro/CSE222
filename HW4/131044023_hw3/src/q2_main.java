import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/**
 * Created by MSD on 23.03.2017.
 * class testing for Q2 ( reverse methods)
 */
public class q2_main {
    public static void main(String[] args) throws Exception {
        try {
            File readFile = new File("test.csv");
            File writeFile = new File("testResult_2.csv");
            Scanner scanner = new Scanner(readFile);
            FileWriter writer = new FileWriter(writeFile);

            ArrayList tempArr = new ArrayList();

            while (scanner.hasNext())
            {
                myQueue<String> myqueque = new myQueue();
                Queue<String> queque = new LinkedList<>();
                String [] arr = scanner.nextLine().split(",");
                for(int i = 0; i < arr.length; ++i)
                {
                    myqueque.add(i,arr[i]);
                    queque.add(arr[i]);
                }

                myqueque.reverseTheQueue();
                queque = myqueque.reverseQueue(queque);
                tempArr.add(myqueque.toString1() + "\n");
                tempArr.add(myqueque.toString2(queque) + "\n");
            }

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

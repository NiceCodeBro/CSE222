import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by MSD on 24.03.2017.
 * class testing for Q3 (PriortyQueueA and PriortyQueueB)
 */
public class q3_main {
    public static void main(String[] args) throws Exception {

        try {
            File readFile = new File("test.csv");
            File writeFile = new File("testResult_3.csv");
            Scanner scanner = new Scanner(readFile);
            FileWriter writer = new FileWriter(writeFile);

            ArrayList tempArr = new ArrayList();

            while (scanner.hasNext())
            {
                PriorityQueueA <String> queueA = new PriorityQueueA<>();
                PriorityQueueB <String> queueB = new PriorityQueueB<>();

                String [] arr = scanner.nextLine().split(",");
                for(int i = 0; i < arr.length; ++i)
                {
                    queueA.insert(arr[i]);
                    queueB.insert(arr[i]);
                }
                tempArr.add(queueA.toString() + "\n");
                queueA.deleteMin();
                tempArr.add(queueA.toString() + "\n");


                tempArr.add(queueB.toString() + "\n");
                queueB.deleteMin();
                tempArr.add(queueB.toString() + "\n");
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

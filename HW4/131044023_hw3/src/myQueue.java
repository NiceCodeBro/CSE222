import java.util.Iterator;
import java.util.Queue;

/**
 * Created by MSD on 23.03.2017.
 * class use KWLinkedList class methods and class have two methods to reverse of queue
 */
public class myQueue<E> extends KWLinkedList {

    myQueue()
    {
        super();
    }
    /**
     * method use KWLinkedList methods and reverse linkedlist iteratively
     */
    void reverseTheQueue()
    {
        if(size() > 1)
        {
            int sizeTemp = size() - 1;
            for(int i = sizeTemp; i >= 0 ; --i)
            {
                super.add(super.get(i));
            }

            for(int i = sizeTemp; i >= 0 ; --i)
            {
                super.remove();
            }
        }
    }

    /**
     * method take a queue as parameter and reverse it recursively
     * @param queue
     * @return
     */
    Queue<E> reverseQueue(Queue<E> queue)
    {
        if( queue.size() > 0)
        {
            E tempElement = queue.poll();
            reverseQueue(queue);
            queue.add(tempElement);
        }

        return queue;
    }

    /**
     * to string method for iterative reverse method
     * @return
     */
    public String toString1()
    {
        int i = 0 ;
        String tempString = "";

        Iterator it = super.iterator();

        while (it.hasNext())
        {
            tempString = tempString + it.next() + ",";
        }
        return tempString;
    }

    /**
     * to string method for recursivly method
     * @param queue
     * @return
     */
    public String toString2(Queue<E> queue)
    {
        String tempString =  "" + queue.poll();
        while ( queue.size()>0)
            tempString = tempString + "," + queue.poll();

        return tempString;
    }
}



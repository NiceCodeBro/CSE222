import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Created by MSD on 24.03.2017.
 */
public class PriorityQueueB<E> {
    private LinkedList<E> linkedList;

    /**
     * default constructor for building linkedlist
     */
    PriorityQueueB()
    {
        linkedList = new LinkedList<>();
    }

    /**
     * return size of queue
     * @return
     */
    public int size()
    {
        return linkedList.size();
    }

    /**
     * method return true only if size is 0, else return false
     * @return
     */
    public boolean isEmpty()
    {
        if(size() > 0)
            return false;
        else
            return true;
    }


    /**
     * Deletes and returns the highest priority element. Lower values have higher priority.
     * @return
     */
    public String deleteMin()
    {
        if(isEmpty())
            throw new NoSuchElementException();
        else
        {
            String  hightPriority = linkedList.get(0).toString();
            int hightPriorityIndex = 0;
            for(int i = 0; i < size(); ++i )
            {
                if(linkedList.get(i).toString().compareTo(hightPriority) < 0)
                {
                    hightPriorityIndex = i;
                    hightPriority = linkedList.get(i).toString();
                }
            }

            linkedList.remove(hightPriorityIndex);
            return hightPriority;
        }



    }
    /**
     * method to insert a element end of que
     * @param element
     */
    public void insert(E element)
    {
        linkedList.add(element);
    }

    /**
     * to string method
     * @return
     */
    public String toString()
    {
        String tempString = "";

        int i = 0;

        while (i < linkedList.size())
        {
            tempString = tempString + linkedList.get(i);
            if(linkedList.size() > 1 && i < linkedList.size()-1)
                tempString = tempString + ",";
            ++i;
        }

        return tempString;
    }

}

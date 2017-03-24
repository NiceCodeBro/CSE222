import java.util.LinkedList;
import java.util.NoSuchElementException;
/**
 * Created by MSD on 24.03.2017.
 *
 */
public class PriorityQueueA<E> extends LinkedList<E>{
    public PriorityQueueA()
    {
        super();
    }

    /**
     * return size of queue
     * @return
     */
    public int size()
    {
        return super.size();
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
            String  hightPriority = super.get(0).toString();
            int hightPriorityIndex = 0;
            for(int i = 0; i < size(); ++i )
            {
                if(super.get(i).toString().compareTo(hightPriority) < 0)
                {
                    hightPriorityIndex = i;
                    hightPriority = super.get(i).toString();
                }
            }
            super.remove(hightPriorityIndex);
            return hightPriority;
        }
    }

    /**
     * method to insert a element end of que
     * @param element
     */
    public void insert(E element)
    {
        super.add(element);
    }

    /**
     * to string method
     * @return
     */
    public String toString()
    {
        String tempString = "";
        int i = 0;
        while (i < size())
        {
            tempString = tempString + super.get(i);
            if(super.size() > 1 && i < super.size()-1)
                tempString = tempString + ",";
            ++i;
        }
        return tempString;
    }
}

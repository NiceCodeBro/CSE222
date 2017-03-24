import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by MSD on 21.03.2017.
 * Class that simulate of stack woring prenciple by extend ArrayList
 */
public class StackD <E> implements StackInterface<E>
{
    Queue<E> queue;

    /**
     * no parameter constructor for build queue
     */
    StackD()
    {
        queue = new LinkedList<E>();

    }
    /**
     * method that return size of arraylist
     * @return
     */
    @Override
    public int size()
    {
        return queue.size();
    }
    /**
     * method return true only if size is 0, else return false
     * @return
     */
    @Override
    public boolean isEmpty() {
        if(size() > 0)
            return true;
        else
            return false;
    }

    /**
     * method that return element that last added
     * @return
     */
    @Override
    public E peek() {
        return queue.peek();
    }

    /**
     * method that return element that last added and delete it
     * @return
     */
    @Override
    public E pop() {
        return queue.remove();
    }

    /**
     * method add a element to stack and return it
     * @param element
     * @return
     */
    @Override
    public E push(E element) {
        queue.add(element);
        return element;
    }

    /**
     * to string method
     * @return
     */
    public String toString()
    {
        String tempString2 = "" + size();
        String tempString = "";

        tempString =  "" + this.pop();
        while ( size()>0)
            tempString =  this.pop()+ "," + tempString;

        tempString = tempString2 + "," + tempString + "\n";
        return tempString;
    }
}

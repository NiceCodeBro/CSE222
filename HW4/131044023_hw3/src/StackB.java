import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * Created by MSD on 21.03.2017.
 * Class that simulate of stack woring prenciple by extend ArrayList
 */
public class StackB <E> implements StackInterface<E>
{
    protected ArrayList<E> arrayList;

    /**
     * no parameter constructor to build arraylist
     */
    public StackB()
    {
        arrayList = new ArrayList<E>();
    }

    /**
     * method that return size of arraylist
     * @return
     */
    @Override
    public int size()
    {
        return arrayList.size();
    }

    /**
     * method that return element that last added
     * if no element in stack throw EmptyStackException
     * @return
     */
    @Override
    public E peek()
    {
        int len;
        len = arrayList.size();

        if (len == 0)
            throw new EmptyStackException();
        return arrayList.get(len - 1);
    }

    /**
     * method that return element that last added and delete it
     * @return
     */
    @Override
    public E pop()
    {
        E   obj;
        int len = arrayList.size();
        obj = this.peek();
        arrayList.remove(len - 1);

        return obj;
    }
    /**
     * method add a element to stack and return it
     * @param element
     * @return
     */
    @Override
    public E push(E element)
    {
        arrayList.add(element);
        return element;
    }

    /**
     * method return true only if size is 0, else return false
     * @return
     */
    @Override
    public boolean isEmpty()
    {
        if(arrayList.size() == 0)
            return true;
        else
            return false;
    }

    /**
     * to string method
     * @return
     */
    public String toString()
    {
        String tempString = "" + arrayList.size();

        while ( !isEmpty())
            tempString = tempString + "," + pop();

        tempString = tempString + "\n";
        return tempString;
    }
}

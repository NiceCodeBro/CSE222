import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * Created by MSD on 21.03.2017.
 * Class that simulate of stack woring prenciple by extend ArrayList
 */
public class StackA<E> extends ArrayList<E> implements StackInterface<E> {

    @Override
    public int size()
    {
        return super.size();
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
        len = super.size();

        if (len == 0)
            throw new EmptyStackException();
        return get(len - 1);
    }

    /**
     * method that return element that last added and delete it
     * @return
     */
    @Override
    public E pop()
    {
        E   obj;
        int len = super.size();
        obj = this.peek();
        remove(len - 1);

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
        super.add(element);
        return element;
    }

    /**
     * method return true only if size is 0, else return false
     * @return
     */
    @Override
    public boolean isEmpty()
    {
        if(super.size() == 0)
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
        String tempString = "" + super.size();

        while ( !isEmpty())
            tempString = tempString + "," + pop();

        tempString = tempString + "\n";
        return tempString;
    }

}

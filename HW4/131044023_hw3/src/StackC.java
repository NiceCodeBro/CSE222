import java.util.EmptyStackException;

/**
 * Created by MSD on 21.03.2017.
 * Class that simulate of stack woring prenciple by extend ArrayList
 */
public class StackC <T> implements StackInterface<T>
{
    private myNode<T> head; /*head of linked list*/
    private myNode<T> end ; /*last node of linked list*/
    private int size ; /*size of linked list*/

    /**
     * no parameter constructor for build of linked list elements
     */
    StackC()
    {
        size = 0;
        head = null;
        end = null;
    }
    /**
     * method that return size of linkedlist
     * @return
     */
    @Override
    public int size()
    {
        return size;
    }

    /**
     * method return true only if size is 0, else return false
     * @return
     */
    @Override
    public boolean isEmpty()
    {
        if(this.size() == 0)
            return true;
        else
            return false;
    }

    /**
     * method that return element that last added
     * if no element in stack throw EmptyStackException
     * @return
     */
    @Override
    public T peek()
    {
        if( isEmpty() )
            throw new EmptyStackException();
        else
        {
            return head.getElement();
        }
    }
    /**
     * method that return element that last added and delete it
     * @return
     */
    @Override
    public T pop()
    {
        T   obj;
        int len = size;
        obj = get(size-1).getElement();
        this.removeFirst();

        return obj;
    }

    /**
     * method add a element to stack and return it
     * @param element
     * @return
     */
    @Override
    public T push(T element) {
        this.add(element);
        return element;
    }

    //remove the last element of linked list
    private void removeFirst()
    {
        if(this.size() == 1)
        {
            head = null;
            end = null;
            --size;
        }
        else if( this.size > 1)
        {
            myNode<T> nodePtr = get(size-1);
            nodePtr.setNext(null);
            --size;
        }

    }

    //return a node spesific index in linked list
    private myNode<T> get(int index) {
        myNode<T> nodePtr = null;
        if(index > this.size() || index < 0)
            return nodePtr;
        else
        {
            nodePtr = head;
            int number = 0;
            while (number != index)
            {
                nodePtr = nodePtr.getNext();
                ++number;
            }
        }
        return nodePtr;
    }
    //add method for add a element end of the linked list
    private void add(T element)
    {
        myNode<T> nodePtr = new myNode<>();
        nodePtr.setElement(element);
        nodePtr.setNext(null);
        if( isEmpty() )
        {
            head = nodePtr;
            end = head;
            ++size;
        }
        else
        {
            end.setNext(nodePtr);
            end = nodePtr;
            ++size;
        }
    }

    /**
     * to string method
     * @return
     */
    public String toString()
    {
        String tempString = "" + this.size;

        while ( !isEmpty())
        {
            tempString = tempString + "," + this.pop();
        }
        tempString = tempString + "\n";

        return tempString;
    }
}

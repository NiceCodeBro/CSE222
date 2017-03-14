import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by MSD on 9.03.2017.
 * this class that simulate single linked list
 */
public class mySingleLinkedList<T> implements Iterable<T> {
    private Node<T> head; /*head of linked list*/
    private Node<T> end ; /*last node of linked list*/
    private int size ; /*size of linked list*/

    /**
     * iterator to move on through the list
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {
        Node<T> tempPtr=head;
        int index = 0;

            @Override
            public boolean hasNext() {
                if(index != 0)
                    tempPtr = tempPtr.getNext();
                return index < size && tempPtr != null;
            }

            @Override
            public T next() {
                ++index;
                return tempPtr.getElement();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

    /**
     *
     * @param action
     */
    @Override
    public void forEach(Consumer<? super T> action) {
        return;
    }

    /**
     *
     * @return
     */
    @Override
    public Spliterator<T> spliterator() {
        return null;
    }

    /**
     * Default constructor
     */
    public mySingleLinkedList()
    {
        head = null;
        end = null;
        size = 0;
    }

    /**
     *  method to check if list is empty
     * @return
     */
    public boolean isEmpty()
    {
        return head == null;
    }

    /**
     * method to get size of list
     * @return
     */
    public int getSize()
    {
        return size;
    }

    /**
     * method to add element to linked list
     * @param element
     */
    public void add(T element)
    {
        Node<T> nodePtr = new Node<>();
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
     * method to get spesific index of linked list
     * @param index
     * @return
     */
    public Node<T> get(int index) {
        Node<T> nodePtr = null;
        if(index > getSize() || index < 0)
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

    /**
     * method to get content of the linked list
     * @return
     */
    public String toString()
    {
        String tempString = "";
        tempString = "Singly Linked List= \n";
        if (size == 0)
        {
            tempString = tempString + "empty.\n";
            return tempString;
        }
        else
        {
            Node<T> nodePtr = head;
            while (nodePtr != null)
            {
                tempString = tempString + nodePtr.getElement();
                nodePtr = nodePtr.getNext();
                if(nodePtr != null)
                    tempString = tempString + "->";
            }
        }
        return tempString;
    }

    /**
     * method to get reversed linked list
     * @param nodePtr
     * @return
     */
    public String reverseToString(Node<T> nodePtr)
    {
        String tempString = "";
        if(nodePtr == null){
            return tempString;
        }
        if(nodePtr.getNext() == null)
            return (String) nodePtr.getElement();
        else
        {
            String temp = reverseToString(nodePtr.getNext());
            tempString = tempString + temp + "->" + nodePtr.getElement() ;
        }
        return tempString;

    }


    public static void main(String [] args)
    {
        mySingleLinkedList<String> m = new mySingleLinkedList<>();
        m.add("muz");
        m.add("elma");
        m.add("armut");
        m.add("ayva");
        m.add("erik");
        m.add("cilek");
        m.add("portakal");
        m.add("mandalina");
        Node<String> n = new Node<>();

        System.out.println( m.toString() );
        System.out.println( "Reversed linked list:");
        System.out.println( m.reverseToString(m.head) );

    }

}

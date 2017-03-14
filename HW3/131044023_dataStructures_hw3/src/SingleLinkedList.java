import java.io.File;
import java.util.Scanner;

/**
 * Created by MSD on 11.03.2017.
 * SingleLinkedList class which reuses deleted nodes.  class will keep deleted nodes.
 * When required, instead of creating a new node,  class will use one of the deleted nodes.
 * This way class will do less garbage collection.
 */
public class SingleLinkedList<T> {

    private Node<T> head;
    private Node<T> end ;
    private int size ;

    private Node<T> headDeleted;
    private Node<T> endDeleted ;
    private int sizeDeleted ;

    /**
     *  default constructor
     */
    public SingleLinkedList()
    {
        head = null;
        end = null;
        size = 0;
        headDeleted = null;
        endDeleted = null;
        sizeDeleted = 0;

    }

    /**
     * getter for regular linked list size
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * getter for deleted linkedlist size
     * @return
     */
    public int getSizeDeleted() {
        return sizeDeleted;
    }

    /**
     * method to check if list is empty
     * @return
     */
    public boolean isEmpty()
    {
        return head == null;
    }

    /**
     * method to check if list is empty
     * @return
     */
    public boolean isEmpty2()
    {
        return headDeleted == null;
    }

    /**
     * method to get node at spesific index in regular linked list.
     * @param index
     * @return
     * @throws Exception
     */
    public Node<T> get(int index) throws Exception {
        Node<T> nodePtr = head;
        if(index > getSize() || index < 0)
            throw new Exception("Invalid index. Index should be in range.");
        else
        {
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
     * method to get node at spesific index in deleted linked list.
     * @param index
     * @return
     * @throws Exception
     */
    public Node<T> getDeleted(int index) throws Exception {
        Node<T> nodePtr = headDeleted;
        if(index > getSize() || index < 0)
            throw new Exception("Invalid index. Index should be in range.");
        else
        {
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
     * method to add element to regular linked list
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
     * method to add node to regular linked list
     * @param tempNode
     */
    public void addNode(Node<T> tempNode)
    {
        tempNode.setNext(null);

        if( isEmpty() )
        {
            head = tempNode;
            end = head;
            ++size;
        }
        else
        {
            end.setNext(tempNode);
            end = tempNode;
            ++size;
        }
    }

    /**
     * method to add node to deleted linked list
     * @param node
     */
    public void addNodeToDeletedLinkedList(Node<T> node)
    {
        node.setNext(null);

        if( isEmpty2() )
        {
            headDeleted = node;
            endDeleted = headDeleted;
            ++sizeDeleted;
        }
        else
        {
            endDeleted.setNext(node);
            endDeleted = node;
            ++sizeDeleted;
        }
    }

    /**
     * remove the node of regular linked list in spesific index
     * @param index
     */
    public void remove(int index)
    {
        if(index >= getSize() )
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: "+size);
        if(index == 0 && getSize() == 1)
        {
            addNodeToDeletedLinkedList(head);
            head = null;
            end = null;
            --size;

        }
        else if(index == 0 && getSize() > 1)
        {
            Node<T> tempNode = head.getNext();
            head.setNext(null);
            addNodeToDeletedLinkedList(head);
            head = tempNode;
            --size;
        }
        else{
            try {
                Node <T> tempNode = get(index-1);
                Node <T> tempNode2 = tempNode.getNext();
                tempNode.setNext(tempNode2.getNext());
                tempNode2.setNext(null);
                addNodeToDeletedLinkedList(tempNode2);
                --size;
                tempNode = null;
                tempNode2 = null;

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    /**
     * method for adding new element to regular linked list but firstly check that deleted linked list empty or not
     * if empty call normal add element method else delete node from deleted linked list and add to regular linked list
     * @param element
     */
    public void addToLinkListFromDeletedLinkList(T element)
    {
        if(sizeDeleted == 0)
        {
            add(element);
        }
        else if(sizeDeleted > 0) //if deleted linked list size > 0
        {
            try {
                Node<T> tempNode = getDeleted(sizeDeleted-1);

                if(sizeDeleted>1)
                    getDeleted(sizeDeleted-2).setNext(null);
                else
                    headDeleted = null;
                tempNode.setElement(element);
                addNode(tempNode);
                --sizeDeleted;
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    /**
     * return content of regular linked list
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
     * return content of deleted linked list
     * @return
     */
    public String toString2()
    {
        String tempString = "";
        tempString = "Singly Deleted Linked List= \n";
        if (sizeDeleted == 0)
        {
            tempString = tempString + "empty.\n";
            return tempString;
        }
        else
        {
            Node<T> nodePtr = headDeleted;
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
     * the main for testing that using the memory space Reuse without giving up memory to garbage collector.
     * @param args
     */
    public static void main(String [] args)
    {
        String fileName = "q4test.txt";
        SingleLinkedList<String > s = new SingleLinkedList<>();
        try {//reading 100 number
            File file = new File(fileName);
            Scanner input  = new Scanner(file);

            while (input.hasNextLine()) {
                String line = input.nextLine();
                s.addToLinkListFromDeletedLinkList(line);
            }
            input.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Size of main linked list: " + s.getSize());
        System.out.println("Size of deleted linked list: " + s.getSizeDeleted());
        System.out.println("100 number added to linked list: " + s.toString());
        System.out.println("Size of main linked list: " + s.getSize());
        System.out.println("Deleted 50 of 100...");
        for(int i = 0; i < 50; ++i)
        {
            s.remove(10);
        }
        System.out.println("Size of main linked list: " + s.getSize());
        System.out.println("Size of deleted linked list: " + s.getSizeDeleted());
        System.out.println("Main linked list: " + s.toString());
        System.out.println("Deleted linked list: " + s.toString2());
        //reading again 100 number
        try {
            File file = new File(fileName);
            Scanner input  = new Scanner(file);

            while (input.hasNextLine()) {
                String line = input.nextLine();
                s.addToLinkListFromDeletedLinkList(line);
            }
            input.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Reading new 100 number from file to linked list...");
        System.out.println("Size of main linked list: " + s.getSize());
        System.out.println("Size of deleted linked list: " + s.getSizeDeleted());
        System.out.println("Main linked list: " + s.toString());
        System.out.println("Deleted linked list: " + s.toString2());

        /*
        SingleLinkedList<String > s = new SingleLinkedList<>();
        s.add("muz");
        s.add("elma");
        s.add("armut");
        s.add("ayva");
        s.add("erik");
        s.add("cilek");
        s.add("portakal");
        s.add("mandalina");
        try {
            System.out.println(s.toString() );
            System.out.println(s.toString2());
            s.remove(4);
            s.remove(4);
            s.remove(2);
            System.out.println("After removing two member of linked list");
            System.out.println(s.toString());
            System.out.println(s.toString2());
            System.out.println("________________________________");
            s.addToLinkListFromDeletedLinkList("kivi");
            s.addToLinkListFromDeletedLinkList("yesilelma");
            System.out.println("After adding new member to linked list. First checking that wheter any member exist on deleted linked list.");
            System.out.println(s.toString());
            System.out.println(s.toString2());
            System.out.println("________________________________");

        }catch (Exception e)
        {

        }*/
    }

}

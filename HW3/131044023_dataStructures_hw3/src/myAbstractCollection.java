import java.util.*;

/**
 * Created by MSD on 10.03.2017.
 * myAbstractCollection class which appends any AbstractCollection object to any other
 * AbstractCollection object by concatenating them.
 */
class myAbstractCollection extends AbstractCollection {
    /**
     *
     * @return
     */
    @Override
    public Iterator iterator() {
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * appends any AbstractCollection object to any other AbstractCollection object by concatenating them
     * with iterator.
     * @param objectOfAddedOnTop
     * @param objectOfWillAdd
     */
    public void appendAnything(AbstractCollection objectOfAddedOnTop, AbstractCollection objectOfWillAdd)
    {
        Iterator iter = objectOfWillAdd.iterator();
        while (iter.hasNext())
        {
            objectOfAddedOnTop.add(iter.next());
        }
    }

    /**
     * the main for test of appendAnything method
     * @param args
     */
    public static void  main(String []args)
    {
        myAbstractCollection myAbstract = new myAbstractCollection(); //creating object of classs
        AbstractCollection arrList = new ArrayList();
        AbstractCollection link = new LinkedList();
        AbstractCollection vect = new Vector();

        arrList.add("elma");
        arrList.add("armut");
        arrList.add("ayva");
        System.out.printf("Content of arraylist at beginning: %s\n", arrList.toString() );
        System.out.println("Arraylist append with linked list.");
        System.out.printf("Content of arraylist at beginning: %s\n", link.toString() );
        myAbstract.appendAnything(link,arrList);
        System.out.printf("Content of linked list after: %s\n", link.toString() );
        System.out.println("Linked list appending wit arraylist again.." );
        myAbstract.appendAnything(arrList,link);
        System.out.printf("Content of arraylist after appending: %s\n", arrList.toString() );
        System.out.printf("Content of arraylist at beginning: %s\n", vect.toString() );
        System.out.println("Arraylist appending wit vector..." );
        myAbstract.appendAnything(vect,arrList);
        System.out.printf("Content of vector after appending: %s\n", vect.toString() );


    }
}

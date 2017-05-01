import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
/**
 * Created by MSD on 31.03.2017.
 * Class that can add a new data to tree and generate pre ordered tree status
 */
public class BinaryTree<K,V> implements Iterable{
    /**Head of tree*/
    protected Node tree = null;
    /**members of nodes that pre ordered*/
    private ArrayList preOrderArr = new ArrayList();
    /**
     * method that print content of pre ordered tree datas
     */
    public void traverseTheTree()
    {
        Iterator it = iterator();
        while (it.hasNext())
        {
            System.out.print(it.next()+ " ");
        }
    }
    /**
     * Wrapper method that call the recursiv add method.
     * methods cread arraylist and fill of tree content
     */
    public void addElement(String e, String  cityName)
    {

        add(tree,e, cityName);

        preOrderArr = new ArrayList();
        fillArrListPreOrder(tree);
    }

    /**
     * method add a new element to tree recursively
     * @param root
     * @param e
     */
    private void add(Node root,String e,String cityName)
    {
        if(tree == null)
        {
            tree = new Node(e,cityName);
            return;
        }
        else
        {
            if(root.getData().hashCode() > e.hashCode())
            {
                if(root.getLeft() != null)
                {
                    add(root.getLeft(),e, cityName);
                    return;
                }
                root.setLeft(new Node(e,cityName));
            }
            else if( root.getData().hashCode() <  e.hashCode())
            {
                if(root.getRight() != null)
                {
                    add(root.getRight(),e, cityName);
                    return;
                }
                root.setRight( new Node(e,cityName));
            }
        }
    }
    /**
     * method fill the the pre ordered array list recursively
     * @param root
     */
    private void fillArrListPreOrder(Node root)
    {
        if(root == null) return;
        preOrderArr.add(root.getData());
        fillArrListPreOrder(root.getLeft());
        fillArrListPreOrder(root.getRight());
    }

    /**
     * Iterator method that move on pre ordered arraylist
     * @return
     */
    @Override
    public Iterator iterator() {
        Iterator it = new Iterator() {
            int index = 0;
            int size = preOrderArr.size();
            @Override
            public boolean hasNext() {
                if(index < size && preOrderArr != null)
                    return true;
                return false;
            }

            @Override
            public Object next() {
                Object temp = preOrderArr.get(index);
                ++index;
                return temp;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
    /**
     * overridden method
     * @param action
     */
    @Override
    public void forEach(Consumer action) {

    }
    /**
     * overridden method
     * @return
     */
    @Override
    public Spliterator spliterator() {
        return null;
    }
}

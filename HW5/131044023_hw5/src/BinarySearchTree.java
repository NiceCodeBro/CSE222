import java.util.*;
/**
 * Created by MSD on 31.03.2017.
 * Class that can add a new data to tree and generate level ordered tree status
 */
public class BinarySearchTree extends BinaryTree implements Iterable {
    /**members of nodes that level ordered*/
    private ArrayList arrLevelOrder = new ArrayList();
    /**que for manupulate of tree when taking tree's level ordered state */
    private Queue<Node> queue = new LinkedList();

    /**
     * Iterator method that move on pre ordered arraylist
     * @return
     */
    public Iterator levelOrderIterator()
    {
        arrLevelOrder = new ArrayList();
        fillArrayListLevelOrder(super.tree);
        Iterator it = new Iterator() {
            int index = 0;
            @Override
            public boolean hasNext() {
                if(index < arrLevelOrder.size())
                    return true;
                return false;
            }

            @Override
            public Object next() {
                return arrLevelOrder.get(index++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
    /**
     * method that print content of pre ordered tree datas
     */
    public void traverseTheTree()
    {
        Iterator it = levelOrderIterator();
        while (it.hasNext())
        {
            System.out.print(it.next()+ " ");
        }
    }
    /**
     * method fill the the level ordered array list recursively
     * @param root
     */
    private void fillArrayListLevelOrder(Node root)
    {
        if(root == null) return;
        queue.add(root);

        while (queue.size() != 0)
        {
            arrLevelOrder.add(queue.peek().getData());
            if(queue.peek().getLeft() != null)
                queue.add(queue.peek().getLeft());
            if(queue.peek().getRight() != null)
                queue.add(queue.peek().getRight());
            queue.poll();
        }
    }

}

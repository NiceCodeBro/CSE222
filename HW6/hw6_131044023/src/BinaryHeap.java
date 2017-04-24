import java.util.*;

/**
 * Created by MSD on 17.04.2017.
 */
public class BinaryHeap<T extends Comparable<T>> extends BinaryTree<T> implements Queue<T>{
    private ArrayList<Node> heapArr = new ArrayList<Node>();
    @Override
    public boolean add(T t) {
        return false;
    }
    public Node<T> getNode() { return root;}
    @Override
    public boolean offer(T t) {
        int i = 0;
        heapArr.add(new Node(t));
        sortHeap();
        makeTree();
        return true;
    }

    private void makeTree()
    {
        int index = 0;
        boolean flag = false;
        root = heapArr.get(0);
        while (index*2+1 < heapArr.size())
        {
            if(index*2+1<heapArr.size())
            {
                heapArr.get(index).left = heapArr.get(index*2+1);
                flag = true;
            }
            if(index*2+2 < heapArr.size())
            {
                heapArr.get(index).right = heapArr.get(index*2+2);
                flag = true;
            }
            if(flag)
            {
                ++index;
                flag = false;
            }

        }
    }

    private void sortHeap()
    {
        if(heapArr.size() > 1)
        {
            int childIndex = heapArr.size()-1;
            int parentIndex = (childIndex-1)/2;

            T tempChildData = (T) heapArr.get(childIndex).data;
            T tempParentData = (T) heapArr.get(parentIndex).data;

            while ( parentIndex >= 0 && tempChildData.compareTo(tempParentData) < 0  )
            {
                heapArr.get(parentIndex).data = tempChildData ;
                heapArr.get(childIndex).data = tempParentData;

                childIndex = parentIndex;
                parentIndex = (childIndex-1)/2;

                if(parentIndex>=0)
                {
                    tempChildData = (T) heapArr.get(childIndex).data;
                    tempParentData = (T) heapArr.get(parentIndex).data;
                }
            }
        }
    }


    @Override
    public T remove() {
        return null;
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public T element() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public int size() {
        return heapArr.size();
    }

    @Override
    public boolean isEmpty() {
        if(heapArr.size() == 0)
            return true;
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
    //referance http://stackoverflow.com/questions/2241513/java-printing-a-binary-tree-using-level-order-in-a-specific-format
    public void printTree(Node<T> tmpRoot) {

        Queue<Node<T>> currentLevel = new LinkedList<Node<T>>();
        Queue<Node<T>> nextLevel = new LinkedList<Node<T>>();

        currentLevel.add(tmpRoot);

        while (!currentLevel.isEmpty()) {
            Iterator<Node<T>> iter = currentLevel.iterator();
            while (iter.hasNext()) {
                Node currentNode = iter.next();
                if (currentNode.left != null) {
                    nextLevel.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    nextLevel.add(currentNode.right);
                }
                System.out.print(currentNode.data + " ");
            }
            System.out.println();
            currentLevel = nextLevel;
            nextLevel = new LinkedList<Node<T>>();

        }

    }
}

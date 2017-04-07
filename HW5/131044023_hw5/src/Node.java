/**
 * Created by MSD on 31.03.2017.
 */
public class Node<T> {
    private int  data;
    private Node<T> left = null;
    private Node<T> right = null;

    public Node()
    {

    }
    public Node(int e)
    {
        setData(e);
    }
    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }
}

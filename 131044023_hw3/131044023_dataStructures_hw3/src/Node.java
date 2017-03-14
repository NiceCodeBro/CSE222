/**
 * Created by MSD on 9.03.2017.
 * Generic node class for linked list
 */

class Node<T>
{
    private T element; /*Node's element*/
    private Node<T> next; /*next node's link*/

    /**
     *  Defaul Constructor
     */
    public Node()
    {
        next = null;
    }

    /**
     * set the next node with this node
     * @param n
     */
    public void setNext(Node<T> n)
    {
        next = n;
    }

    /**
     * set element method
     * @param d
     */
    public void setElement(T d)
    {
        element = d;
    }

    /**
     * return the next node with connected this node
     * @return
     */
    public Node<T> getNext()
    {
        return next;
    }

    /**
     * return the element
     * @return
     */
    public T getElement()
    {
        return element;
    }
}
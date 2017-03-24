/**
 * created by MSD on 21.03.2017.
 * Generic node class for linked list
 */

class myNode<T>
{
    private T element; /*Node's element*/
    private myNode<T> next; /*next node's link*/

    /**
     *  Defaul Constructor
     */
    public myNode()
    {
        next = null;
    }

    /**
     * set the next node with this node
     * @param n
     */
    public void setNext(myNode<T> n)
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
    public myNode<T> getNext()
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
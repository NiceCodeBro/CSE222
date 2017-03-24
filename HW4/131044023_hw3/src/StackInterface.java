/**
 * Created by MSD on 21.03.2017.
 * interface for simulating stack woring prenciple
 */
public interface StackInterface<E> {

    public boolean isEmpty();
    public int size();
    public E peek();
    public E pop();
    public E push(E element);
}
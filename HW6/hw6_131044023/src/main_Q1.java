/**
 * Created by MSD on 20.04.2017.
 */
public class main_Q1 {

    public static void main(String[] args)
    {
        BinaryHeap<Integer> bh = new BinaryHeap<>();
        bh.offer(6);
        bh.offer(18);
        bh.offer(29);
        bh.offer(20);
        bh.offer(28);
        bh.offer(39);
        bh.offer(66);
        bh.offer(37);
        bh.offer(26);
        bh.offer(76);
        bh.offer(32);
        bh.offer(74);
        bh.offer(89);


        System.out.println("Tree before adding 8");
        bh.printTree(bh.getNode());
        bh.offer(8);
        System.out.println("Tree after adding 8");
        bh.printTree(bh.getNode());
    }
}

import java.util.*;
import java.util.function.Consumer;

/**
 * Created by MSD on 2.04.2017.
 */
public class FamilyTree {

    /**root of tree*/
    private treeNode tree = null;
    private ArrayList<treeNode> tempArraylist;
    private ArrayList<treeNode> traverseArrayList;
    /**members of nodes that level ordered*/
    private ArrayList arrLevelOrder = new ArrayList();
    /**que for manupulate of tree when taking tree's level ordered state */
    private Queue<treeNode> queue = new LinkedList();

    /**
     * constructor for build tree's root
     * @param personName
     */
    public FamilyTree(String personName)
    {
        traverseArrayList = new ArrayList<>();
        tree = new treeNode(personName);
    }

    /**
     * method fill the the level ordered array list recursively
     * @param root
     */
    private void fillArrayListLevelOrder(treeNode root)
    {
        if(root == null) return;
        queue.add(root);

        while (queue.size() != 0)
        {
            arrLevelOrder.add(queue.peek().getPersonName());
            if(queue.peek().getLeft() != null)
                queue.add(queue.peek().getLeft());
            if(queue.peek().getRight() != null)
                queue.add(queue.peek().getRight());
            queue.poll();
        }
    }

    /**
     * method for adding a new node to tree
     * Method use helper arraylist
     * @param personName
     * @param parentName
     * @param nickname
     * @throws Exception
     */
    public void addMember(String personName,String parentName, String nickname) throws Exception {
        tempArraylist = null;
        tempArraylist = new ArrayList<>();
        getNodeByName(tree,parentName);
        if(tempArraylist.size() == 1)
        {
            tempArraylist.get(0).setNickName(nickname);
            if(tempArraylist.get(0).getLeft() == null)
            {
                tempArraylist.get(0).setLeft(new treeNode(personName));
            }
            else
            {
                addRight(tempArraylist.get(0).getLeft(),new treeNode(personName));
            }
        }
        else if(tempArraylist.size() > 1 ) //if size > 1
        {
            boolean flag = false;
            for(int i = 0; i < tempArraylist.size() && !flag; ++i)
            {
                if(tempArraylist.get(i).getPersonName().equals(parentName) && tempArraylist.get(i).getNickName().equals(nickname) && !flag)
                {
                    if(tempArraylist.get(i).getLeft() == null)
                        tempArraylist.get(i).setLeft(new treeNode(personName));
                    else
                        addRight(tempArraylist.get(i).getLeft(),new treeNode(personName));

                    flag = true;
                }
            }

            if(!flag)
                for(int i = 0; i < tempArraylist.size() && !flag; ++i )
                {
                    if(tempArraylist.get(i).getPersonName().equals(parentName) && tempArraylist.get(i).getNickName().equals("") && !flag)
                    {
                        tempArraylist.get(i).setNickName(nickname);
                        if(tempArraylist.get(i).getLeft() == null)
                            tempArraylist.get(i).setLeft(new treeNode(personName));
                        else
                            addRight(tempArraylist.get(i).getLeft(),new treeNode(personName));
                        flag = true;
                    }
                }

            if(!flag)
                throw new Exception("Exception: parent could not add to tree.");
        }

        if(tempArraylist.size() == 0)
            throw new Exception("Exception: parent not found to add to tree ");
    }


    /**
     * Iterator method that move on pre ordered arraylist
     * @return
     */
    public Iterator levelOrderIterator()
    {
        arrLevelOrder = new ArrayList();
        fillArrayListLevelOrder(tree);
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
     * method traverse the tree as parent name and save all parents node's referance to use later
     * @param node
     * @param parentName
     */
    private void getNodeByName(treeNode node, String parentName)
    {
        if(node != null)
        {
            if(node.getPersonName().equals(parentName))
            {
                tempArraylist.add(node);
            }
            getNodeByName(node.getLeft(),parentName);
            getNodeByName(node.getRight(),parentName);
        }
    }

    //nodun solunu dest olarak yolla
    private void addRight(treeNode destNode, treeNode sourceNode)
    {
        if(destNode.getRight() != null)
            addRight(destNode.getRight(),sourceNode);
        else
            destNode.setRight(sourceNode);
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

}
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by MSD on 2.04.2017.
 */
public class FamilyTree implements Iterable {

    /**root of tree*/
    private treeNode tree = null;
    private ArrayList<treeNode> tempArraylist;
    private ArrayList<treeNode> traverseArrayList;

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
     * method fill traverseArrayList for iterator
     * @param node
     */
    private void fillTraverseArraylist(treeNode node)
    {
        if(node != null)
        {
            if(node.getLeft() != null)
            {
                traverseArrayList.add(node);

            }
            fillTraverseArraylist(node.getLeft());
            fillTraverseArraylist(node.getRight());
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
     * Iterator method that traverse on tree
     * @return
     */
    @Override
    public Iterator iterator()
    {
        ArrayList tempArr = new ArrayList();
        fillTraverseArraylist(tree);
        for(int i = 0; i < traverseArrayList.size(); ++i)
        {
            int j = 0;
            treeNode temp = traverseArrayList.get(i);
            while(temp != null)
            {
                tempArr.add(temp.getPersonName());
                ++j;
                if(j == 1)
                    temp = temp.getLeft();
                else if(j > 1)
                    temp = temp.getRight();
            }
        }

        Iterator it = new Iterator() {
            int index = 0;
            int size = tempArr.size();
            @Override
            public boolean hasNext()
            {
                if(index < size && tempArr != null)
                    return true;
                return false;
            }

            @Override
            public Object next()
            {
                Object temp = tempArr.get(index);
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
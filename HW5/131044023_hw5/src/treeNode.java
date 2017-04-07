/**
 * Created by MSD on 6.04.2017.
 */
public class treeNode {

    private String personName="";
    private String nickName="";
    private treeNode left = null;
    private treeNode right = null;
    public boolean hasChild = false;
    public treeNode(String e)
    {
        setPersonName(e);
    }
    public String getPersonName() {
        return personName;
    }
    public void setPersonName(String data) {
        this.personName = data;
    }
    public treeNode getLeft() {
        return left;
    }
    public void setLeft(treeNode left) {
        this.left = left;
    }
    public treeNode getRight() {
        return right;
    }
    public void setRight(treeNode right) {
        this.right = right;
    }
    public String getNickName() { return nickName; }
    public void setNickName(String nickName) { this.nickName = nickName;}

}

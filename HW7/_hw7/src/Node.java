import javax.crypto.Cipher;

/**
 * Created by MSD on 31.03.2017.
 */
public class Node<T,K> {
    private String data;
    private String  cityName;
    private Node<T,K>left = null;
    private Node<T,K>right = null;


    public Node(String e, String cityName)
    {
        setData(e);
        setCityName(cityName);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Node<T, K> getLeft() {
        return left;
    }

    public void setLeft(Node<T, K> left) {
        this.left = left;
    }

    public Node<T, K> getRight() {
        return right;
    }

    public void setRight(Node<T, K> right) {
        this.right = right;
    }

    public String toString()
    {
       return "[" + data + "," + cityName + "]";
    }
}

/**
 * Created by MSD on 19.04.2017.
 */

import java.util.*;
import java.io.*;

/** Class to represent and build a Huffman tree.
 *   @author Koffman and Wolfgang
 * */

public class HuffmanTree
        implements Serializable {

    // Data Fields
    /** A reference to the completed Huffman tree. */
    public BinaryTree < HuffData > huffTree;
    private String mainEncodedMessage;
    // Nested Classes
    /** A datum in the Huffman tree. */
    public static class HuffData
            implements Serializable {
        // Data Fields
        /** The weight or probability assigned to this HuffData. */
        private double weight;

        /** The alphabet symbol if this is a leaf. */
        private Character symbol;

        public Character getSymbol(){ return symbol;}
        public HuffData(double weight, Character symbol) {
            this.weight = weight;
            this.symbol = symbol;
        }
    }



    /** A Comparator for Huffman trees; nested class. */
    private static class CompareHuffmanTrees
            implements Comparator < BinaryTree < HuffData >> {
        /** Compare two objects.
         @param treeLeft The left-hand object
         @param treeRight The right-hand object
         @return -1 if left less than right,
         0 if left equals right,
         and +1 if left greater than right
         */
        public int compare(BinaryTree < HuffData > treeLeft,
                           BinaryTree < HuffData > treeRight) {
            double wLeft = treeLeft.getData().weight;
            double wRight = treeRight.getData().weight;
            return Double.compare(wLeft, wRight);
        }
    }

    /** Builds the Huffman tree using the given alphabet and weights.
     post:  huffTree contains a reference to the Huffman tree.
     @param symbols An array of HuffData objects
     */
    public void buildTree(HuffData[] symbols) {
        Queue < BinaryTree < HuffData >> theQueue
                = new PriorityQueue < BinaryTree < HuffData >>
                (symbols.length, new CompareHuffmanTrees());
        // Load the queue with the leaves.
        for (HuffData nextSymbol : symbols) {
            BinaryTree < HuffData > aBinaryTree =
                    new BinaryTree < HuffData > (nextSymbol, null, null);
            theQueue.offer(aBinaryTree);
        }

        // Build the tree.
        while (theQueue.size() > 1) {
            BinaryTree < HuffData > left = theQueue.poll();
            BinaryTree < HuffData > right = theQueue.poll();
            double wl = left.getData().weight;
            double wr = right.getData().weight;
            HuffData sum = new HuffData(wl + wr, null);
            BinaryTree < HuffData > newTree =
                    new BinaryTree < HuffData > (sum, left, right);
            theQueue.offer(newTree);
        }

        // The queue should now contain only one item.
        huffTree = theQueue.poll();
    }

    /** Outputs the resulting code.
     @param out A PrintStream to write the output to
     @param code The code up to this node
     @param tree The current node in the tree
     */
    public void printCode(PrintStream out, String code,
                           BinaryTree < HuffData > tree) {
        HuffData theData = tree.getData();
        if (theData.symbol != null) {
            if (theData.symbol.equals(" ")) {
                out.println("space: " + code);
            }
            else {
                out.println(theData.symbol + ": " + code);
            }
        }
        else {
            printCode(out, code + "0", tree.getLeftSubtree());
            printCode(out, code + "1", tree.getRightSubtree());
        }
    }

    /** Method to decode a message that is input as a string of
     digit characters '0' and '1'.
     @param codedMessage The input message as a String of
     zeros and ones.
     @return The decoded message as a String
     */
    public String decode(String codedMessage) {
        StringBuilder result = new StringBuilder();
        BinaryTree < HuffData > currentTree = huffTree;
        for (int i = 0; i < codedMessage.length(); i++) {
            if (codedMessage.charAt(i) == '1') {
                currentTree = currentTree.getRightSubtree();
            }
            else {
                currentTree = currentTree.getLeftSubtree();
            }
            if (currentTree.isLeaf()) {
                HuffData theData = currentTree.getData();
                if(theData.symbol == '_')
                    theData.symbol = ' ';
                result.append(theData.symbol);
                currentTree = huffTree;
            }
        }
        return result.toString();
    }

    /** Method to encode a message that is input as a string of
     alphabets.
     @param encodedMessage The input message as a String of alphabets
     @return The encoded message as a String
     */
    public String encode(String encodedMessage)
    {

        StringBuilder result = new StringBuilder();
        BinaryTree < HuffData > currentTree = huffTree;
        for (int i = 0; i < encodedMessage.length(); i++) {
            char tempChar = encodedMessage.charAt(i);
            encodeHelper(huffTree,"",tempChar);
            if(mainEncodedMessage != "")
            {
                result.append(mainEncodedMessage);
                mainEncodedMessage = "";
            }
        }
        return result.toString();
    }

    /**
     * helper method for encode method.
     * this is recursively traverse the tree and fill string
     * @param tree
     * @param encodedMessage
     * @param ch
     */
    private void encodeHelper(BinaryTree<HuffData> tree,String encodedMessage,char ch)
    {
        if(tree.isLeaf() && tree.getData().getSymbol().equals(ch)) {

            mainEncodedMessage = encodedMessage;
            return;
        }else if(tree.isLeaf()) {
            return;
        }
        else if(!tree.isLeaf())
        {
            encodeHelper(tree.getLeftSubtree(),encodedMessage + 0,ch);
            encodeHelper(tree.getRightSubtree(),encodedMessage + 1,ch);
        }
    }

    /**
     * this method read file named freq.txt and fill huffData array and call buildTree method
     * @param fileName
     */
    public void readFile(String fileName)
    {
        HuffData[] huffDatas = new HuffData[27];

        int i = 0;
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine())
            {
                String[] temp = scanner.nextLine().split(" ");
                huffDatas[i] = new HuffData(Double.valueOf(temp[1]),temp[0].charAt(0));
                ++i;
            }

            this.buildTree(huffDatas);


        }
        catch (FileNotFoundException f)
        {
            System.err.println("File could not found");
        }
    }

}

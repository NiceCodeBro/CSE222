import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by MSD on 20.04.2017.
 */
public class main_Q2 {

    public static void main(String [] args)
    {
        HuffmanTree hf = new HuffmanTree();
        hf.readFile("freq.txt");

        System.err.println(hf.encode("merhaba gebze teknik"));
        System.err.println(hf.decode("110010010001000011010100000101011110000101010000011000010110101111101010110000110111011011000011"));

        System.err.println(hf.encode("bu veri yapilari dersinin bir odevi"));
        System.err.println(hf.decode("100000000011111100000010001001101111000111010100010011010110101000100110111101110100010001101100111011001111111000000110001011110011011101011000000110"));


        System.err.println(hf.encode("ders hocasi fatih erdogan sevilgen"));
        System.err.println(hf.decode("101110100010001111100011001000001010001101101111100111010110101100001111010001010111100110000110100111111001101011000000110101101000010100111"));

    }
}

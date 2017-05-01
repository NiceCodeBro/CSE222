import java.util.Iterator;
import java.util.Map;

/**
 * Created by syucer on 4/24/2017.
 */
public class HashTableChaining<K, V> implements HashMap<K, V> {
    /** The table */
    private HashtableOpen<K, V> [] table;
    /** The number of keys */
    private int numKeys;

    /** The capacity */
    private static final int CAPACITY = 101;

    /** The maximum load factor */
    private static final double LOAD_THRESHOLD = 3.0;


    // Constructor
    public HashTableChaining() {
        table = new HashtableOpen[CAPACITY];
    }

    /** Method get for class HashtableChain.
     @param key The key being sought
     @return The value associated with this key if found;
     otherwise, null
     */
    public V get(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null)
            return null; // key is not in the table.
        String tempS="";
        // Search the list at table[index] to find the key.
        for (int i = 0;i < table[index].table.length ; ++i) {
            Entry < K, V > nextItem = table[index].table[i];

            if (nextItem!= null && nextItem.key.equals(key))
            {
                tempS += (String ) nextItem.value + " " ;
            }
            if(i+1 == table[index].table.length)
                return (V)tempS;
        }



        // assert: key is not in the table.
        return null;
    }

    public Entry<K,V> getEntry(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null)
            return null; // key is not in the table.

        // Search the list at table[index] to find the key.
        for (int i = 0;i < table[index].table.length ; ++i) {
            Entry < K, V > nextItem = table[index].table[i];

            if (nextItem!= null && nextItem.key.equals(key))
                return nextItem;
        }

        // assert: key is not in the table.
        return null;
    }

    /** Method put for class HashtableChain.
     post: This key-value pair is inserted in the
     table and numKeys is incremented. If the key is already
     in the table, its value is changed to the argument
     value and numKeys is not changed.
     @param key The key of item being inserted
     @param value The value for this key
     @return The old value associated with this key if
     found; otherwise, null
     */
    public V put(K key, V value) {
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null) {
            // Create a new linked list at table[index].
            table[index] = new HashtableOpen<K, V>() ;
        }


        // assert: key is not in the table, add new item.
        table[index].put(key,value);

        numKeys++;
        if (numKeys > (LOAD_THRESHOLD * table.length))
            rehash();
        return null;
    }

    /** Returns the number of entries in the map */
    public int size() {
        return numKeys;
    }

    /** Returns true if empty */
    public boolean isEmpty() {
        return numKeys == 0;
    }

    /**** BEGIN EXERCISE ****/
    public V remove(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null)
            return null; // Key not in table

        for(int i = 0 ; i < table[index].table.length; ++i)
        {
            Entry < K, V > nextItem = table[index].table[i];

            if (nextItem!= null && nextItem.key.equals(key))
            {
                V item = table[index].table[i].value;
                table[index].table[i] = null;
                --numKeys;
                return item;
            }
        }
        return null; // Key not in table
    }

    public void rehash() {
        // Save a reference to oldTable
        HashtableOpen<K, V>[] oldTable = table;
        // Double capacity of this table
        table = new HashtableOpen[2 * oldTable.length + 1];

        // Reinsert all items in oldTable into expanded table.
        numKeys = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null) {
                for (Entry<K, V> nextEntry : oldTable[i].table) {
                    // Insert entry in expanded table
                    put(nextEntry.key, nextEntry.value);
                }
            }
        }
    }
}

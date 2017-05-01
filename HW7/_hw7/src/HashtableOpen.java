import java.util.Iterator;


/** Hash table implementation using open addressing.
 *  @author Koffman and Wolfgang
 */

public class HashtableOpen < K, V > implements Iterable{
    // Data Fields
    public Entry < K, V > [] table;
    private static final int START_CAPACITY = 101;
    private double LOAD_THRESHOLD = 0.75;
    private int numKeys;
    private int numDeletes;
    private final Entry < K, V > DELETED =
            new Entry < K, V > (null, null);


    @Override
    public Iterator iterator() {
        return null;
    }

    // Constructor
    public HashtableOpen() {
        table = new Entry[START_CAPACITY];
    }


    /** Returns the number of entries in the map */
    public int size() {
        return numKeys;
    }

    /** Returns true if empty */
    public boolean isEmpty() {
        return numKeys == 0;
    }

    /** Finds either the target key or the first empty slot in the
     search chain using linear probing.
     pre: The table is not full.
     @param key The key of the target object
     @return The position of the target or the first empty slot if
     the target is not in the table.
     */
    private int find(Object key) {
        // Calculate the starting index.
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length; // Make it positive.

        // Increment index until an empty slot is reached
        // or the key is found.
        while ( (table[index] != null)
                && (!key.equals(table[index].key))) {
            index++;
            // Check for wraparound.
            if (index >= table.length)
                index = 0; // Wrap around.
        }
        return index;
    }

    /** Method get for class HashtableOpen.
     @param key The key being sought
     @return the value associated with this key if found;
     otherwise, null
     */
    public V get(Object key) {
        // Find the first table element that is empty
        // or the table element that contains the key.
        int index = find(key);

        // If the search is successful, return the value.
        if (table[index] != null)
            return table[index].value;
        else
            return null; // key not found.
    }

    /** Method put for class HashtableOpen.
     post: This key-value pair is inserted in the
     table and numKeys is incremented. If the key is already
     in the table, its value is changed to the argument
     value and numKeys is not changed. If the LOAD_THRESHOLD
     is exceeded, the table is expanded.
     @param key The key of item being inserted
     @param value The value for this key
     @return Old value associated with this key if found;
     otherwise, null
     */
    public V put(K key, V value) {
        // Find the first table element that is empty
        // or the table element that contains the key.
        int index = find(key);
        while (table[index] != null)
        {
            if(index <START_CAPACITY)
                ++index;
            else
                index %= table.length;
        }
        // If an empty element was found, insert new entry.
        if (table[index] == null) {
            table[index] = new Entry < K, V > (key, value);
            numKeys++;
            // Check whether rehash is needed.
            double loadFactor =
                    (double) (numKeys + numDeletes) / table.length;
            if (loadFactor > LOAD_THRESHOLD)
                rehash();
            return null;
        }

        // assert: table element that contains the key was found.
        // Replace value for this key.
        V oldVal = table[index].value;
        table[index].value = value;
        return oldVal;
    }

    /** Expands table size when loadFactor exceeds LOAD_THRESHOLD
     post: The size of table is doubled and is an odd integer.
     Each nondeleted entry from the original table is
     reinserted into the expanded table.
     The value of numKeys is reset to the number of items
     actually inserted; numDeletes is reset to 0.
     */
    private void rehash() {
        // Save a reference to oldTable.
        Entry < K, V > [] oldTable = table;
        // Double capacity of this table.
        table = new Entry[2 * oldTable.length + 1];

        // Reinsert all items in oldTable into expanded table.
        numKeys = 0;
        numDeletes = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if ( (oldTable[i] != null) && (oldTable[i] != DELETED)) {
                // Insert entry in expanded table
                put(oldTable[i].key, oldTable[i].value);
            }
        }
    }

    /**** BEGIN EXERCISE ****/
    /** Remove the item with a given key value
     *  @param key The key to be removed
     *  @return The value associated with this key, or null
     *  if the key is not in the table.
     */
    public V remove(Object key) {
        int index = find(key);
        if (table[index] == null)
            return null;
        V oldValue = table[index].value;
        table[index] = DELETED;
        numKeys--;
        return oldValue;
    }

    /**** END EXERCISE ****/
}

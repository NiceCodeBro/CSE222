/** Contains key-value pairs for a hash table. */
public  class Entry < K, V > {

    /** The key */
    public K key;

    /** The value */
    public V value;

    /** Creates a new key-value pair.
     @param key The key
     @param value The value
     */
    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /** Retrieves the key.
     @return The key
     */
    public K getKey() {
        return key;
    }

    /** Retrieves the value.
     @return The value
     */
    public V getValue() {
        return value;
    }

    /** Sets the value.
     @param val The new value
     @return The old value
     */
    public V setValue(V val) {
        V oldVal = value;
        value = val;
        return oldVal;
    }

    public void setKey(K ky) {
        key = ky;
    }
}
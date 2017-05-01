/**
 * Created by syucer on 4/24/2017.
 */
import com.sun.deploy.panel.ITreeNode;

import java.util.*;
import java.util.HashMap;

public class BinaryNavMap<K,V> extends AbstractMap<K,V>
        implements NavigableMap<K,V>, Cloneable, java.io.Serializable
{
    public BinarySearchTree myTree = new BinarySearchTree();
    ArrayList<Node<String,String >> sortedByKeyArray = myTree.getInfArrayList();

    public BinaryNavMap()
    {
        sortArrayList(sortedByKeyArray);
    }
    @Override
    public V put(K key, V value) {
        myTree.addElement((String) key,(String) value);
        return value;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new TreeSet<>();

        for(int i = 0; i < sortedByKeyArray.size(); ++i)
        {
            final int index = i;
            set.add(new Entry<K , V>() {
                String key = sortedByKeyArray.get(index).getData();
                String value = sortedByKeyArray.get(index).getCityName();
                @Override
                public K getKey() {
                    return (K)key;
                }

                @Override
                public V getValue() {
                    return (V)value;
                }

                @Override
                public V setValue(V value) {
                    return null;
                }
                public String toString()
                {
                    return "[" + (K)key + "," +  (V)value + "]";
                }
            });
        }

        return set;

    }

    /**
     * Returns a key-value mapping associated with the greatest key
     * strictly less than the given key, or {@code null} if there is
     * no such key.
     *
     * @param key the key
     * @return an entry with the greatest key less than {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public Entry<K, V> lowerEntry(K key) {
        int index = -1;
        K tempK = key;

        if(key.equals(null)) {
            throw new NullPointerException();
        }
        if(sortedByKeyArray.size()>0)
        {
            if(sortedByKeyArray.get(0).getData().hashCode()> key.hashCode() && sortedByKeyArray.get(sortedByKeyArray.size()-1).getData().hashCode()< key.hashCode()  )
                throw new ClassCastException();

            for(int i =0; i < sortedByKeyArray.size(); ++i)
            {
                if(sortedByKeyArray.get(i).getData().hashCode() < tempK.hashCode())
                {
                    tempK = (K)sortedByKeyArray.get(i).getData();
                    index = i;
                }
            }
            final int finalIndex = index;
            if(index != -1)
                return new Entry<K, V>() {
                    @Override
                    public K getKey() {
                        return (K) sortedByKeyArray.get(finalIndex).getData();
                    }

                    @Override
                    public V getValue() {
                        return (V) sortedByKeyArray.get(finalIndex).getCityName();
                    }

                    @Override
                    public V setValue(V value) {
                        return null;
                    }
                    public String toString()
                    {
                        return "[" + (K)sortedByKeyArray.get(finalIndex).getData() + "," +  (V)sortedByKeyArray.get(finalIndex).getCityName() + "]";
                    }
                };
            return null;
        }

        return null;
    }

    /**
     * Returns the greatest key strictly less than the given key, or
     * {@code null} if there is no such key.
     *
     * @param key the key
     * @return the greatest key less than {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public K lowerKey(K key) {
        int index = -1;
        K tempK = key;

        if(key.equals(null)) {
            throw new NullPointerException();
        }
        if(sortedByKeyArray.size()>0)
        {
            if(sortedByKeyArray.get(0).getData().hashCode()> key.hashCode() && sortedByKeyArray.get(sortedByKeyArray.size()-1).getData().hashCode()< key.hashCode()  )
                throw new ClassCastException();

            for(int i =0; i < sortedByKeyArray.size(); ++i)
            {
                if(sortedByKeyArray.get(i).getData().hashCode()<= tempK.hashCode())
                {
                    tempK = (K)sortedByKeyArray.get(i).getData();
                    index = i;
                }
            }
            if(index != -1)
                return (K) sortedByKeyArray.get(index).getData();
            return null;
        }

        return null;
    }

    /**
     * Returns a key-value mapping associated with the greatest key
     * less than or equal to the given key, or {@code null} if there
     * is no such key.
     *
     * @param key the key
     * @return an entry with the greatest key less than or equal to
     * {@code key}, or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public Entry<K, V> floorEntry(K key) {
        int index = -1;

        if(key.equals(null)) {
            throw new NullPointerException();
        }
        if(sortedByKeyArray.size()>0)
        {
            if(sortedByKeyArray.get(0).getData().hashCode()> key.hashCode() && sortedByKeyArray.get(sortedByKeyArray.size()-1).getData().hashCode()< key.hashCode()  )
                throw new ClassCastException();

            for(int i =0; i < sortedByKeyArray.size(); ++i)
            {
                if(sortedByKeyArray.get(i).getData().hashCode() <= key.hashCode())
                {
                    index = i;
                }
            }
            final int finalIndex = index;
            if(index != -1)
                return new Entry<K, V>() {
                    @Override
                    public K getKey() {
                        return (K) sortedByKeyArray.get(finalIndex).getData();
                    }

                    @Override
                    public V getValue() {
                        return (V) sortedByKeyArray.get(finalIndex).getCityName();
                    }

                    @Override
                    public V setValue(V value) {
                        return null;
                    }
                    public String toString()
                    {
                        return "[" + (K)sortedByKeyArray.get(finalIndex).getData() + "," +  (V)sortedByKeyArray.get(finalIndex).getCityName() + "]";
                    }
                };
            return null;
        }

        return null;
    }

    /**
     * Returns the greatest key less than or equal to the given key,
     * or {@code null} if there is no such key.
     *
     * @param key the key
     * @return the greatest key less than or equal to {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public K floorKey(K key) {
        int index = -1;

        if(key.equals(null)) {
            throw new NullPointerException();
        }
        if(sortedByKeyArray.size()>0)
        {
            if(sortedByKeyArray.get(0).getData().hashCode()> key.hashCode() && sortedByKeyArray.get(sortedByKeyArray.size()-1).getData().hashCode()< key.hashCode()  )
                throw new ClassCastException();
            for(int i =0; i < sortedByKeyArray.size(); ++i)
            {
                if(sortedByKeyArray.get(i).getData().hashCode()< key.hashCode())
                {
                    index = i;
                }
            }
            if(index != -1)
                return (K) sortedByKeyArray.get(index).getData();
            return null;
        }

        return null;
    }

    /**
     * Returns a key-value mapping associated with the least key
     * greater than or equal to the given key, or {@code null} if
     * there is no such key.
     *
     * @param key the key
     * @return an entry with the least key greater than or equal to
     * {@code key}, or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public Entry<K, V> ceilingEntry(K key){
        K tempK = key;

        if(key.equals(null)) {
            throw new NullPointerException();
        }
        if(sortedByKeyArray.size()>0)
        {
            if(sortedByKeyArray.get(0).getData().hashCode()> key.hashCode() && sortedByKeyArray.get(sortedByKeyArray.size()-1).getData().hashCode()< key.hashCode()  )
                throw new ClassCastException();

            for(int i =0; i< sortedByKeyArray.size() ; ++i)
            {
                if(sortedByKeyArray.get(i).getData().hashCode() >= tempK.hashCode())
                {
                    tempK = (K)sortedByKeyArray.get(i).getData();
                    final int finalIndex = i;
                    return new Entry<K, V>() {
                        @Override
                        public K getKey() {
                            return (K) sortedByKeyArray.get(finalIndex).getData();
                        }

                        @Override
                        public V getValue() {
                            return (V) sortedByKeyArray.get(finalIndex).getCityName();
                        }

                        @Override
                        public V setValue(V value) {
                            return null;
                        }
                        public String toString()
                        {
                            return "[" + (K)sortedByKeyArray.get(finalIndex).getData() + "," +  (V)sortedByKeyArray.get(finalIndex).getCityName() + "]";
                        }
                    };
                }
            }

            return null;
        }

        return null;
    }

    /**
     * Returns the least key greater than or equal to the given key,
     * or {@code null} if there is no such key.
     *
     * @param key the key
     * @return the least key greater than or equal to {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public K ceilingKey(K key) {
        int index = -1;
        K tempK = key;

        if(key.equals(null)) {
            throw new NullPointerException();
        }
        if(sortedByKeyArray.size()>0)
        {
            if(sortedByKeyArray.get(0).getData().hashCode()> key.hashCode() && sortedByKeyArray.get(sortedByKeyArray.size()-1).getData().hashCode()< key.hashCode()  )
                throw new ClassCastException();
            for(int i =sortedByKeyArray.size()-1; i >= 0 ; --i)
            {
                if(sortedByKeyArray.get(i).getData().hashCode()>= tempK.hashCode())
                {
                    tempK = (K)sortedByKeyArray.get(i).getData();
                    index = i;
                }
            }
            if(index != -1)
                return (K) sortedByKeyArray.get(index).getData();
            return null;
        }

        return null;
    }

    /**
     * Returns a key-value mapping associated with the least key
     * strictly greater than the given key, or {@code null} if there
     * is no such key.
     *
     * @param key the key
     * @return an entry with the least key greater than {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public Entry<K, V> higherEntry(K key) {
        int index = -1;
        K tempK = key;

        if(key.equals(null)) {
            throw new NullPointerException();
        }
        if(sortedByKeyArray.size()>0)
        {
            if(sortedByKeyArray.get(0).getData().hashCode()> key.hashCode() && sortedByKeyArray.get(sortedByKeyArray.size()-1).getData().hashCode()< key.hashCode()  )
                throw new ClassCastException();
            for(int i = 0; i < sortedByKeyArray.size() ; ++i)
            {
                if(sortedByKeyArray.get(i).getData().hashCode()> tempK.hashCode())
                {
                    final int x = i;
                    return new Entry<K, V>() {
                        @Override
                        public K getKey() {
                            return (K) sortedByKeyArray.get(x).getData();
                        }

                        @Override
                        public V getValue() {
                            return (V) sortedByKeyArray.get(x).getCityName();
                        }

                        @Override
                        public V setValue(V value) {
                            return null;
                        }
                        public String toString()
                        {
                            return "[" + (K)sortedByKeyArray.get(x).getData() + "," +  (V)sortedByKeyArray.get(x).getCityName() + "]";
                        }
                    };
                }
            }
            return null;
        }

        return null;
    }

    /**
     * Returns the least key strictly greater than the given key, or
     * {@code null} if there is no such key.
     *
     * @param key the key
     * @return the least key greater than {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public K higherKey(K key){
        int index = -1;
        K tempK = key;

        if(key.equals(null)) {
            throw new NullPointerException();
        }
        if(sortedByKeyArray.size()>0)
        {
            if(sortedByKeyArray.get(0).getData().hashCode()> key.hashCode() && sortedByKeyArray.get(sortedByKeyArray.size()-1).getData().hashCode()< key.hashCode()  )
                throw new ClassCastException();
            for(int i = 0; i < sortedByKeyArray.size() ; ++i)
            {
                if(sortedByKeyArray.get(i).getData().hashCode()> tempK.hashCode())
                {
                    return (K) sortedByKeyArray.get(i).getData();
                }
            }
            return null;
        }

        return null;
    }

    /**
     * Returns a key-value mapping associated with the least
     * key in this map, or {@code null} if the map is empty.
     *
     * @return an entry with the least key,
     * or {@code null} if this map is empty
     */
    @Override
    public Entry<K, V> firstEntry() {

        if(sortedByKeyArray.size()>0)
            return new Entry<K, V>() {
                @Override
                public K getKey() {
                    return (K)sortedByKeyArray.get(0).getData();
                }

                @Override
                public V getValue() {
                    return (V)sortedByKeyArray.get(0).getCityName();
                }

                @Override
                public V setValue(V value) {
                    return null;
                }
                @Override
                public String toString()
                {
                    return "[" + (K)sortedByKeyArray.get(0).getData() + "," +  (V)sortedByKeyArray.get(0).getCityName() + "]";
                }


            };
        else
            return null;
    }

    /**
     * Returns a key-value mapping associated with the greatest
     * key in this map, or {@code null} if the map is empty.
     *
     * @return an entry with the greatest key,
     * or {@code null} if this map is empty
     */
    @Override
    public Entry<K, V> lastEntry() {
        if(sortedByKeyArray.size()>0)
        {
            return new Entry<K, V>() {
                @Override
                public K getKey() {
                    return (K)sortedByKeyArray.get(sortedByKeyArray.size()-1).getData();
                }

                @Override
                public V getValue() {
                    return (V)sortedByKeyArray.get(sortedByKeyArray.size()-1).getCityName();
                }

                @Override
                public V setValue(V value) {
                    return null;
                }
                @Override
                public String toString()
                {
                    return "[" + (K)sortedByKeyArray.get(sortedByKeyArray.size()-1).getData() + "," +  (V)sortedByKeyArray.get(sortedByKeyArray.size()-1).getCityName() + "]";
                }
            };

        }

        return null;
    }

    /**
     * Removes and returns a key-value mapping associated with
     * the least key in this map, or {@code null} if the map is empty.
     *
     * @return the removed first entry of this map,
     * or {@code null} if this map is empty
     */
    @Override
    public Entry<K, V> pollFirstEntry(){
        myTree = null;
        myTree = new BinarySearchTree();
        final String key = sortedByKeyArray.get(0).getData();
        final String value = sortedByKeyArray.get(0).getCityName();

        for(int i = 1; i < sortedByKeyArray.size(); ++i)
        {
            this.put((K)sortedByKeyArray.get(i).getData(),(V)sortedByKeyArray.get(i).getCityName());
        }
        sortedByKeyArray = null;
        sortedByKeyArray = myTree.getInfArrayList();
        return new Entry<K, V>() {
            @Override
            public K getKey() {
                return (K)key;
            }

            @Override
            public V getValue() {
                return (V)value;
            }

            @Override
            public V setValue(V value) {
                return null;
            }
            @Override
            public String toString()
            {
                return "[" + (K)key + "," +  (V)value + "]";
            }
        };

    }

    /**
     * Removes and returns a key-value mapping associated with
     * the greatest key in this map, or {@code null} if the map is empty.
     *
     * @return the removed last entry of this map,
     * or {@code null} if this map is empty
     */
    @Override
    public Entry<K, V> pollLastEntry() {
        myTree = null;
        myTree = new BinarySearchTree();
        final String key = sortedByKeyArray.get(sortedByKeyArray.size()-1).getData();
        final String value = sortedByKeyArray.get(sortedByKeyArray.size()-1).getCityName();
        for(int i = 0; i < sortedByKeyArray.size()-1; ++i)
        {
            this.put((K)sortedByKeyArray.get(i).getData(),(V)sortedByKeyArray.get(i).getCityName());
        }
        sortedByKeyArray = null;
        sortedByKeyArray = myTree.getInfArrayList();
        return new Entry<K, V>() {
            @Override
            public K getKey() {
                return (K)key;
            }

            @Override
            public V getValue() {
                return (V)value;
            }

            @Override
            public V setValue(V value) {
                return null;
            }
            @Override
            public String toString()
            {
                return "[" + (K)key + "," +  (V)value + "]";
            }
        };

    }

    /**
     * Returns a reverse order view of the mappings contained in this map.
     * The descending map is backed by this map, so changes to the map are
     * reflected in the descending map, and vice-versa.  If either map is
     * modified while an iteration over a collection view of either map
     * is in progress (except through the iterator's own {@code remove}
     * operation), the results of the iteration are undefined.
     * <p>
     * <p>The returned map has an ordering equivalent to
     * <tt>{@link Collections#reverseOrder(Comparator) Collections.reverseOrder}(comparator())</tt>.
     * The expression {@code m.descendingMap().descendingMap()} returns a
     * view of {@code m} essentially equivalent to {@code m}.
     *
     * @return a reverse order view of this map
     */
    @Override
    public NavigableMap<K, V> descendingMap() {

        if(sortedByKeyArray.size()>0)
        {
            NavigableMap<K, V> nm = new TreeMap();
            for(int i = sortedByKeyArray.size()-1; i>= 0; --i)
            {
                nm.put((K)sortedByKeyArray.get(i).getData(),(V)sortedByKeyArray.get(i).getCityName()) ;
            }
            return nm;
        }
        return  null;
    }

    /**
     * Returns a {@link NavigableSet} view of the keys contained in this map.
     * The set's iterator returns the keys in ascending order.
     * The set is backed by the map, so changes to the map are reflected in
     * the set, and vice-versa.  If the map is modified while an iteration
     * over the set is in progress (except through the iterator's own {@code
     * remove} operation), the results of the iteration are undefined.  The
     * set supports element removal, which removes the corresponding mapping
     * from the map, via the {@code Iterator.remove}, {@code Set.remove},
     * {@code removeAll}, {@code retainAll}, and {@code clear} operations.
     * It does not support the {@code add} or {@code addAll} operations.
     *
     * @return a navigable set view of the keys in this map
     */
    @Override
    public NavigableSet<K> navigableKeySet(){
        if(sortedByKeyArray.size()>0)
        {
            NavigableSet<K> ns = new TreeSet<K>();
            for(int i = 0; i< sortedByKeyArray.size(); ++i)
            {
                ns.add((K)sortedByKeyArray.get(i).getData());
            }
            return ns;
        }
        return  null;
    }

    /**
     * Returns a reverse order {@link NavigableSet} view of the keys contained in this map.
     * The set's iterator returns the keys in descending order.
     * The set is backed by the map, so changes to the map are reflected in
     * the set, and vice-versa.  If the map is modified while an iteration
     * over the set is in progress (except through the iterator's own {@code
     * remove} operation), the results of the iteration are undefined.  The
     * set supports element removal, which removes the corresponding mapping
     * from the map, via the {@code Iterator.remove}, {@code Set.remove},
     * {@code removeAll}, {@code retainAll}, and {@code clear} operations.
     * It does not support the {@code add} or {@code addAll} operations.
     *
     * @return a reverse order navigable set view of the keys in this map
     */
    @Override
    public NavigableSet<K> descendingKeySet() {
       if(sortedByKeyArray.size()>0)
       {
           NavigableSet<K> ns = new TreeSet<K>();
           for(int i = sortedByKeyArray.size()-1; i>= 0; --i)
           {
                ns.add((K)sortedByKeyArray.get(i).getData());
           }
           return ns.descendingSet();
       }
       return  null;
    }

    /**
     * Returns a view of the portion of this map whose keys range from
     * {@code fromKey} to {@code toKey}.  If {@code fromKey} and
     * {@code toKey} are equal, the returned map is empty unless
     * {@code fromInclusive} and {@code toInclusive} are both true.  The
     * returned map is backed by this map, so changes in the returned map are
     * reflected in this map, and vice-versa.  The returned map supports all
     * optional map operations that this map supports.
     * <p>
     * <p>The returned map will throw an {@code IllegalArgumentException}
     * on an attempt to insert a key outside of its range, or to construct a
     * submap either of whose endpoints lie outside its range.
     *
     * @param fromKey       low endpoint of the keys in the returned map
     * @param fromInclusive {@code true} if the low endpoint
     *                      is to be included in the returned view
     * @param toKey         high endpoint of the keys in the returned map
     * @param toInclusive   {@code true} if the high endpoint
     *                      is to be included in the returned view
     * @return a view of the portion of this map whose keys range from
     * {@code fromKey} to {@code toKey}
     * @throws ClassCastException       if {@code fromKey} and {@code toKey}
     *                                  cannot be compared to one another using this map's comparator
     *                                  (or, if the map has no comparator, using natural ordering).
     *                                  Implementations may, but are not required to, throw this
     *                                  exception if {@code fromKey} or {@code toKey}
     *                                  cannot be compared to keys currently in the map.
     * @throws NullPointerException     if {@code fromKey} or {@code toKey}
     *                                  is null and this map does not permit null keys
     * @throws IllegalArgumentException if {@code fromKey} is greater than
     *                                  {@code toKey}; or if this map itself has a restricted
     *                                  range, and {@code fromKey} or {@code toKey} lies
     *                                  outside the bounds of the range
     */
    @Override
    public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
        ArrayList<Node<String,String >> tempArr = myTree.getInfArrayList();
        NavigableMap<K,V> tempNav = new TreeMap<K, V>();
        for(int i = 0 ; i < tempArr.size(); ++i)
        {
            if(fromInclusive==true && toInclusive==true && tempArr.get(i).getData().hashCode()>=fromKey.hashCode() && tempArr.get(i).getData().hashCode()<=toKey.hashCode())
            {
                tempNav.put((K)tempArr.get(i).getData(),(V)tempArr.get(i).getCityName());
            }
            else if(fromInclusive==true && toInclusive==false && tempArr.get(i).getData().hashCode()>=fromKey.hashCode() && tempArr.get(i).getData().hashCode()<toKey.hashCode())
            {
                tempNav.put((K)tempArr.get(i).getData(),(V)tempArr.get(i).getCityName());
            }
            else if(fromInclusive==false && toInclusive==true && tempArr.get(i).getData().hashCode()>=fromKey.hashCode() && tempArr.get(i).getData().hashCode()<=toKey.hashCode())
            {
                tempNav.put((K)tempArr.get(i).getData(),(V)tempArr.get(i).getCityName());
            }
            else if(fromInclusive==false && toInclusive==false && tempArr.get(i).getData().hashCode()>fromKey.hashCode() && tempArr.get(i).getData().hashCode()<toKey.hashCode())
            {
                tempNav.put((K)tempArr.get(i).getData(),(V)tempArr.get(i).getCityName());
            }
        }

        return tempNav;
    }


    private void sortArrayList(ArrayList<Node<String,String>> arr)
    {
        for(int i = 1; i < arr.size(); ++i)
        {
            for(int j = 0; j < arr.size()-i; ++j)
            {
                if (arr.get(j).getData().hashCode() > arr.get(j+1).getData().hashCode())
                {
                    Node<String,String> tempNode = arr.get(j);
                    arr.get(j).setData(arr.get(j+1).getData());
                    arr.get(j).setCityName(arr.get(j+1).getCityName());
                    arr.get(j+1).setData(tempNode.getData());
                    arr.get(j+1).setCityName(tempNode.getCityName());
                }
            }
        }
    }

    /**
     * Returns a view of the portion of this map whose keys are less than (or
     * equal to, if {@code inclusive} is true) {@code toKey}.  The returned
     * map is backed by this map, so changes in the returned map are reflected
     * in this map, and vice-versa.  The returned map supports all optional
     * map operations that this map supports.
     * <p>
     * <p>The returned map will throw an {@code IllegalArgumentException}
     * on an attempt to insert a key outside its range.
     *
     * @param toKey     high endpoint of the keys in the returned map
     * @param inclusive {@code true} if the high endpoint
     *                  is to be included in the returned view
     * @return a view of the portion of this map whose keys are less than
     * (or equal to, if {@code inclusive} is true) {@code toKey}
     * @throws ClassCastException       if {@code toKey} is not compatible
     *                                  with this map's comparator (or, if the map has no comparator,
     *                                  if {@code toKey} does not implement {@link Comparable}).
     *                                  Implementations may, but are not required to, throw this
     *                                  exception if {@code toKey} cannot be compared to keys
     *                                  currently in the map.
     * @throws NullPointerException     if {@code toKey} is null
     *                                  and this map does not permit null keys
     * @throws IllegalArgumentException if this map itself has a
     *                                  restricted range, and {@code toKey} lies outside the
     *                                  bounds of the range
     */
    @Override
    public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
        NavigableMap<K,V> nm = new TreeMap<K, V>();
        if(sortedByKeyArray.size()>0)
        {
            for(int i = 0;i<sortedByKeyArray.size(); ++i){
                if(inclusive==false && sortedByKeyArray.get(i).getData().hashCode()< toKey.hashCode())
                    nm.put((K)sortedByKeyArray.get(i).getData(),(V)sortedByKeyArray.get(i).getCityName());
                else if(inclusive==true && sortedByKeyArray.get(i).getData().hashCode()<= toKey.hashCode())
                    nm.put((K)sortedByKeyArray.get(i).getData(),(V)sortedByKeyArray.get(i).getCityName());
            }
            return nm;
        }
        else
            return null;
    }

    /**
     * Returns a view of the portion of this map whose keys are greater than (or
     * equal to, if {@code inclusive} is true) {@code fromKey}.  The returned
     * map is backed by this map, so changes in the returned map are reflected
     * in this map, and vice-versa.  The returned map supports all optional
     * map operations that this map supports.
     * <p>
     * <p>The returned map will throw an {@code IllegalArgumentException}
     * on an attempt to insert a key outside its range.
     *
     * @param fromKey   low endpoint of the keys in the returned map
     * @param inclusive {@code true} if the low endpoint
     *                  is to be included in the returned view
     * @return a view of the portion of this map whose keys are greater than
     * (or equal to, if {@code inclusive} is true) {@code fromKey}
     * @throws ClassCastException       if {@code fromKey} is not compatible
     *                                  with this map's comparator (or, if the map has no comparator,
     *                                  if {@code fromKey} does not implement {@link Comparable}).
     *                                  Implementations may, but are not required to, throw this
     *                                  exception if {@code fromKey} cannot be compared to keys
     *                                  currently in the map.
     * @throws NullPointerException     if {@code fromKey} is null
     *                                  and this map does not permit null keys
     * @throws IllegalArgumentException if this map itself has a
     *                                  restricted range, and {@code fromKey} lies outside the
     *                                  bounds of the range
     */
    @Override
    public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
        NavigableMap<K,V> nm = new TreeMap<K, V>();
        if(sortedByKeyArray.size()>0)
        {
            for(int i = 0;i< sortedByKeyArray.size(); ++i){
                if(inclusive==false && sortedByKeyArray.get(i).getData().hashCode()> fromKey.hashCode())
                    nm.put((K)sortedByKeyArray.get(i).getData(),(V)sortedByKeyArray.get(i).getCityName());
                else if(inclusive==true && sortedByKeyArray.get(i).getData().hashCode()>= fromKey.hashCode())
                    nm.put((K)sortedByKeyArray.get(i).getData(),(V)sortedByKeyArray.get(i).getCityName());
            }
            return nm;
        }
        else
            return null;
    }



    /**
     * {@inheritDoc}
     * <p>
     * <p>Equivalent to {@code subMap(fromKey, true, toKey, false)}.
     *
     * @param fromKey
     * @param toKey
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    public SortedMap<K, V> subMap(K fromKey, K toKey) {
        ArrayList<Node<String,String >> tempArr = myTree.getInfArrayList();
        NavigableMap<K,V> tempNav = new TreeMap<K, V>();
        for(int i = 0 ; i < tempArr.size(); ++i)
        {
            if(tempArr.get(i).getData().hashCode()>=fromKey.hashCode() && tempArr.get(i).getData().hashCode()<toKey.hashCode())
                tempNav.put((K)tempArr.get(i).getData(),(V)tempArr.get(i).getCityName());
        }

        return tempNav;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>Equivalent to {@code headMap(toKey, false)}.
     *
     * @param toKey
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    public SortedMap<K, V> headMap(K toKey){
        NavigableMap<K,V> nm = new TreeMap<K, V>();
        if(sortedByKeyArray.size()>0)
        {
            for(int i = 0;i<sortedByKeyArray.size(); ++i){
                if(sortedByKeyArray.get(i).getData().hashCode()< toKey.hashCode())
                    nm.put((K)sortedByKeyArray.get(i).getData(),(V)sortedByKeyArray.get(i).getCityName());
            }
            return nm;
        }
        else
            return null;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>Equivalent to {@code tailMap(fromKey, true)}.
     *
     * @param fromKey
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    public SortedMap<K, V> tailMap(K fromKey){
        NavigableMap<K,V> nm = new TreeMap<K, V>();
        if(sortedByKeyArray.size()>0)
        {
            for(int i = 0;i< sortedByKeyArray.size(); ++i){
                if( sortedByKeyArray.get(i).getData().hashCode()>= fromKey.hashCode())
                    nm.put((K)sortedByKeyArray.get(i).getData(),(V)sortedByKeyArray.get(i).getCityName());
            }
            return nm;
        }
        else
            return null;
    }

    /**
     * Returns the first (lowest) key currently in this map.
     *
     * @return the first (lowest) key currently in this map
     * @throws NoSuchElementException if this map is empty
     */
    @Override
    public K firstKey() {
        if(sortedByKeyArray.size()>0)
        {
            return (K)sortedByKeyArray.get(0).getData();
        }
        else
            return null;
    }
    @Override
    public Comparator<? super K> comparator() {
        return null;
    }
    /**
     * Returns the last (highest) key currently in this map.
     *
     * @return the last (highest) key currently in this map
     * @throws NoSuchElementException if this map is empty
     */
    @Override
    public K lastKey() {
        if(sortedByKeyArray.size()>0)
        {
            return (K)sortedByKeyArray.get(sortedByKeyArray.size()-1).getData();
        }
        else
            return null;
    }

    public String toString()
    {
        String tempString="";
        Iterator iter = myTree.levelOrderIterator();

        while (iter.hasNext())
        {
            tempString += iter.next();
        }
        return tempString;
    }

}
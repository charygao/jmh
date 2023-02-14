
package org.openjdk.jmh.util;


import java.util.Collection;
import java.util.Map;

/**
 * Basic Multimap.
 *
 * @param <K> key type
 * @param <V> value type
 */
public interface Multimap<K, V> {

    /**
     * Put the element pair.
     *
     * @param key key
     * @param value value
     */
    void put(K key, V value);

    /**
     * Put multiple pairs.
     * @param k key
     * @param vs values
     */
    void putAll(K k, Collection<V> vs);

    /**
     * Get all values associated with the key
     * @param key key
     * @return collection of values
     */
    Collection<V> get(K key);

    /**
     * Get all associations of the multimap.
     * The method is intended for read-only view.
     * @return entry set of the multimap
     */
    Collection<Map.Entry<K, Collection<V>>> entrySet();

    /**
     * Checks if multimap is empty
     * @return true, if empty
     */
    boolean isEmpty();

    /**
     * Clears the multimap
     */
    void clear();

    /**
     * Keys in the map
     * @return collection of keys
     */
    Collection<K> keys();

    Collection<V> values();

    void remove(K key);

    void merge(Multimap<K, V> other);
}

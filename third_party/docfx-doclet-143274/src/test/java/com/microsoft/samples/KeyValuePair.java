package com.microsoft.samples;

/**
 * The type Key value pair.
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 */
public class KeyValuePair<K, V> {

  private K key;
  private V value;

    /**
     * Instantiates a new Key value pair.
     */
    public KeyValuePair() {}

    /**
     * Instantiates a new Key value pair.
     *
     * @param key   the key
     * @param value the value
     */
    public KeyValuePair(K key, V value) {
    this.key = key;
    this.value = value;
  }

    /**
     * Gets key.
     *
     * @return the key
     */
    public K getKey() {
    return key;
  }

    /**
     * Gets value.
     *
     * @return the value
     */
    public V getValue() {
    return value;
  }
}

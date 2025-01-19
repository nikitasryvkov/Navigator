package com.example.DataStructure;

public interface MyMap<K, V> {
    void put(K key, V value);

    V get(K key);

    V remove(K key);

    int size();

    boolean contains(K value);

    boolean isEmpty();

    Iterable<V> values();

    boolean containsKey(K key);

    boolean containsValue(V value);

    int getOrder(K key);
}

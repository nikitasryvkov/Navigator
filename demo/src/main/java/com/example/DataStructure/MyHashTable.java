package com.example.DataStructure;

import java.util.Collection;
import java.util.Iterator;

public class MyHashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private MyLinkedList<KeyValue<K, V>>[] table;
    private int size;
    private int capacity;

    public MyHashTable() {
        this.table = new MyLinkedList[DEFAULT_CAPACITY];
        this.size = 0;
        this.capacity = DEFAULT_CAPACITY;
    }

    public void add(K key, V value) {
        growIfNeeded();

        int bucketNumber = findBucketNumber(key);
        if (table[bucketNumber] == null) {
            table[bucketNumber] = new MyLinkedList<>();
        }

        table[bucketNumber].add(new KeyValue<>(key, value));
        size++;
    }

    public V remove(K key) {
        int bucketNumber = findBucketNumber(key);
        MyLinkedList<KeyValue<K, V>> requiredBucket = table[bucketNumber];

        Iterator<KeyValue<K, V>> iterator = requiredBucket.iterator();
        while (iterator.hasNext()) {
            KeyValue<K, V> keyValue = iterator.next();

            if (keyValue.getKey().equals(key)) {
                iterator.remove();
                size--;
                return keyValue.getValue();
            }
        }

        return null;
    }

    public V get(K key) {
        int bucketNumber = findBucketNumber(key);
        MyLinkedList<KeyValue<K, V>> requiredBucket = table[bucketNumber];

        for (KeyValue<K, V> keyValue : requiredBucket) {
            if (keyValue.getKey().equals(key)) {
                return keyValue.getValue();
            }
        }

        return null;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public int size() {
        return size;
    }

    public Iterable<V> values() {
        MyLinkedList<V> values = new MyLinkedList<>();
        for (MyLinkedList<KeyValue<K, V>> list : table) {
            if (list != null) {
                for (KeyValue<K, V> keyValue : list) {
                    values.add(keyValue.getValue());
                }
            }
        }

        return values();
    }

    private int findBucketNumber(K key) {
        return key.hashCode() % capacity;
    }

    private void growIfNeeded() {
        if (size >= capacity * LOAD_FACTOR) {
            grow();
        }
    }

    private void grow() {
        capacity = capacity * 2;
        MyLinkedList<KeyValue<K, V>>[] newTable = new MyLinkedList[capacity];

        for (MyLinkedList<KeyValue<K, V>> list : table) {
            if (list != null) {
                for (KeyValue<K, V> keyValue : list) {
                    int newBucketNumber = findBucketNumber(keyValue.getKey());
                    if (newTable[newBucketNumber] == null) {
                        newTable[newBucketNumber] = new MyLinkedList<>();
                    }

                    newTable[newBucketNumber].add(keyValue);
                }
            }
        }

        table = newTable;
    }
}

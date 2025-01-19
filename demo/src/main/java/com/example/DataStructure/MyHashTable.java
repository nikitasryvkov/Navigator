package com.example.DataStructure;

import java.util.Iterator;

public class MyHashTable<K, V> implements MyMap<K, V> {
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

    @Override
    public void put(K key, V value) {
        growIfNeeded();

        int bucketNumber = findBucketNumber(key);
        if (table[bucketNumber] == null) {
            table[bucketNumber] = new MyLinkedList<>();
        }

        table[bucketNumber].add(new KeyValue<>(key, value, size));
        size++;
    }

    @Override
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

    @Override
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

    public int getOrder(K key) {
        return this.find(key).getEntryOrder();
    }

    public KeyValue<K, V> find(K key) {
        int bucketNumber = findBucketNumber(key);
        MyLinkedList<KeyValue<K, V>> requiredBucket = table[bucketNumber];

        for (KeyValue<K, V> keyValue : requiredBucket) {
            if (keyValue.getKey().equals(key)) {
                return keyValue;
            }
        }

        return null;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterable<V> values() {
        return () -> new Iterator<>() {
            int bucketIndex = 0;
            Iterator<KeyValue<K, V>> entryIterator = (table[0] == null) ? null : table[0].iterator();

            @Override
            public boolean hasNext() {
                while (bucketIndex < table.length && (entryIterator == null || !entryIterator.hasNext())) {
                    bucketIndex++;
                    if (bucketIndex < table.length) {
                        entryIterator = (table[bucketIndex] == null) ? null : table[bucketIndex].iterator();
                    }
                }
                return entryIterator != null && entryIterator.hasNext();
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new IllegalStateException("No more elements");
                }
                return entryIterator.next().getValue();
            }
        };
    }

    private int findBucketNumber(K key) {
        return Math.abs(key.hashCode()) % capacity;
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

    @Override
    public boolean contains(K key) {
        int index = findBucketNumber(key);
        MyLinkedList<KeyValue<K, V>> bucket = table[index];

        for (KeyValue<K, V> keyValue : bucket) {
            if (keyValue.getKey().equals(key)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (MyLinkedList<KeyValue<K, V>> bucket : table) {
            for (KeyValue<K, V> keyValue : bucket) {
                if (keyValue.getValue().equals(value)) {
                    return true;
                }
            }
        }

        return false;
    }
}

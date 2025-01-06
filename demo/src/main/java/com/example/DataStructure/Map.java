package com.example.DataStructure;

public class Map<K, V> {
    private MyHashTable<K, V> hashTable;

    public Map() {
        this.hashTable = new MyHashTable<>();
    }

    public void put(K key, V value) {
        hashTable.add(key, value);
    }

    public V get(K key) {
        return hashTable.remove(key);
    }

    public V remove(K key) {
        return hashTable.remove(key);
    }

    public int size() {
        return hashTable.size();
    }

    public boolean contains(K value) {
        return hashTable.containsKey(value);
    }

    public Iterable<V> values() {
        return hashTable.values();
    }
}

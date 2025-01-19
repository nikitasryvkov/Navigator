package com.example.DataStructure;

import java.util.Objects;

public class KeyValue<K, V> {
    private K key;
    private V value;
    private Integer entryOrder;

    public KeyValue(K key, V value, Integer entryOrder) {
        this.key = key;
        this.value = value;
        this.entryOrder = entryOrder;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Integer getEntryOrder() {
        return entryOrder;
    }

    public void setEntryOrder(Integer entryOrder) {
        this.entryOrder = entryOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        KeyValue<?, ?> KeyValue = (KeyValue<?, ?>) o;
        return Objects.equals(key, KeyValue.key) && Objects.equals(value, KeyValue.value);
    }

    @Override
    public int hashCode() {
        return this.combineHashCodes(this.getKey().hashCode(), this.getValue().hashCode());
    }

    private int combineHashCodes(int h1, int h2) {
        return ((h1 << 5) + h1) ^ h2;
    }
}

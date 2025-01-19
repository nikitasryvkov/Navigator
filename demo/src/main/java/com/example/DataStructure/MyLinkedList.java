package com.example.DataStructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }

        size++;
    }

    public boolean remove(T data) {
        if (head == null) {
            return false;
        }

        if (head.getData().equals(data)) {
            head = head.getNext();
            size--;

            if (head == null) {
                tail = null;
            }

            return true;
        }

        Node<T> current = head;
        while (current.getNext() != null && !current.getNext().getData().equals(data)) {
            current = current.getNext();
        }

        if (current.getNext() != null) {

            if (current.getNext() == tail) {
                tail = current;
            }

            current.setNext(current.getNext().getNext());
            size--;
            return true;
        }

        return false;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator<>(head, this);
    }

    private static class MyLinkedListIterator<T> implements Iterator<T> {
        private Node<T> current;
        private Node<T> previous;
        private Node<T> lastReturned;
        private final MyLinkedList<T> list;

        public MyLinkedListIterator(Node<T> head, MyLinkedList<T> list) {
            this.current = head;
            this.previous = null;
            this.lastReturned = null;
            this.list = list;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            lastReturned = current;
            current = current.getNext();

            if (lastReturned != list.head) {
                previous = lastReturned;
            }

            return lastReturned.getData();
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException("remove() может быть вызван только один раз после next()");
            }

            if (lastReturned == list.head) {
                list.head = list.head.getNext();
                if (list.head == null) {
                    list.tail = null;
                }
            } else {
                previous.setNext(current);
                if (lastReturned == list.tail) {
                    list.tail = previous;
                }
            }

            list.size--;
            lastReturned = null;
        }
    }
}

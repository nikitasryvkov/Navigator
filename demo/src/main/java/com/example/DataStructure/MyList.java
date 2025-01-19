package com.example.DataStructure;

import java.util.Comparator;

public interface MyList<E> extends Iterable<E> {
    void add(E element);

    E get(int index);

    void remove(int index);

    boolean contains(E element);

    int size();

    int indexOf(E element);

    void sort(Comparator<? super E> comparator);

    MyList<E> subList(int start, int end);

    static <E> MyArrayList<E> of(E... elements) {
        if (elements == null) {
            throw new NullPointerException("Элементы не могут быть нулевыми");
        }
        return new MyArrayList<>(elements);
    }

    boolean addAll(Iterable<? extends E> iterable);
}

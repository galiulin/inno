package lesson_03;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MyArrayList<E> implements Collection<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] arr = {};

    private int size;

    public MyArrayList() {
        arr = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) arr = new Object[initialCapacity];
        else if (initialCapacity == 0) arr = new Object[DEFAULT_CAPACITY];
        else throw new IllegalArgumentException(String.format("Illegal capacity: %d", initialCapacity));
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }


    public Object[] toArray() {
        return this.arr;
    }

    public <T> T[] toArray(T[] a) {
        return null;
    }

    public boolean add(E e) {
        if (size < arr.length - 1) {
            arr[size++] = e;
        } else {
            addSize();
            arr[size++] = e;
        }

        return true;
    }

    private boolean addSize(){
        Arrays.copyOf(arr, (int) ((size * 1.5) + 1));
        return true;
    }

    public boolean remove(Object o) {

        return false;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        return false;
    }

    public boolean removeIf(Predicate<? super E> filter) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public void clear() {

    }

    public Spliterator<E> spliterator() {
        return null;
    }

    public Stream<E> stream() {
        return null;
    }

    public Stream<E> parallelStream() {
        return null;
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (arr[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(arr[i]))
                    return i;
        }
        return -1;
    }

    public Iterator<E> iterator() {
        return null;
    }

}

package lesson_03;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class MyArrayList<E> implements List<E> {

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

    //TODO
    public boolean remove(Object o) {

        return false;
    }

    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
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
        arr = new Object[DEFAULT_CAPACITY];
    }

    //ignored
    public Spliterator<E> spliterator() {
        return null;
    }

    //ignored
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

   //ignored
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {

    }

    @Override
    public void sort(Comparator<? super E> c) {

    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheck(index);
        return false;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return (E) arr[index];
    }

    private void rangeCheck(int index){
        if (index >= size) throw new IndexOutOfBoundsException();
    }

    @Override
    public E set(int index, E element) {
        E oldObj = (E) arr[index];
        arr[index] = element;
        return oldObj;
    }

    @Override
    public void add(int index, E element) {
        rangeCheck(index);
    }

    //TODO
    private void moveForwardFrom(int index){

    }

    //TODO
    @Override
    public E remove(int index) {
        return null;
    }

    //TODO
    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    //TODO
    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    //TODO
    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    //TODO
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}


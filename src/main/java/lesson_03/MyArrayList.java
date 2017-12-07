package lesson_03;

import exceptions.IgnoredMethod;

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
        Object[] temp = new Object[size];
        System.arraycopy(arr, 0, temp, 0, size);
        return temp;
    }

    //fixme
    public <T> T[] toArray(T[] a) {
        T[] arr = (T[]) new Object[10]; //example
        throw new IgnoredMethod();
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

    //done
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (arr[i] == null) {
                    fastRemove(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(arr[i])) {
                    fastRemove(i);
                    return true;
                }
            }
        }
        return false;
    }

    private Object fastRemove(int index) {
        Object o = arr[index];
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(arr, index + 1, arr, index, numMoved);
        arr[--size] = null;
        return o;
    }


    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    //todo check in junit test
    public boolean containsAll(Collection<?> c) {
        Iterator<?> iterator = c.iterator();
        while (iterator.hasNext()){
            Object obj = iterator.next();
            if (!contains(obj)) return false;
        }
        return true;
    }


    public boolean addAll(Collection<? extends E> c) {
        Iterator iterator = c.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            add((E) obj);
        }
        return true;
    }

    public boolean removeAll(Collection<?> c) {
        Iterator iterator = c.iterator();
        while (iterator.hasNext()){
            Object obj = iterator.next();
            remove(obj);
        }
        return true;
    }

    //todo
    public boolean removeIf(Predicate<? super E> filter) {
        return false;
    }

    //todo
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public void clear() {
        arr = new Object[DEFAULT_CAPACITY];
    }

    //ignored
    public Spliterator<E> spliterator() {
        throw new IgnoredMethod();
//        return null;
    }

    //ignored
    public Stream<E> stream() {
        throw new IgnoredMethod();
//        return null;
    }

    //ignored
    public Stream<E> parallelStream() {
        throw new IgnoredMethod();
//        return null;
    }

    //todo check in junit
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
        throw new IgnoredMethod();
//        return null;
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        throw new IgnoredMethod();
    }

    @Override
    public void sort(Comparator<? super E> c) {
        throw new IgnoredMethod();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheck(index);
        for (Object obj : c) {
            add((E) obj);
        }
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

    //todo
    @Override
    public void add(int index, E element) {
        rangeCheck(index);
        moveForwardFrom(index);
    }

    //fixme
    private void moveForwardFrom(int index){
        for (int i = index; i < size; i++) {

        }
    }

    //todo check with junit
    @Override
    public E remove(int index) {
        E obj =(E) fastRemove(index);
        arr[--size] = null;
        return obj;
    }

    //done
    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size; i >= 0; i--) {
                if (arr[i] == null) return i;
            }
        } else {
            for (int i = size; i >= 0; i--) {
                if (o.equals(arr[i])) return i;
            }
        }
        return -1;
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


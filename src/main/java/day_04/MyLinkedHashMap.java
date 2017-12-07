package day_04;

import java.time.temporal.ValueRange;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MyLinkedHashMap<K, V> implements Map {


    @Override
    public Object getOrDefault(Object key, Object defaultValue) {
        return null;
    }

    @Override
    public void forEach(BiConsumer action) {

    }

    @Override
    public void replaceAll(BiFunction function) {

    }

    @Override
    public Object putIfAbsent(Object key, Object value) {
        return null;
    }

    @Override
    public boolean remove(Object key, Object value) {
        return false;
    }

    @Override
    public boolean replace(Object key, Object oldValue, Object newValue) {
        return false;
    }

    @Override
    public Object replace(Object key, Object value) {
        return null;
    }

    @Override
    public Object computeIfAbsent(Object key, Function mappingFunction) {
        return null;
    }

    @Override
    public Object computeIfPresent(Object key, BiFunction remappingFunction) {
        return null;
    }

    @Override
    public Object compute(Object key, BiFunction remappingFunction) {
        return null;
    }

    @Override
    public Object merge(Object key, Object value, BiFunction remappingFunction) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        return null;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry> entrySet() {
        return null;
    }

    protected static class EntrySet<K, V>{

        EntrySet<K, V> cursor;

        private K key;
        private V value;

        EntrySet<K, V> next;
        EntrySet<K, V> previos;

        private EntrySet(K key, V value){
            this.key = key;
            this.value = value;
            cursor = this;
        }

        public void addEntry(K key, V value){
            next = new EntrySet<>(key, value);
            next.setPrevios(this);
        }

        public boolean isHasNext(){
            return next != null;
        }

        public boolean isHasPrevios(){
            return previos != null;
        }

        public EntrySet<K, V> next(){
            if (!isHasNext()) throw new NoSuchElementException();
            return next;
        }

        public EntrySet<K, V> previos() {
            if (!isHasPrevios()) throw new NoSuchElementException();
            return previos;
        }

        public K getKey(){
            return key;
        }

        public V getValue(){
            return value;
        }

        private void setPrevios(EntrySet<K, V> previos){
            this.previos = previos;
        }

        protected void deleteElement() {
            if (isHasNext()) this.next.previos = this.previos;
            if (isHasPrevios()) this.previos.next = this.next;
        }
    }

    public static void main(String[] args) {
        EntrySet<Integer, String> entrySet = new EntrySet<>(1, "one");
        entrySet.addEntry(2, "two");
        entrySet.addEntry(3, "three");
        entrySet.addEntry(4, "four");
        entrySet.addEntry(5, "five");
        while (entrySet.isHasNext()){
            System.out.println(entrySet.next);
        }
    }
}

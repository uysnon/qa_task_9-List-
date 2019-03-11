import java.util.*;

public class StockArrayList <T> implements List<T> {
    private  final int DEFAULT_LENGTH = 10;
    private  int capacity;
    private int size;
    private Object[] elements;


    @Override
    public  int size(){
        return elements.length;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null){
            for (int i = 0; i < size; i++){
                if (elements[i] == o) return true;
            }
            return false;
        }
        for (int i =0; i< size; i++){
            if (elements[i].equals(o)) return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int current = 0;
            @Override
            public boolean hasNext() {
                if (current < size) return true;
                return false;
            }

            @Override
            public T next() {
                T element = (T)elements[current];
                current++;
                return element;
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements,size);
    }

    @Override
    public boolean add(T o) {
        if (isListFull()) updateCapacityArray();
        elements[size] = o;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null){
            for (int i = 0; i < size; i++){
                if (elements[i] == o) {
                    privateRemove(i);
                    return true;
                }
            }
            return false;
        }
        for (int i = 0; i< size; i++){
            if (elements[i].equals(o)){
                privateRemove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {
        for(int i =0; i< size; i++){
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }



    public T get(int index){
        if (!isElementInList(index)) {
            throw  new StockArrayListException();
        }
        return getElement(index);

    }

    @Override
    public T set(int index, T element) {
        if (!isElementInList(index)) {
            throw  new StockArrayListException();
        }
        T el = (T) elements[index];
        elements[index] = element;
        return el;

    }

    @Override
    public void add(int index, T element) {
        if (!isElementInList(index)){
            throw new StockArrayListException();
        }
        if (isListFull()) {updateCapacityArray();}
        int copyLength = size - index;
        System.arraycopy(elements,index,elements,index+1, copyLength);
        elements[index] = element;
    }

    @Override
    public T remove(int index) {
        if (!isElementInList(index)){
            throw new StockArrayListException();
        }
        T object = getElement(index);
        int copyLength = size - index -1;
        System.arraycopy(elements, index+1, elements,index,copyLength);
        return object;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null){
            for (int i = 0; i < size; i++){
                if (elements[i] == o) return i;
            }
        }
        for (int i = 0; i< size; i++){
            if (elements[i].equals(o)) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null){
            for (int i = size-1; i >= 0; i--){
                if (elements[i] == o) return i;
            }
        }
        for (int i = size-1; i >= 0; i--){
            if (elements[i].equals(o)) return i;
        }
        return -1;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
       return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }


    public StockArrayList() {
        elements = new Object[DEFAULT_LENGTH];
    }


    public StockArrayList(Object[] elements) {
        elements = Arrays.copyOf(elements, elements.length * 3 / 2 + 1);

    }

    private boolean isListFull(){
        if (size >= capacity) return true;
        return false;
    }
    private void newCapacity(){
        capacity = capacity*3/2+1;
    }
    private void updateCapacityArray(){
        Object[] helpArray = Arrays.copyOf(elements,size);
        newCapacity();
        elements = new Object[capacity];
        System.arraycopy(helpArray,0,elements,0,helpArray.length);
    }

    private  void privateRemove(int index){
        elements[index] = null;
        int numCopy = size - index -1;
        System.arraycopy(elements,index+1,elements,index, numCopy);
        size --;
        elements[size] = null;
    }

    private T getElement(int index){
        return (T)elements[index];
    }

    private boolean isElementInList(int index){
        if (index< 0 || index >= size) return false;
        return true;
    }
}

class StockArrayListException extends RuntimeException{
}

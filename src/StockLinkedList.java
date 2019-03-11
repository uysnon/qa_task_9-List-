import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class StockLinkedList<T> implements List<T> {

    private int size = 0;
    private Node<T> last;
    private Node<T> first;


    private static class Node<T>{
        Node<T> next;
        Node<T> prev;
        T value;

        public Node( Node<T> prev, Node<T> next, T value) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }
    }

    private void unlink(Node<T> x) {
        final T el = x.value;
        final Node<T> next = x.next;
        final Node<T> prev = x.prev;

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        x.value = null;
        size--;
    }

    private Node<T> privateGet(int index){
        if (!isValidIndex(index)){
            throw  new StockLinkedListException();
        }
        if (index < size/2){
            int i = 0;
            Node<T> x = first;
            for ( ; i < index; i++ , x = x.next ){}
            return x;
            }
        else{
            int i = 0;
            Node<T> x = last;
            for ( ; i < size - index; i++ , x = x.prev ){}
            return x;
            }
    }

    private void privateAddLast(T t){
        final Node<T> l = last;
        final Node<T> newNode = new Node<>(l, null, t);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }

    private void privateAddBefore(T t, Node<T> nodeBefore){
        Node<T> prev = nodeBefore.prev;
        Node<T>  next = nodeBefore;
        Node<T> newNode = new Node<T> (prev, next, t);
        if (prev != null){ prev.next = newNode;}
        else {first = newNode;}
        next.prev = newNode;
        size++;

    }

    private  boolean isValidIndex(int index){
        if (index >= 0 && index < size) return true;
        return false;
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
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> current = first;
            int index = 0;
            @Override
            public boolean hasNext() {
                return (index < size);
            }

            @Override
            public T next()
            {
                T res =  current.value;
                index++;
                current = current.next;
                return res;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] res  = new Object[size];
        int k = 0;
        for (Node<T> i = first; i != null; i = i.next){
            res[k++] = i.value;
        }
        return res;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
      privateAddLast(t);
      return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<T> i = first; i != null; i = i.next) {
                if (i.value == o) {
                    unlink(i);
                    return true;
                }
            }
        }
        for (Node<T> i = first; i != null; i = i.next) {
            if (i.value.equals(o)) {
                unlink(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        for (Node <T> i = first; i != null;){
            Node<T> help = i.next;
            i.value = null;
            i.prev = null;
            i.next = null;
            i = help;
        }
    }

    @Override
    public T get(int index) {
        return privateGet(index).value;
    }

    @Override
    public T set(int index, T element) {
        if (!isValidIndex(index)){
            throw  new StockLinkedListException();
        }
        Node<T> x = privateGet(index);
        T oldValue = x.value;
        x.value = element;
        return oldValue;

    }

    @Override
    public void add(int index, T element) {
        if (!isValidIndex(index)){
            throw  new StockLinkedListException();
        }
        if (index == size)
            privateAddLast(element);
        else
            privateAddBefore(element, privateGet(index));
    }

    @Override
    public T remove(int index) {
        unlink(privateGet(index));
        return privateGet(index).value;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
class StockLinkedListException extends RuntimeException{

}

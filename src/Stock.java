import java.util.*;
import java.util.function.Consumer;

 public class Stock<T> implements Iterable<T>{

     private Object [] sections;
     @Override
     public Iterator iterator() {
        return new IteratorStock();
    }

    protected class IteratorStock<T>  implements Iterator{
        int current = 0;

        public IteratorStock() {
        }

        @Override
        public boolean hasNext() {
           if ((current >=0)&&(current < sections.length)) return true;
           return false;
        }

        @Override
        public Object next() {
            return (T) sections[current++];
        }
    }

     public Stock(T[] sections) {
         this.sections = sections;
     }

     public Object[] getSections() {
         return sections;
     }
 }

class Section{
     String name;
    List<Product> products;
    public Section(List<Product> products, String name) {
        this.products = products;
        this.name = name;
    }
}

 class Product {
    private String name;
    private int quantity;

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}


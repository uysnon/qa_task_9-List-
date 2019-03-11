import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;

public class Main {
    private final static int NUM_ELEMENTS = 10000;
    public static void main(String[] args) {
        StockArrayList  <Product> sectionArrayList = new StockArrayList<>();
        StockLinkedList  <Product> sectionLinkedArrayList = new StockLinkedList<>();

        for (int i = 0; i<NUM_ELEMENTS; i++){
            if (i%2 == 0)  sectionArrayList.add(new Product("Чай черный", i));
            else sectionArrayList.add(new Product("Чай зеленый", i));
        }

        for (int i = 0; i<NUM_ELEMENTS; i++){
            if (i%2 == 0)  sectionLinkedArrayList.add(new Product("Морковь", i));
            else sectionLinkedArrayList.add(new Product("Картошка", i));
        }

        Section section1 = new Section(sectionLinkedArrayList, "Овощи (stockLinkedList)");
        Section section2 =  new Section(sectionArrayList, "Чай (stockArrayList)");

        Section[] sections = {section1, section2};
        Stock <Section> stock = new Stock <Section>(sections);

        System.out.println("Склад N\n");

        //Обращение к каждому элементу списков с помощью   for-each
        for (Section section: stock){
            System.out.println("РАЗДЕЛ: " + section.name);
            System.out.println("Список товаров:");
            long time  = System.currentTimeMillis();
            for (Product product:  section.products){
                product.getName();
//                System.out.println("  "+product.getName()+ " Кол-во: " + product.getQuantity());
            }

            System.out.println("Здесь должен быть вывод " + NUM_ELEMENTS+ " элементов списка, но он закоментирован");
            System.out.println("ВРЕМЯ, ЗАТРАЧЕННОЕ НА ВЗЯТИЕ И ВЫВОД " + NUM_ELEMENTS + " ЭЛЕМЕНТОВ ИЗ СПИСКА С ПОМОЩЬЮ FOR-EACH " + String.valueOf(System.currentTimeMillis()-time) + " мс");
            System.out.println("__________________________________________________________________________");
            System.out.println();
        }

        //Обращение к случайным элементам массива с помощью get() методов
        System.out.println("ВРЕМЯ, ЗАТРАЧЕННОЕ НА ВЗЯТИЕ " + NUM_ELEMENTS + " СЛУЧАЙНЫХ ЭЛЕМЕНТОВ ИЗ СПИСКОВ:");
        long time  = System.currentTimeMillis();
        for (int i = 0; i < section1.products.size(); i++){
            Random random = new Random();
            int r = random.nextInt(NUM_ELEMENTS-1);
            section1.products.get(r);
        }
        System.out.println("stockLinkedList: " + String.valueOf(System.currentTimeMillis()-time) + " мс" );
        time  = System.currentTimeMillis();
        for (int i = 0; i < section2.products.size(); i++){
            Random random = new Random();
            int r = random.nextInt(NUM_ELEMENTS-1);
            section2.products.get(r);
        }
        System.out.println("stockArrayList: " + String.valueOf(System.currentTimeMillis()-time) + " мс");
    }
}

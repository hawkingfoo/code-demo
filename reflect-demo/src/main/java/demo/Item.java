package demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class Item {
    private String id;
    private String name;
    private double price;
    public double f1;

    public Item(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public static void main(String[] args) throws Exception {
        Class clz1 = Class.forName("java.lang.String"); // 通过包和类名
        Class clz2 = String.class;  // 直接通过类名

        Item item = new Item("1", "a", 1.0);
        Class clz3 = item.getClass(); // 根据对象(运行时类)


        clz3.getEnclosingClass();

        clz1.getDeclaringClass();
        clz1.getDeclaredClasses();

        clz1.getClass();
        clz1.getClasses();

        clz3.getEnclosingConstructor();
        clz1.getEnclosingMethod();
        Constructor[] constructors = clz3.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor.getName());
        }

      Field field;



    }
}

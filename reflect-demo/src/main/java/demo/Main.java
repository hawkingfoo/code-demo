package demo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {

    private static void getFiledFromPerson(Person person, Person.BaseInfo baseInfo) {
        try {
            // 获取外部类的私有属性
            Field idField = person.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            String id = (String) idField.get(person);
            System.out.println("id:" + id);

            Field familyInfoField = person.getClass().getDeclaredField("familyInfo");
            familyInfoField.setAccessible(true);
            Object familyInfo = familyInfoField.get(person);

            // 获取外部类的私有方法
            Method printPersonMethod = person.getClass().getDeclaredMethod("print");
            printPersonMethod.setAccessible(true);
            printPersonMethod.invoke(person);

            // 获取内部类的私有属性
            Class baseInfoClz = Class.forName("demo.Person$BaseInfo");
            Field nationField = baseInfoClz.getDeclaredField("nation");
            nationField.setAccessible(true);
            String nation = (String) nationField.get(baseInfo);
            System.out.println("nation:" + nation);

            Class familyInfoClz = Class.forName("demo.Person$FamilyInfo");
            Field field = familyInfoClz.getDeclaredField("address");
            field.setAccessible(true);
            String address = (String) field.get(familyInfo);
            System.out.println("address:" + address);

            // 获取内部类的私有方法
            Method printBaseInfoMethod = baseInfoClz.getDeclaredMethod("print");
            printBaseInfoMethod.setAccessible(true);
            printBaseInfoMethod.invoke(baseInfo);

            Method printFamilyInfoMethod = familyInfoClz.getDeclaredMethod("print");
            printFamilyInfoMethod.setAccessible(true);
            printFamilyInfoMethod.invoke(familyInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Person person = new Person("1001", "John"); // 初始化Person以及默认FamilyInfo
        Person.BaseInfo baseInfo = person.new BaseInfo("han", "A"); // 初始化BaseInfo
        getFiledFromPerson(person, baseInfo);
    }
}

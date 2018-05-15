package origin;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Students students = new Students();
        List<Person> personList = students.getPersonList();
        for (Person person : personList) {
            System.out.println(person);
        }

        Teachers teachers = new Teachers();
        Person[] persons = teachers.getPersons();
        for (Person person : persons) {
            System.out.println(person);
        }
    }
}

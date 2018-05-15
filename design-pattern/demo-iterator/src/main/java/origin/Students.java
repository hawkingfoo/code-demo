package origin;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private List<Person> personList;

    public Students() {
        this.personList = new ArrayList<Person>();
        personList.add(new Person("S1", "A", false, 18));
        personList.add(new Person("S2", "B", true, 18));
        personList.add(new Person("S3", "C", false, 18));
    }

    public List<Person> getPersonList() {
        return personList;
    }
}

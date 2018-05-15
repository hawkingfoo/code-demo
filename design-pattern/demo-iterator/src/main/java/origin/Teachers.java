package origin;

public class Teachers {
    private Person[] persons;

    public Teachers() {
        this.persons = new Person[3];

        persons[0] = new Person("T1", "X", false, 30);
        persons[1] = new Person("T2", "Y", true, 40);
        persons[2] = new Person("T3", "Z", false, 35);
    }

    public Person[] getPersons() {
        return persons;
    }
}

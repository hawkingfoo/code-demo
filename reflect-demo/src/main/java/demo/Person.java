package demo;

public class Person {
    private String id;
    private String name;

    private FamilyInfo familyInfo;

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
        this.familyInfo = this.new FamilyInfo("beijing", "beijing");
    }

    private void print() {
        System.out.println("Person{id=" + id + ", name=" + name + "}");
    }

    public class BaseInfo {
        private String nation;
        private String bloodType;

        public BaseInfo(String nation, String bloodType) {
            this.nation = nation;
            this.bloodType = bloodType;
        }

        private void print() {
            System.out.println("BaseInfo{nation=" + nation + ", bloodType=" + bloodType + "}");
        }
    }

    private class FamilyInfo {
        private String nativePlace; // 籍贯
        private String address;     // 住址

        private FamilyInfo(String nativePlace, String address) {
            this.nativePlace = nativePlace;
            this.address = address;
        }
        private void print() {
            System.out.println("FamilyInfo{nativePlace=" + nativePlace + ", address=" + address + "}");
        }
    }
}

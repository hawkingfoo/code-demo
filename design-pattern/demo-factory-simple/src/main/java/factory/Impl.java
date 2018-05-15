package factory;

public class Impl implements Phone {
    public void call(String s) {
        System.out.println("impl call: " + s);
    }
}

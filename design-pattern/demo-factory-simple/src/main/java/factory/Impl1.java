package factory;

public class Impl1 implements Phone {
    public void call(String s) {
        System.out.println("impl2 call: " + s);
    }
}

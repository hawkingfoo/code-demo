package factory;

public class Client {
    public static void main(String[] args) throws Exception {
        Phone phone = Factory.createPhone();
        phone.call("hello world");

        Phone phone1 = Factory.createPhone(Impl.class);
        phone1.call("hello world");
    }
}

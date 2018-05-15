package factory;

public class Factory {
    public static Phone createPhone() {
        return new Impl();
    }

    public static Phone createPhone1() {
        return new Impl1();
    }

    public static Phone createPhone(int type) {
        if (type == 1) {
            return new Impl();
        } else if (type == 2) {
            return new Impl1();
        } else {
            return null;
        }
    }

    public static <T extends Phone> T createPhone(Class<T> clz) throws Exception {
        return (T) Class.forName(clz.getName()).newInstance();
    }


}

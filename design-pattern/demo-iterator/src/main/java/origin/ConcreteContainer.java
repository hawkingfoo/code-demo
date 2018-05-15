package origin;

import java.util.List;

public class ConcreteContainer implements Container {
    private List<Object> list;

    public ConcreteContainer(List<Object> list) {
        this.list = list;
    }

    public void add(Object obj) {
        list.add(obj);
    }

    public Iterator createIterator() {
        return new ConcreteIterator();
    }

    private class ConcreteIterator implements Iterator {
        private int cursor;

        public Object next() {
            if (hasNext()) {
                return list.get(cursor++);
            }
            return null;
        }

        public boolean hasNext() {
            return cursor != list.size();
        }
    }
}

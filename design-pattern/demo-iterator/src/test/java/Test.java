import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        Set<String> set = new HashSet<String>(Arrays.asList("a", "b", "c"));
        Map<String, String> map = new HashMap<String, String>();
        map.put("a", "b");


        // 方法 1
        for (Map.Entry<String, String> entry: map.entrySet()) {
            System.out.println(entry.toString());
        }
        // 方法 3
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}

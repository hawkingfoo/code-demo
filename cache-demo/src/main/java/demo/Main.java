package demo;

import com.google.common.cache.Cache;

import java.util.HashMap;
import java.util.Map;

public class Main {
    private static int solution(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int ret = 0;
        int pre1 = 2;
        int pre2 = 1;
        for (int i = 3; i <= n; i++) {
            ret = pre2 + pre1;
            pre2 = pre1;
            pre1 = ret;
        }
        return ret;
    }
    private static String solution(String line) {
        int n = Integer.parseInt(line);
        if (n < 1) {
            return String.valueOf(0);
        }
        if (n == 1 || n == 2) {
            return String.valueOf(n);
        }
        int ret = 0;
        int pre1 = 2;
        int pre2 = 1;
        for (int i = 3; i <= n; i++) {
            ret = pre2 + pre1;
            pre2 = pre1;
            pre1 = ret;
        }
        return String.valueOf(ret);
    }

    public static void main(String[] args) {
        String str = "9";
        System.out.println(solution(str));

        Cache cache1 = LocalCacheManager.getCache("1");
        cache1.put("a", "a");
        System.out.println(cache1.getIfPresent("a"));

        Cache cache2 = LocalCacheManager.getCache("1");
        Map<String, String> map = new HashMap<String, String>();
        map.put("a", "a");
        cache2.put("a", map);

        Cache cache3 = LocalCacheManager.getCache("1");


        System.out.println(cache2.getIfPresent("a"));
        System.out.println(cache3.getIfPresent("a"));
    }
}

package builder;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;

import java.net.URI;

public class Main {
    public static void main(String[] args) throws Exception {
        Student student = new Student.Builder()
                .setId(1)
                .isFemale(false)
                .setName("Li")
                .setAge(18)
                .build();

        String str = new StringBuilder()
                .append("ab")
                .reverse()
                .toString();
        System.out.println(str);
        System.out.println(student.toString());


        test();

    }
    public static void test() throws Exception {
        URI uri = new URIBuilder("http://www.baidu.com")
                .setPath("/hello")
                .setParameter("user", "Li")
                .setParameter("pwd", "123456")
                .build();

        // custom() 实际上返回一个 HttpClientBuilder
        HttpClient client = HttpClients.custom().build();
        System.out.println(client.execute(new HttpGet(uri)));
    }
}


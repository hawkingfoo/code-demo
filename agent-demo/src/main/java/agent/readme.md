
## 一、概述
在前面几节当中，我们构造了多个Agent。Agent由于是在`main`方法之前执行，我们可以做很多事情。
本节中，我们将使用Agent技术来监控应用程序的JVM内存和GC信息。


## 二、实现
### 1、修改pom.xml
```
<dependencies>
<dependency>
    <groupId>javassist</groupId>
    <artifactId>javassist</artifactId>
    <version>3.12.1.GA</version>
    <type>jar</type>
</dependency>
<dependency>
    <groupId>net.bytebuddy</groupId>
    <artifactId>byte-buddy</artifactId>
    <version>1.5.7</version>
</dependency>
<dependency>
    <groupId>net.bytebuddy</groupId>
    <artifactId>byte-buddy-agent</artifactId>
    <version>1.5.7</version>
</dependency>
</dependencies>

<build>
<finalName>my-agent</finalName>
<plugins>
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-jar-plugin</artifactId>
        <version>2.3.2</version>
        <configuration>
            <archive>
                <index>true</index>
                <manifestFile>src/main/resources/META-INF/MANIFEST.MF</manifestFile>
                <manifest>
                    <addDefaultImplementationEntries/>
                </manifest>
            </archive>
        </configuration>
    </plugin>

    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-shade-plugin</artifactId>
        <executions>
            <execution>
                <phase>package</phase>
                <goals>
                    <goal>shade</goal>
                </goals>
            </execution>
        </executions>
        <configuration>
            <artifactSet>
                <includes>
                    <include>javassist:javassist:jar:</include>
                    <include>net.bytebuddy:byte-buddy:jar:</include>
                    <include>net.bytebuddy:byte-buddy-agent:jar:</include>
                </includes>
            </artifactSet>
        </configuration>
    </plugin>

    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <configuration>
            <source>1.7</source>
            <target>1.7</target>
        </configuration>
    </plugin>
</plugins>
</build>
```

### 2、实现一个Agent
Agent的构造比较简单，我们只需要在`premain`方法中，加入一个线程池。其功能是每隔5秒输出一次内存信息和GC信息。
```
public class MyAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("this is an perform monitor agent.");

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
            public void run() {
                Metric.printMemoryInfo();
                Metric.printGCInfo();
            }
        }, 0, 5000, TimeUnit.MILLISECONDS);
    }
}
```

### 3、获取JVM 内存以及GC信息
```
public class Metric {
    private static final long MB = 1048576L;

    public static void printMemoryInfo() {
        MemoryMXBean memory = ManagementFactory.getMemoryMXBean();
        MemoryUsage headMemory = memory.getHeapMemoryUsage();

        String info = String.format("\ninit: %s\t max: %s\t used: %s\t committed: %s\t use rate: %s\n",
                headMemory.getInit() / MB + "MB",
                headMemory.getMax() / MB + "MB", headMemory.getUsed() / MB + "MB",
                headMemory.getCommitted() / MB + "MB",
                headMemory.getUsed() * 100 / headMemory.getCommitted() + "%"

        );

        System.out.print(info);

        MemoryUsage nonheadMemory = memory.getNonHeapMemoryUsage();

        info = String.format("init: %s\t max: %s\t used: %s\t committed: %s\t use rate: %s\n",
                nonheadMemory.getInit() / MB + "MB",
                nonheadMemory.getMax() / MB + "MB", nonheadMemory.getUsed() / MB + "MB",
                nonheadMemory.getCommitted() / MB + "MB",
                nonheadMemory.getUsed() * 100 / nonheadMemory.getCommitted() + "%"

        );
        System.out.println(info);
    }

    public static void printGCInfo() {
        List<GarbageCollectorMXBean> garbages = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean garbage : garbages) {
            String info = String.format("name: %s\t count:%s\t took:%s\t pool name:%s",
                    garbage.getName(),
                    garbage.getCollectionCount(),
                    garbage.getCollectionTime(),
                    Arrays.deepToString(garbage.getMemoryPoolNames()));
            System.out.println(info);
        }
    }
}
```
## 三、运行
```
public class AgentTest {
    
    public static void main(String[] args) throws Exception {
        boolean is = true;
        while (is) {
            List<Object> list = new ArrayList<Object>();
            list.add("hello world");
        }
    }
}
```
运行上述代码时，我们同样加上Agent。`-javaagent:./my-agent.jar`
运行结果：



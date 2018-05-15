### 一、概述
单例模式：是一种对象创建模式，用于产生一个对象的实例。可以确保系统中一个类只产生一个实例。

**好处**：
- 对于频繁使用的对象，可以省略创建对象所花费的时间。
- 由于new操作次数的减少，对系统内存的使用频率会降低，减轻GC压力，缩短GC停顿时间。
- 由于一个类只有一个实例，可以用来保存一些状态值。

**因此**:
对于系统的关键组件和频繁使用的对象，使用单例模式可以有效改善系统性能。

图-----------------------------------

### 二、相关概念
- 静态属性：静态字段的值为一个类的所有实例所共享；在概念上，它的值属于类所有，而不是类的每一个实例/对象所有。
  通俗的讲静态属性的值对类的所有对象来看都是相同的。
- 静态方法：静态属性与类相关联，而不是关联到具体的单个对象；同样，静态方法也可以通过作为整体的类来调用。
  多见于一些工具类。
- 静态属性和方法初始化时机：静态属性和静态方法在**JVM类加载**时完成其初始化过程。
  

### 三、实现条件
- 私有构造方法：一个对象只有一个对象实例，因此不能显示的实例化(new 操作)。
- 存在一个静态属性，且这个属性是这个类的一个对象。
- 使用静态方法获取仅有的这个对象。

### 四、实现方式
 #### 1、饿汉模式（类加载时初始化实例）
 ```
 public class Singleton {
     private Singleton() {} // 条件 1
 
     private static Singleton instance = new Singleton(); // 条件 2
 
     public static Singleton getInstance() { // 条件 3
         return instance;
     }
 }
 ```
 从上述的代码可以看到，其遵从了单例模式的三个条件：**私有的构造方法、静态对象实例以及获取其的静态方法**。
 
 - 饿汉模式的缺点
 
 **先把结论抛出来：饿汉效率差、饿汉效率差、饿汉效率差**
 A: 由于在JVM类加载时完成对象实例化过程，因此类加载速度要慢；
 B: 而且不支持延时加载，虽然并没有使用单例类，但它还是被创建出来了。
 
那我们尝试修改下代码，不让其在JVM类加载时初始化对象，让其支持延时加载。

#### 2、懒汉方式（类加载时不初始化实例，延时加载）
```
public class Singleton {
    private Singleton() {} // 条件 1

    private static Singleton instance; // 条件 2，默认为Null

    public static Singleton getInstance() { // 条件 3
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```
 
 - 为什么称之为**懒汉模式**呢？
 这是因为其静态属性(条件2)初始值为null，只有当第一次构建单例(条件3)时才会构建并返回。
 这里需要注意的是静态方法的初始化时机在类加载，但并非是执行一次此方法。
 
 - 懒汉模式缺点
 
 **先把结论抛出来：懒汉不安全，懒汉不安全，懒汉不安全**
 
 图-----------------------------------
 设想这样一种情景：在Singleton类刚完成初始化，此时instance=null, 这时有两个线程同时通过getInstance()方法获取对象实例。
 当线程1和线程2同时走到上图的位置上，同时判断为instance=null，此时两个线程均执行之后的new操作。
 还有一点是，相对于饿汉模式，懒汉获取instance的速度变慢了。（多了一次判断）
 
 没想到不通过JVM类加载了，竟出现的懒汉线程不安全的现象，那我们尝试修改一下代码。
 

#### 3、增强型懒汉模式
```
public class Singleton {
    private Singleton() {} // 条件 1

    private static Singleton instance; // 条件 2，默认为Null

    public static synchronized Singleton getInstance() { // 条件 3
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```
从代码上看，我们仅仅增加了synchronized同步关键字，就保证了其线程安全性。这样就是最佳实现了吧？
未必，由于方法整体被synchronized同步，而且每次调用只能是单个线程操作，其并发度大大降低。

那应该怎么处理呢？回想我们加synchronized同步关键字的目的？
我们是为了在第一次初始化instance时，防止两个线程同时new操作，才增加的这个关键字。那我们可以在第一次new是使用synchronized同步关键字啊。

那我们再次尝试修改一下代码。

#### 4、双重检测机制（double check）
```
public class Singleton {
    private Singleton() {} // 条件 1

    private static Singleton instance; // 条件 2，默认为Null

    public static Singleton getInstance() { // 条件 3
        if (instance == null) {
            synchronized (Singleton.class) {
                instance = new Singleton();
            }
        }
        return instance;
    }
}
```
我们把synchronized同步关键字加到了判空的后面。
**等一下，这似乎并没有什么卵用啊？在new操作之前加锁，仍然不能改变两个线程new两个实例，只不过是将其new操作在时间上进行了分离。**
既然两个new操作在时间上分离了，那第二次操作我再判空一次？

```
public class Singleton {
    private Singleton() {} // 条件 1

    private static Singleton instance; // 条件 2，默认为Null

    public static Singleton getInstance() { // 条件 3
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```
 
 
 
 




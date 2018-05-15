### 一、概述
Builder模式：主要用于复杂对象的构建，通过使用该模式可以有效地减少构造函数或方法传入的参数数量。
这对于有很多配置项的来初始化的对象非常适用。

通俗的讲，builder模式是一步步地创建一个超级复杂的对象。
它允许用户仅通过复杂对象的类型和内容就可以构建，完全不需要知道其内部细节。

总之，builder模式，不仅可以让构造函数瘦身，对非构造方法同样适用。

### 二、万年不变的学生信息管理
上面的概念看起来还是一头雾水，不知道这个builder模式到底有什么用。
那我们通过一个例子逐步的来说明builder模式的使用方法。

#### 1、起源
有这样一个类，记录了学生信息。其中学号、姓名、性别和年龄为必须项，其他为非必须项。
```
public class Student {
    private int id; // 学号
    private String name; // 姓名
    private boolean sex; // 性别
    private int age;  // 年龄
    // 非必须项
    private String addr; // 家庭地址
    private String phone; // 电话
    private String email; // 邮件
}
```

我们想要构造这样一个类的实例，首先想到的是这样去做。
```
public Student(int id, String name, boolean sex, int age,
                   String addr, String phone, String email) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.addr = addr;
        this.phone = phone;
        this.email = email;
    }
```
假设有个学生小王，上面7项信息都有，可以这样使用。
接下来，学生小李，只有4项基本信息，其他项都不知道，那我们怎么去做？
很简单...
```
Student student = new Student(1, "Li", true, 18,"", "", "");
```

这样让我们很难区分出后面三项参数的具体含义。当然，我们有更高级的方法去做，例如重载构造函数：
```
public Student(int id, String name, boolean sex, int age) {
    this(id, name, sex, age, "");
}
public Student(int id, String name, boolean sex, int age, String addr) {
    this(id, name, sex, age, addr, "", "");
}
```
当然我们可以重载多个构造函数，第一个构造4个参数，第二个构造5个，以此类推，最终包含所有的参数。
这未免过于繁琐，而且不利于阅读。

#### 2、使用JavaBeans settter进行优化
使用setter优化话，大概是下面这个样子，这也是我们经常看到和使用的。
```
Student student = new Student();
student.setId(1);
student.setName("Li");
student.setSex(true);
student.setAge(18);
student.setAddr("BeiJing");
student.setPhone("13888888888");
```
同样地，使用setter方法会产生大量重复的代码，而且代码外观看起来不是很优雅。
遗憾的是，JavaBeans模式有着严重的缺点，构造过程分散到多个setter方法中，
构造过程中由于多线程操作，JavaBean可能处于不一致状态。

#### 3、使用优雅地Builder模式
先看下结果：
```
Student student = new Student.Builder()
    .setId(1)
    .isFemale(false)
    .setName("Li")
    .setAge(18)
    .build();
```
这种形式的代码是不是非常熟悉呢？
比如这样：
```
String str = new StringBuilder()
    .append("ab")
    .reverse()
    .toString();
```
再比如这样：
```
URI uri = new URIBuilder("http://www.baidu.com")
        .setPath("/hello")
        .setParameter("user", "Li")
        .setParameter("pwd", "123456")
        .build();

// custom() 实际上返回一个 HttpClientBuilder
HttpClient client = HttpClients.custom().build();
System.out.println(client.execute(new HttpGet(uri)));
```
是不是非常熟悉呢？尤其是上面发送Http请求这个例子。

```
public static class Builder {
    private int id;
    private String name;
    private boolean sex;
    private int age;
    private String addr;
    private String phone;
    private String email;

    public Builder setId(int id) {
        this.id = id;
        return this;
    }
    public Builder setName(String name) {
        this.name = name;
        return this;
    }
    public Builder isFemale(boolean sex) {
        this.sex = sex;
        return this;
    }
    public Builder setAge(int age) {
        this.age = age;
        return this;
    }
    // ...
    public Student build() {
       return new Student(this.id, this.name, this.sex, this.age, 
                this.addr, this.phone, this.email);
    }
}
```

```
Student student = new Student.Builder()
        .setId(1)
        .isFemale(false)
        .setName("Li")
        .setAge(18)
        .build();
```
### 三、优点以及常见使用场景
package builder;

public class Student {
    private int id; // 学号
    private String name; // 姓名
    private boolean sex; // 性别
    private int age;  // 年龄

    private String addr; // 家庭地址
    private String phone; // 电话
    private String email; // 邮件

    public static Builder custom() {
        return new Builder();
    }

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


    public Student(int id, String name, boolean sex, int age) {
        this(id, name, sex, age, "");
    }
    public Student(int id, String name, boolean sex, int age, String addr) {
        this(id, name, sex, age, addr, "", "");
    }

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

    public Student() {}

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", addr='" + addr + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

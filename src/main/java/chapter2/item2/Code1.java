package chapter2.item2;

// 생성자에 매개변수가 많다면 빌더를 고려하라
// 1. 빌더 패턴
public class Code1 {
    private String name;
    private int age;
    private String company;

    public static class Builder {
        private final String name;
        private final int age;

        private String company = "";

        public Builder(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Builder company(String value) {
            company = value;

            return this;
        }

        public Code1 build() {
            return new Code1(this);
        }
    }

    private Code1(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.company = builder.company;
    }

    @Override
    public String toString() {
        return "Code1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", company='" + company + '\'' +
                '}';
    }
}

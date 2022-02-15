# 익명 클래스보다는 람다를 사용하라

### 람다
* 함수형 인터페이스
* 코드가 훨씬 간단하다.
* 타입을 명시해야 코드가 더 명확할 때만 제외하고는, 람다의 모든 매개변수 타입은 생략하자.
* 컴파일러가 타입을 알 수 없다는 오류를 낼 때만 해당 타입을 명시한다.
####
    // 익명 클래스의 인스턴스를 함수 객체로 사용
    Collection.sort(words, new Comparator<String>() {
        public int compare(String s1, String s2) {
            return Integer.compare(s1.length(), s2.length());
        }
    }

    // 람다식을 함수 객체로 사용
    words.sort(comparingInt(String::length));
####

    // 상수별 클래스 몸체와 데이터를 사용한 열거 타입
    public enum Operation {
        PLUS("+") { public double apply(double, double y) { return x+y; } }
        MINUS("-") { public double apply(double x, double y) { return x-y; } }
        TIMES("*") { public double apply(double x, double y) { return x*y; } }
        DIVIDE("/") { public double apply(double x, double y) { return x/y; } }

        private final String symbol;
        
        Operation(String symbol) { this.symbol = symbol; }
        
        @Override public String toString() { return symbol; }
        public abstract double apply(double x, double y);
    }

    // 람다를 인스턴스 필드에 저장해 상수별 동작을 구현한 열거 타입
    public enum Operation {
        PLUS ("+", (x,y) -> x+y),
        MINUS ("-", (x,y) -> x-y),
        TIMES ("*", (x,y) -> x*y),
        DIVIDE ("/", (x,y) -> x/y);

        private final String symbol;
        private final DoubleBinaryOperator op;
        
        Operation(String symbol, DoubleBinaryOperator op) {
            this.symbol = symbol;
            this.op = op;
        }
        
        @Override public String toString() { return symbol; }
        
        public double apply(double x, double y) {
            return op.applyAsDobule(x,y);
        }
    }
* 람다를 이용하면 쉽게 구현할 수 있다.
1. 각 열거 타입 상수의 동작을 람다로 구현해 생성자에 넘긴다.
2. 생성자는 이 람다를 인스턴스 필드로 저장해둔다.
3. apply 메서드에서 필드에 저장된 람다를 호출한다.

### 기타
* 제네릭의 로 타입을 쓰지말라, 제네릭을 써라, 제네릭 메서드를 써라는 람다와 함께 쓸 때 더 중요해진다.
  * 컴파일러가 타입을 추론하는 데 필요한 타입 정보 대부분을 제네릭에서 얻기 때문.

### 람다 사용 시 주의점
* 람다는 이름이 없고 문서화를 할 수 없다.
  * 코드 자체로 동작이 명확히 설명되지 않거나 코드 줄 수가 많아지면 람다를 쓰지 말아야 한다.
* 람다는 한 줄 ~ 세 줄이내가 좋다.
  * 세 줄을 넘어가면 가독성이 나빠진다.
* 열거 타입 생성자 안의 람다는 열거 타입의 인스턴스 멤버에 접근할 수 없다.
  * 상수별 동작을 간단하게 구현하기 어렵거나, 인스턴스 필드나 메서드를 사용해야만 하는 상황이라면 
    상수별 클래스 몸체를 사용해야 한다.
* 추상 클래스의 인스턴스를 만들 때 람다를 쓸 수 없으니, 익명 클래스를 써야 한다.
* 추상 메서드가 여러 개인 인터페이스의 인스턴스를 만들 때 익명 클래스를 쓸 수 있다.
* 람다는 자신을 참조할 수 없다.
  * 람다에서의 this 는 바깥 인스턴스를 가리킨다.
  * 함수 객체가 자신을 참조해야 한다면 반드시 익명 클래스를 써야 한다.
* 람다를 직렬화하는 일은 극히 삼가야 한다.
  * 직렬화해야만 하는 함수 객체가 있다면 private 정적 중첩 클래스의 인스턴스를 사용하자.

### 정리
* 일단 람다 사용 방식에 대해 학습하자.
* 무작정 람다를 쓰는 것이 좋은 건 아니기 때문에 주의점도 학습하자. 
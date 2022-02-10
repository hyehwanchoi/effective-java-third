# 확장할 수 있는 열거 타입이 필요하면 인터페이스를 사용하라

### 확장하는 열거 타입의 단점
* 타입 안전 열거 패턴은 확장할 수 있으나 열거 타입은 그럴 수 없다.
* 타입 안전 열거 패턴은 열거한 값들을 그대로 가져온 다음 값을 더 추가하여 다른 목적으로 쓸 수 있지만,
  열거 타입은 그럴 수 없다.
* 확장성을 높이려면 고려할 요소가 늘어나 설계와 구현이 더 복잡해진다.

### 확장할 수 있는 열거 타입이 쓰이는 곳
* 열거 타입은 대부분 확장하는 것이 좋지 않지만 확장할 수 있는 열거 타입이 어울리는 쓰임은 연산 코드이다.
* 사용자 확장 연산을 추가할 수 있도록 열어줘야할 때가 있다.

### 방법
* 연산 코드용 인터페이스를 정의하고 열거 타입이 이 인터페이스를 구현하게 하면 된다.
* 열거 타입이 그 인터페이스의 표준 구현체 역할을 한다.
####

    // test 메서드에 ExtendedOperation 의 class 리터럴을 넘겨 확장된 연산들이 무엇인지 알려준다.
    // class 리터럴은 한정적 타입 토큰 역할을 한다.
    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        test(ExtendedOperation.class, x, y);
    }

    private static <T extends Enum<T> & Operation> void test(
        Class<T> opEnumType, double x, double y) {
        for(Operation op : opEnumType.getEnumConstants()) {
            System.out.printf("%f %s %f = %f%n", x, op, op.applay(x,y));
        }
    }
####

    // Class 객체 대신 한정적 와일드카드 타입인 Collection<? extends Operation> 을 넘기는 방법이다.
    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        test(Arrays.asList(ExtendedOperation.values()), x, y);
    }

    private static void test(Collection<? extends Operation> opSet, double x, double y) {
        for (Operation op : opSet) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.applay(x,y));
        }
    }
* 여러 구현 타입의 연산을 조합해 호출할 수 있다.
* 특정 연산에서는 `EnumSet`, `EnumMap` 을 사용하지 못한다.

### 인터페이스를 이용한 확장 가능한 열거 타입 방식의 단점
* 열거 타입끼리 구현을 상속할 수 없다.
  * 아무 상태에도 의존하지 않는 경우에는 디폴트 구현을 이용해 인터페이스에 추가하는 방법이 있다.

### 확인해볼 것
* `java.nio.file.LinkOption` 열거 타입은 `CopyOption` 과 `OpenOption` 인터페이스 ??

### 정리 
* 인터페이스와 그 인터페이스를 구현하는 기본 열거 타입을 함께 사용해 확장성을 높일 수 있다.
* API 가 인터페이스 기반으로 작성되었다면 기본 열거 타입의 인스턴스가 쓰이는 모든 곳을 새로 확장한 열거 타입의 인스턴스로 대체해 사용할 수 있다.
package Chapter2.item1;

// 생성자 대신 정적 팩터리 메서드를 고려하라
// 1. 이름을 가질 수 있다.
public abstract class Calculator {

    public abstract boolean calc(int number);

    public static Calculator newOddInstance() {
        return new Odd();
    }

    public static Calculator newEvenInstance() {
        return new Even();
    }
}

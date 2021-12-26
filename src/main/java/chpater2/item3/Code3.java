package chpater2.item3;

// private 생성자나 열거 타입으로 싱글턴임을 보증하라
// 3. 열거 타입 방식의 싱글턴
public enum Code3 {
    INSTANCE;

    public static Code3 getInstance() {
        return INSTANCE;
    }
}

package item3;

import java.io.Serializable;

// private 생성자나 열거 타입으로 싱글턴임을 보증하라
// 4. 싱글턴 클래스를 직렬화 하려면 readResolve 메서드를 제공해야 한다.
public class Code4 implements Serializable {

    private static final Code4 INSTANCE = new Code4();
    public static Code4 getInstance() {
        return INSTANCE;
    }

    private Object readResolve() {
        return INSTANCE;
    }
}

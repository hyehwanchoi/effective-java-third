package item6;

// 불필요한 객체 생성을 피하라
// 1. 정적 팩터리 메서드를 사용해 불필요한 객체 생성을 피한다.
public class Code1 {

    public boolean valueOf(String str) {
        return Boolean.valueOf(str);
    }

    // java 9 deprecated method
    public boolean valueOf2(String str) {
        return new Boolean(str);
    }
}

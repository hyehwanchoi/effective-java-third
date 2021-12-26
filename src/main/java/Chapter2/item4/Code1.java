package Chapter2.item4;

// 인스턴스화를 막으려거든 private 생성자를 사용하라
// 1. private 생성자를 만들어 인스턴스화를 막는다.
public class Code1 {

    private Code1() {
        throw new AssertionError();
    }
}

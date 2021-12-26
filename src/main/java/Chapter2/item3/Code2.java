package Chapter2.item3;

// private 생성자나 열거 타입으로 싱글턴임을 보증하라
// 2. 정적 팩터리 방식의 싱글턴
public class Code2 {
  private static final Code2 INSTANCE = new Code2();

  public static Code2 getInstance() {
    return INSTANCE;
  }
}

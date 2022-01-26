package chapter2.item3;

// private 생성자나 열거 타입으로 싱글턴임을 보증하라
// 1. public static final 필드 방식의 싱글턴
public class Code1 {
  public static final Code1 INSTANCE = new Code1();

  public static boolean status = false;

  private Code1() {
    if(status) {
      throw new IllegalStateException("두 번 객체를 생성할 수 없습니다.");
    }
  }
}

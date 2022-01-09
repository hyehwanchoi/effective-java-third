# public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라

### 필드들을 모두 private 으로 바꾸고 public 접근자(getter)를 추가한다.
* public 클래스는 절대 가변 필드를 직접 노출해서는 안 된다.
* package-private 클래스나 private 중첩 클래스에서는 종종 필드를 노출하는 편이 나을 때도 있다.
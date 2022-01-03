# Comparable 을 구현할지 고민하라

### 알파벳, 숫자, 연대 같이 순서가 명확한 값 클래스를 작성한다면 반드시 Comparable 인터페이스를 구현하자.

### equals 와의 공통점
반사성, 대칭성, 추이성을 충족해야 함

### equals 와의 차이점
CompareTo 는 단순 동치성 비교에 더해 순서까지 비교할 수 있으며, 제네릭하다.

### compareTo 의 규약
* 이 객체와 주어진 객체의 순서를 비교한다.
  * 객체 < 주어진 객체 => 음의 정수
  * 객체 == 주어진 객체 => 0
  * 객체 > 주어진 객체 => 양의 정수
  * 객체와 비교할 수 없는 타입의 객체면 ClassCastException

* sng(x.compareTo(y)) == -sgn(y.compareTo(x))
* x.compareTo(x) > 0 && y.compareTo(z) > 0 이면 x.compareTo(z) > 0
* x.compareTo(y) == 0 sgn(x.compareTo(z)) == sgn(y.compareTo(z))
* x.compareTo(y) == 0 == x.equals(y)

### 작성 요령
* 객체 참조 필드를 비교하려면 compareTo 메서드를 재귀 호출한다.
* Comparable 을 구현하지 않은 필드나 표준이 아닌 순서로 비교해야 한다면 비교자를 대신 사용한다.
* 클래스에 핵심 필드가 여러 개 라면 어느 것을 먼저 비교하느냐가 중요해진다.
  * 자바의 정적 임포트 기능 이용
* long, double 은 comparingInt 와 thenComparingInt
# 매개변수가 유효한지 검사하라

* 메서드와 생성자 대부분은 입력 매개변수의 값이 특정 조건을 만족하길 바란다.
    * 인덱스 값은 음수여서는 안됨, 객체 참조는 null 이 아니어야 함 등...
* 이러한 제약은 반드시 문서화해야하며 메서드 몸체가 시작되기 전에 검사해야 즉각적으로 깔끔하게 예외를 던질 수 있다.

### public, protected method
* public, protected method 는 매개변수 값이 잘못 됐을 때 던지는 예외를 문서화해야 한다.
    * @throws 자바독 태그 사용
* API 사용자가 제약을 지킬 가능성을 크게 높을 수 있다.
* 자바7의 `java.util.Objects.requireNonNull` 메서드를 사용하면 쉽게 null 검사 수행이 가능하다.  
  `this.strategy = Objects.requireNonNull(strategy, "전략");`
* 혹은 자바9의 `checkFromIndexSize`, `checkFromToIndex`, `checkIndex` 등도 null 검사에 쓸 수 있다.

### private method
* 공개되지 않은 메서드면 개발자가 메서드 호출 상황 통제가 가능하다.
* assert 를 사용해 매개변수 유효성을 검사할 수 있다.
* assert 문은 자신이 단언한 조건을 무조건 참이라고 선언한다.
* 실패하면 AssertionError 를 던진다.
* 런타임에 아무런 효과, 성능 저하도 없다.
####
    private static void sort(long a[], int offset, int length) {
      assert a != null;
      assert offset >= 0 && offset <= a.length;
      assert length >= 0 && length <= a.length - offset;
    }

### 유효성 검사는 언제하지 말아야 할까?
* 유효성 검사 비용이 지나치게 높거나 실용적이지 않을 때
* 계산 과정에서 암묵적으로 검사가 수행될 때
    * 메서드 몸체 실행 전에 꼭 유효성을 검새햐야 하는 것은 아니다.

### 메서드 실행 전 매개변수 검사 예외 케이스
* 메서드 몸체가 실행되기 전에 매개변수를 확인해야 한다는 규칙에도 예외는 있다.
    * 유효성 검사의 비용이 지나치게 높거나 실용적이지 않을 때, 혹은 계산 과정에서 암묵적으로 검사가 수행되는 경우이다.
    * 예시) Collections.sort(List)는 두 원소를 비교할 수 있는 타입인지 정렬 과정에서 비교하고 비교할 수 없는 타입이라면 ClassCastException 이 발생한다.

### 정리
* 메서드나 생성자를 작성할 때 그 매개변수들에 어떤 제약이 있을지 생각하자.
* 제약들을 문서화하고 메서드 시작 부분에서 명시적으로 검사하자.
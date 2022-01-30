# 이왕이면 제네릭 메서드로 만들라

* 타입 매개 변수의 명명 규칙은 제네릭 메서드나 제네릭 타입이나 똑같다.


    // 제네릭 메서드
    public static <E> Set<E> union(Set<E> s1, Set<E> s2) {
        Set<E> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    } => 한정적 와일드카드 타입을 사용하여 개선할 수 있다.

    // 제네릭 메서드를 사용한 프로그램
    public static void main(String[] args) {
        Set<String> guys = Set.of("톰", "딕", "해리");
        Set<String> stooges = Set.of("래리", "모에", "컬리");
        Set<String> aflCio = union(guys, stooges);
        System.out.println(aflCio);
    }
    
### 제네릭 싱글턴 패턴
* 요청한 타입 매개 변수에 맞게 매번 그 객체의 타입을 바꿔주는 형식  


    private static UnaryOperator<Object> IDENTITY_FN = (t) -> t;

    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> identityFunction() {
        return (UnaryOperator<T>) IDENTITY_FN;
    }

* `IDENTITY_FN` 을 `UnaryOperator<T>` 로 형변환하면 비검사 형변환 경고가 발생한다.
  * T 가 어떤 타입이든 `UnaryOperator<Object>` `UnaryOperator<T>` 가 아니기 때문
* T 가 어떤 타입이든 `UnaryOperator<T>` 를 사용해도 타입 안전하다.
  * 입력 값을 수정 없이 그대로 반환하는 특별한 함수인 항등함수의 특징 때문

### 재귀적 타입 한정
* 자기 자신이 들어간 표현식을 사용하여 타입 매개변수의 허용 범위를 한정할 수 있다.
* 주로 타입의 자연적 순서를 정하는 Comparable 인터페이스와 함께 쓰인다.  
* <E Extends Comparable<E> 는 모든 타입 E 는 자신과 비교할 수 있다.


    public interface Comparable<T> {
      int compareTo(T o);
    }
* 타입 매개변수 T 는 `Comparable<T>` 를 구현한 타입이 비교할 수 있는 원소의 타입을 정의한다.
  * String 은 Comparable<String> 을 구현하고 Integer 는 Comparable<Integer> 를 구현.

### 정리
* 형변환을 해줘야 하는 메서드는 제네릭하게 만들자.
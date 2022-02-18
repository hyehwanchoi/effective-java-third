# 표준 함수형 인터페이스를 사용하라

### 필요한 용도에 맞는 게 있다면, 직접 구현하지 말고 표준 함수형 인터페이스를 활용하라.
* API 가 다루는 개념의 수가 줄어들어 익히기 쉬워진다.
* 표준 함수형 인터페이스들은 유용한 디폴트 메서드를 많이 제공하므로 다른 코드와의 상호운용성도 크게 좋아질 것이다.
####

### java.util.function 기본 인터페이스 6개를 이해하자.
* `Operator`
  * 인수가 1개인 `UnaryOperator`와 2개인 `BinaryOperator` 로 나뉘며, 
    반환값과 인수의 타입이 같은 함수를 뜻한다.
* `Predicate`
  * 인수 하나를 받아 boolean 을 반환하는 함수를 뜻한다.
* `Function`
  * 인수와 반환 타입이 다른 함수를 뜻한다.
* `Supplier`
  * 인수를 받지 않고 값을 반환하는 함수를 뜻한다.
* `Consumer`
  * 인수를 하나 받고 반환값은 없는 함수를 뜻한다.

  |인터페이스|함수시그니처|예|
  |--------|--------|-----|
  |`UnaryOperator<T>`|T apply(T t)|String::toLowerCase|
  |`BinaryOperator<T>`|T apply(T t1, T t2)|BigInteger::add|
  |`Predicate<T>`|boolean test(T t)|Collection::isEmpty|
  |`Function<T,R>`|R apply(T t)|Arrays::asList|
  |`Supplier<T>`|T get()|Instant::now|
  |`Consumer<T>`|void accept(T t)|System.out::println|

### 인터페이스 변형들
* 기본 인터페이스의 기본 타입의 변형
  * `IntPredicate`
    * `int`를 받는 `Predicate`
  * `LongBinaryOperator`
    * `long`을 받아 `long`을 반환
  * `LongFunction<int[]>`
    * `long` 인수를 받아 `int[]` 을 반환


* Function 인터페이스의 기본 타입을 반환하는 변형
  * `UnaryOperator`
    * 인수와 같은 타입을 반환
  * 입력과 결과 타입이 모두 기본 타입이면 `SrcToResult`를 접두어로 사용한다.
    * 예시) `long`을 받아 `int`를 반환하면 `LongToIntFunction`
  * 입력을 매개변수화하면 접두어로 `ToResult`를 사용
    * 예시) `int[]` 인수를 받아 `Long` 을 반환하면 `ToLongFunction<int[]>`


* 기본 함수형 인터페이스 중 인수를 2개씩 받는 변형
  * `BiFunction<T,U,R>`
    * 기본 타입을 반환
      * `ToIntBiFunction<T,U>`
      * `ToLongBiFunction<T,U>`
      * `ToDoubleBiFunction<T,U>`


* `Consumer` 객체에서 인수를 2개 받는 변형
  * `ObjDobuleConsumer<T>`
  * `ObjIntConsumer<T>`
  * `ObjLongConsumer<T>`


* `boolean` 을 반환
  * BooleanSupplier

### 기본 함수형 인터페이스에 박싱된 기본 타입을 넣어 사용하지는 말자.
* 계산량이 많을 때 성능이 느려질 수 있다.

### 언제 코드를 직접 작성해야 하는가 ?
* 필요한 용도에 맞는게 없을 때
  * 가령, 매개변수 3개를 Predicate, 검사 예외를 던지는 경우
* 똑같은 표준 함수형 인터페이스가 있더라도 직접 작성해야만 할 때가 있다.
  * `Comparator<T>` 를 사용하며 아래의 조건을 하나 이상 만족한다면 작성한다.
    1) 자주쓰이며, 이름 자체가 용도를 명확히 설명해준다.
    2) 반드시 따라야 하는 규약이 있다.
    3) 유용한 디폴트 메서드를 제공할 수 있다.

### Comparator 특성
* 지금의 이름이 그 용도를 잘 설명해주어 API 에서 자주 사용된다.
* 구현하는 쪽에서 반드시 지켜야 할 규약을 담고 있다.
* 비교자들을 반환하고 유용한 디폴트 메서드들이 많다.

### @FunctionalInterface 사용 이유
* 해당 클래스의 코드나 설명 문서를 읽을 이에게 인터페이스가 람다용으로 설계된 것임을 알려준다.
* 해당 인터페이스가 추상 메서드를 오직 하나만 가지고 있어야 컴파일되게 해준다.
* 누군가 실수로 메서드를 추가하지 못하게 막아준다.
* `직접 만든 함수형 인터페이스에는 항상 @FunctionalInterface 애너테이션을 사용하라.`

### 함수형 인터페이스를 API 에서 사용할 때의 주의점
* 서로 다른 함수형 인터페이스를 같은 위치의 인수로 받는 메서드들을 다중 정의해서는 안된다.
  * 다중정의를 피하자.
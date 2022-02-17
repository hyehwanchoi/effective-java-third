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
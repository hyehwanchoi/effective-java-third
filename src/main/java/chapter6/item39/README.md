# 명명 패턴보다 애너테이션을 사용하라

### 명명 패턴의 단점
* 오타가 나면 안된다.
* 안전한 지 알 수 없다.
* 프로그램 요소를 매개변수로 전달할 마땅한 방법이 없다.
  * 테스트를 실행하기 전에는 그런 이름의 클래스가 존재하는지 혹은 예외가 맞는지 알 수 없다.

### 이를 위해 애너테이션을 사용한다.
* 애너테이션으로 할 수 있는 일을 명명 패턴으로 처리할 이유는 없다.
* 자바 프로그래머라면 예외 없이 자바가 제공하는 애너테이션 타입들은 사용해야 한다.

### 정리
* 애너테이션을 잘쓰면 확실히 편하다.
* 하지만 때에 맞게 적절히 사용하는 것이 중요한 것 같다.
* 이를 위해 나는 공부를 위해서 애너테이션을 잘쓰지 않으려고 한다.
* 애너테이션을 왜 쓰고 필요한지 몸소 익히기 위해 그렇다.
* 더 다양한 애너테이션들을 익히고 때에 맞게 적절히 사용할 수 있도록 학습하자.

### @Test 애너테이션
* `@Retention` 과 `@Target` 이 달려있다.
* `@Retention` 은 `@Test` 가 런타임에도 유지되어야 한다.
* `@Target` 은 `@Test` 가 반드시 메서드 선언에서만 사용돼야 한다.

### 마커 애너테이션
* 직접적인 영향을 주지 않고 추가 정보를 제공할 뿐이다.
* 대상 코드의 의미는 그대로 둔 채 그 애너테이션에 관심 있는 도구에서 특별한 처리를 할 기회를 준다.

### 배열 매개변수를 받는 애너테이션
* 원소가 여럿인 배열을 지정할 때는 원소들을 중괄호로 감싸고 쉼표로 구분한다.

### 반복 가능한 애너테이션 (@Repeatable)
* 자바 8에서는 여러 개의 값을 받는 애너테이션을 다른 방식으로도 만들 수 있다.
* `@Repeatable` 을 단 애너테이션은 하나의 프로그램 요소에 여러 번 달 수 있다.

### 주의 사항
* `@Repeatable` 을 단 애너테이션을 반환하는 컨테이너 애너테이션을 하나 더 정의하고, 
  `@Repeatable` 에 이 컨테이너 애너테이션의 class 객체를 매개변수로 전달해야 한다.
* 컨테이너 애너테이션은 내부 애너테이션 타입의 배열을 반환하는 value 메서드를 정의해야 한다.
* 컨테이너 애너테이션 타입에는 적절한 보존 정책(@Retention)과 적용 대상(@Target)을 명시해야 한다. 
  그렇지 않으면 컴파일되지 않을 것이다.
####
    // 반복 가능한 애너테이션
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @Repeatable(ExceptionTestContainer.class)
    public @interface ExceptionTest {
      Class<? extends Throwable> value();
    }
    
    // 컨테이너 애너테이션
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface ExceptionTestContainer {
      ExceptionTest[] value();
    }
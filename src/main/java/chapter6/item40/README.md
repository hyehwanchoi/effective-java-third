# @Override 애너테이션을 일관되게 사용하라

* 이 애너테이션을 사용할 경우 여러 가지 버그들을 예방해준다.
* 그러니, 상위 클래스의 메서드를 재정의하려는 모든 메서드에 `@Override` 애너테이션을 달자
  * 구체 클래스에서 상위 클래스의 추상 메서드를 재정의할 때는 굳이 달지 않아도 된다.
* 추상 클래스나 인터페이스에서는 상위 클래스나 상위 인터페이스의 메서드를 재정의하는 모든 메서드에 `@Override` 를 다는 것이 좋다.
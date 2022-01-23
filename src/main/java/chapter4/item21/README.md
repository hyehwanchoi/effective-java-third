# 인터페이스는 구현하는 쪽을 생각해 설계하라.

### 디폴트 메서드의 문제점
* 생각할 수 있는 모든 상황에서 불변식을 해치지 않는 디폴트 메서드를 작성하기란 어려운 법이다.
* 디폴트 메서드는 기존 구현체에 런타임 오류를 일으킬 수 있다.
  * 기존 구현체들과 충돌할 수 있다.

### 결론
* 새로운 인터페이스라면 서로 다른 방식으로 최소한 세 가지를 구현해 테스트 해본다.
* 각 인터페이스의 인스턴스를 다양한 작업에 활용하는 클라이언트도 여러 개 만들어봐야 한다.
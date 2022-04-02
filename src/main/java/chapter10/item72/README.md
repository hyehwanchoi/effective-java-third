# 표준 예외를 사용하라

* 예외도 마찬가지로 재사용하는 것이 좋다.
  * 예외 클래스 수가 적을수록 메모리 사용량도 줄고 클래스를 적재하는 시간도 적게 걸린다.
####
    IllegalArgumentException : 허용하지 않는 값이 인수로 건네졌을 때
    IllegalStateException : 객체가 메서드를 수행하기에 적절하지 않은 상태일 때
    NullPointerException : null 을 허용하지 않는 메서드에 null 을 건냈을 때
    IndexOutOfBoundsException : 인덱스가 범위를 넘어섰을 때
    ConcurrentModificationException : 허용하지 않는 동시 수정이 발견됐을 때
    UnsupportedOperationException : 호출한 메서드를 지원하지 않을 때
    ArithmeticException, NumberFormatException : 복소수나 유리수를 다루는 객체를 작성할 때
    
### 정리
* 사용자 정의 예외보다는 표준 예외를 사용하자.
# equals 는 일반 규약을 지켜 재정의하라

### 언제 equals 를 재정의 해야 하는가?
* 논리적 동치성을 확인해야 하는데, 상위 클래스의 equals 가 논리적 동치성을 비교하도록 재정의되지 않았을 때
* 주로 값 클래스들 ex) Integer, String 
* 값 클래스라 해도 인스턴스 통제 클래스라면 재정의 필요 없음.

### equals 메서드 재정의 시 따라야 할 규약
* 반사성(reflexivity) : null 이 아닌 모든 참조 값 x에 대해 x.equals(x) 는 true 다.
* 대칭성(symmetry) : null 이 아닌 모든 참조 값 x, y에 대해 x.equals(y) 가 true 이면 y.equals(x) 도 true 이다.
* 추이성(transitivity) : null 이 아닌 모든 참조 값 x, y, z에 대해 x.equals(y) 가 true 이고 y.equals(z) 도 true 면 x.equals(z) 도 true 이다.
* 일관성(consistency) : null 이 아닌 모든 참조 값 x, y에 대해 x.equals(y) 를 반복해서 호출하면 항상 true 를 반환하거나 항상 false 를 반환한다.
* not null : not null 인 모든 참조 값 x에 대해 x.equals(null) 은 false 다.
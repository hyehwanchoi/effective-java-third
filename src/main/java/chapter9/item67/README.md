# 최적화는 신중히 하라

* 빠른 프로그램보다는 좋은 프로그램을 작성하라.
  * 좋은 프로그램은 정보 은닉 원칙을 따르므로 개별 구성요소의 내부를 독립적으로 설계할 수 있다.
  * 시스템의 나머지에 영향을 주지 않고도 각 요소를 다시 설계할 수 있다.
  * 아키텍쳐의 결함이 성능을 제한하는 상황이라면 시스템 전체를 다시 작성하지 않고는 해결하기 불가능할 수 있다.
* 성능을 제한하는 설계를 피하라.
* API 를 설계할 때 성능에 주는 영향을 고려하라.
* 성능을 위해 API 를 왜곡하는 건 매우 안 좋은 생각이다.
  * 왜곡된 API 와 이를 지원하는 데 따르는 고통은 영원히 계속될 것이다.
* 각각의 최적화 시도 전후로 성능을 측정하라.

### 정리
* 좋은 프로그램을 작성하다 보면 성능은 따라오게 마련이다.
* 프로토콜, 영구 저장용 데이터 포맷을 설계할 때는 성능을 염두에 두어야 한다.
* 다른 저수준 최적화는 아무리 해봐야 소용이 없다.
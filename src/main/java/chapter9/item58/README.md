# 전통적인 for 문보다는 for-each 문을 사용하라

* for-each 문을 사용하면 반복자와 인덱스 변수를 사용하지 않으니 코드가 깔끔해지고 오류가 날 일이 없다.
* 원소들의 묶음을 표현하는 타입을 작성해야 한다면 Iterable 을 고민하라.

### for-each 를 사용할 수 없는 상황
1. 파괴적인 필터링 : 컬렉션을 순회하면서 선택된 원소를 제거해야 한다면 반복자의 remove 메서드를 호출해야 한다. 
   자바 8부터는 Collection 의 removeIf 메서드를 사용해 컬렉션을 명시적으로 순회하는 일을 피할 수 있다.
2. 변형 : 리스트나 배열을 순회하면서 그 원소의 값 일부 혹은 전체를 교체해야 한다면 리스트의 반복자나 배열의 인덱스를 사용해야 한다.
3. 병렬 반복 : 여러 컬렉션을 병렬로 순회해야 한다면 각각의 반복자와 인덱스 변수를 사용해 엄격하고 명시적으로 제어해야 한다.
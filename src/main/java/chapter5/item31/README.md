# 한정적 와일드카드를 사용해 API 유연성을 높여라

    // Integer 는 Number 의 하위 타입이 아니다. 오류 메시지 발생
    public void pushAll(Iterable<E> src) {
        for (E e : src) {
            push(e);
        }
    }

    => 변경
    // 생산자 매개변수에 한정적 와일드 카드 타입 적용
    public void pushAll(Iterable<? extends E> src) {
        for (E e : src) {
            push(e);
        }
    }
####

    // Collection<Object> 는 Collection<Number> 의 하위 타입이 아니다.
    public void popAll(Collection<E> dst) {
        while (!isEmpty()) {
            dst.add(pop());
        }
    }

    // 소비자 매개변수에 와일드 카드 타입 적용
    public void popAll(Collection<? super E> dst) {
        while (!isEmpty()) {
            dst.add(pop());
        }
    }

### 기타
* 유연성을 극대화하려면 원소의 생산자나 소비자용 입력 매개변수에 와일드카드 타입을 사용하라.

* `PECS : producer-extends, consumer-super`  
   : 매개변수화 타입 T 가 생산자라면 <? extends T> 를 사용하고 소비자라면 <? super T> 를 사용하라.

* 반환 타입에는 한정적 와일드카드 타입을 사용하면 안 된다.
* 클래스 사용자가 와일드카드 타입을 신경써야 한다면 그 API 에 무슨 문제가 있을 가능성이 크다.
* 자바 7까지는 명시적 타입 인수를 사용해야 한다.
  * Set<Number> numbers = Union.<Number>union(integers, doubles);
* Comparable 과 Comparator 는 모두 소비자다.
* 메서드 선언에 타입 매개변수가 한 번만 나오면 와일드 카드로 대체하라.
  * 비한정적 타입 매개변수라면 비한정적 와일드카드로 바꾸고, 한정적 타입 매개변수라면 한정적 와일드카드로 바꾼다.
* 널리 쓰일 라이브러리를 작성한다면 반드시 와일드카드 타입을 적절히 사용해줘야 한다.
####
    public static void swap(List<?> List, int i, int j) {
        swapHelper(list, i, j);
    }

    // 와일드카드 타입을 실제 타입으로 바꿔주는 private 도우미 메서드
    // 실제 타입을 알아내려면 도우미 메서드는 제네릭 메서드여야 한다.
    private static <E> void swapHelper(List<E> list, int i, intj) {
        list.set(i, list.set(j, list.get(i)));
    }
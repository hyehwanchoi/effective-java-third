# 제네릭과 가변인수를 함께 쓸 때는 신중하라

### 제네릭과 varargs 를 혼용하면 타입 안정성이 깨진다.

* 매개변수화 타입의 변수가 타입이 다른 객체를 참조하면 힙 오염이 발생한다.
####
    static void dangerous(List<String>... stringLists) {
        List<Integer> intList = List.of(42);
        Object[] objects = stringLists;
        Object[0] = intList; // 힙 오염 발생
        String s = stringLists[0].get(0); // ClassCastException
    }

1. List<String> varargs 형태의 파라미터를 받는 메서드이다.
2. List<Integer> 제네릭타입 객체를 생성하여 42라는 값을 추가하였다.
3. varargs 는 내부적으로 배열이고, 배열은 공변이기 때문에 List<String>[] -> Object[]에 참조될 수 있다.
4. Object[0] = intList 초기화
   1. 내부적으로는 List<String> 타입이지만, 런타임에는 제네릭 타입이 소거되므로 같은 List 로만 인식되어 할당이 가능하다. 힙 오염 발생
5. stringList[0]을 하면 List 가 나오고 List 의 0번째 인덱스 위치의 객체를 호출해 눈에 보이지 않는 String 으로 형변환한다. 여기서 ClassCastException 이 발생

### @SafeVarargs
* @SafeVarargs 애노테이션은 메서드 작성자가 그 메서드가 타입 안전함을 보장하는 장치이므로 반드시 메서드가 타입 안전한 경우에만 이 애노테이션을 붙이는 것이 좋다.

#### 어떤게 안전한 것인가?
* 가변인수 메서드를 호출하면 varargs 매개변수를 담는 제네릭 배열이 만들어 진다. 
* 메서드 내에서 이 배열에 아무것도 저장하지 않고, 배열의 참조가 밖으로 노출되지 않는다면 타입 안전하다. 
* 순수하게 메서드의 생산자 역할만 충실히 하면 메서드는 안전하다.

### 자신의 제네릭 매개 변수 배열의 참조를 노출하는 것은 위험하다.
    static <T> T[] toArray(T... args) {
        return args;
    }

    static <T> T[] pickTwo(T a, T b, T c) {
        switch(ThreadLocalRandom.current().nextInt(3)) {
            case 0: return toArray(a, b);
            case 1: return toArray(b, c);
            case 2: return toArray(c, a);
        }
        throw new AssertiionError();
    }

    public static void main(String[] args) {
        String[] attributes = pickTwo("좋은", "빠른", "저렴한");
    }
* 아무 문제가 없는 메서드이니 별다른 문제없이 컴파일 된다. 
* 하지만 실행하면 ClassCastException 을 던진다.
* String[] attributes = pickTwo(); 메서드에서 보이지 않는 형변환 시 발생한다.
  * String[] attributes = (String[]) pickTwo("좋은", "빠른", "저렴한");
* 예외 사
  * @SafeVarargs 로 선언된 타입 안전성이 보장된 또 다른 varargs 메서드에 넘기는 것은 안전하다. 
  * 배열 내용의 일부 함수를 호출만 하는 (varargs 를 받지않는) 일반 메서드에 넘기는 것도 안전하다.

### 제네릭 varargs 매개변수를 List 로 대체하면 안전하다.
    static <T> List<T> flatten(List<List<? extends T>> lists) {
        List<T> result = new ArrayList<>();
            for (List<? extends T> list : lists) {
            result.addAll(list);
        }
        return result;
    }
* 이 메서드의 타입 안전성을 검증할 수 있다는 점이다.
* @SafeVarargs 를 달지 않아도 되며 실수로 안전하다고 판단할 걱정도 없다.
* 단점은 클라이언트 코드가 살짝 지저분해지고, 속도가 약간 느려질 수 있다는 점이다.

### 정리
* varargs 매개변수는 단순히 파라미터를 받아와 메서드의 생산자로만 사용하자
* varargs 배열을 외부에 리턴하거나 노출하지 말자.
* 웬만하면 컬렉션(List)에 담아 리턴하는 안전한 방식을 취하자
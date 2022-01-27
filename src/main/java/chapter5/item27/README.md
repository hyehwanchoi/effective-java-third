# 비검사 경고를 제거하라

### 비검사 경고
* 비검사 경고는 다이아몬드 연산자로 쉽게 해결할 수 있다.
  * `Set<Lark> exaltation = new HashSet();`  
    => `Set<Lark> exaltation = new HashSet<>();`
* 할 수 있는 한 모든 비검사 경고를 제거하라.
  * 타입 안전성이 보장된다.
  * 런타임에 ClassCastException 이 발생할 일이 없다.
* 타입이 안전하다고 확신할 수 있다면 `@SuppressWarnings("unchecked")` 애노테이션을 달아 경고를 숨기자.
  * 안전하다고 검증된 코드의 비검사 경고를 숨기지 않고 그대로 둔다면, 진짜 문제의 경고를 눈치채지 못할 수 있다.

### @SuppressWarnings 애노테이션
* 항상 가능한 한 좁은 범위에 적용하자.
  * 변수선언, 아주 짧은 메서드, 생성자
* @SuppressWarnings("unchecked") 에노테이션을 사용할 때면 그 경고를 무시해도 안전한 이유를 항상 주석으로 남겨야 한다.

    
    // 컴파일 시 경고 발생
    public <T> T[] toArray(T[] a) {
      if(a.length < size) {
        return (T[]) Arrays.copyOf(element, size, a.getClss());
      }

      System.arraycopy(elements, 0, a, 0, size);
      if(a.length > size) {
        a[size] = null;
      }

      return a;
    }
    
    // @SuppressWarnings("unchecked") 애노테이션 추가로 경고 제거
    public <T> T[] toArray(T[] a) {
      if(a.length < size) {
        // 생성한 배열과 매개변수로 받은 배열의 타입이 모두 T[]로 같으므로 올바른 형변환이다.
        @SuppressWarnings("unchecked")
        T[] result = (T[]) Arrays.copyOf(element, size, a.getClss());
        
        return result;
      }

      System.arraycopy(elements, 0, a, 0, size);
      if(a.length > size) {
        a[size] = null;
      }

      return a;
    }

## 의견 
평소 컴파일러에서 발생하는 경고 메시지는 중요하게 생각하지 않아 쉽게 넘겼던 것 같다.   
하지만, 진짜 문제를 잘 캐치하기 위해 경고 메시지를 제거하는 데도 노력해야겠다.
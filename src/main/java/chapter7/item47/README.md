# 반환 타입으로는 스트림보다 컬렉션이 낫다.

* 일련의 원소를 반환하는 메서드는 많다.
  * `Collection`, `Set`, `List`, `Iterable`, `Array`
  * for-eatch 문에서만 쓰이거나 반환된 원소 시퀀스가 일부 Collection 메서드를 구현할 수 없을 때는 Iterable 인터페이스를 썼다.
  * 반환 원소들이 기본 타입이거나 성능에 민감한 상황이라면 배열을 썼다.
* 스트림은 반복을 지원하지 않는다. 따라서 스트림과 반복을 알맞게 조합해야 좋은 코드가 나온다. 
  * Stream 인터페이스는 Iterable 인터페이스가 정의한 추상 메서드를 전부 포함할 뿐만 아니라, Iterable 인터페이스가 정의한 방식대로 동작한다. 
    그럼에도 for-each 로 스트림을 반복할 수 없는 까닭은 바로 Stream 이 Iterable 을 확장하지 않아서다.
* 객체 시퀀스를 반환하는 메서드를 작성하는데, 이 메서드가 오직 스트림 파이프라인 에서만 쓰일 걸 안다면 마음 놓고 스트림을 반환하게 해주자.
  반대로 반환된 객체들이 반복문에서만 쓰인 걸 안다면 Iterable 을 반환하자.
* Collection 인터페이스는 Iterable 의 하위 타입이고 stream 메서드도 제공하니 반복과 스트림을 동시에 지원한다. 
  * 따라서 원소 시퀀스를 반환하는 공개 API 의 반환 타입에는 Collection 이나 그 하위 타입을 쓰는 게 일반적으로 최선이다. 
  * **하지만 단지 컬렉션을 반환한다는 이유로 덩치 큰 시퀀스를 메모리에 올려서는 안 된다.**
  * 반환할 시퀀스가 크지만 표현을 간결하게 할 수 있다면 전용 컬렉션을 구현하는 방안을 검토해보자.
####
  
    public class PowerSet {
      public static final <E> Collection<Set<E>> of(Set<E> s) {
        List<E> src = new ArrayList<>(s);
        if (src.size() > 30) {
          throw new IllegalArgumentException("집합에 원소가 너무 많습니다.(최대 30개)".: + s);
        }

        return new AbstractList<Set<E>>() {
          @Override 
          public int size() {
            // 멱집합의 크기는 2를 원래 집합의 원소 수만큼 거듭제곱과 같다.
            return 1 << src.size();
          }

          @Override 
          public boolean contains(Object o) {
            return o instanceof Set && src.containsAll((Set)o);
          }

          @Override 
          public Set<E> get(int index) {
            Set<E> result = new HashSet<>();
            for (int i = 0; index != 0; i++, index >>=1) {
              if ((index & 1) == 1) {
                result.add(src.get(i));
              }
            }

            return result;
          }
        };
      }
    }

* AbstractCollection 을 활용해서 Collection 구현체를 작성할 때는 Iterable 용 메서드 외에 2개만 더 구현하면 된다. 
  * 바로 contains 와 size 다. 
* 스트림을 반환하는 두 가지 구현을 알아봤는데 모두 쓸만은 하다. 
  * 하지만 반복을 사용하는 게 더 자연스러운 상황에서도 사용자는 그냥 스트림을 쓰거나 Stream 을 Iterable 로 변환해주는 어댑터를 이용해야 한다. 
  * 하지만 이러한 어댑터는 클라이언트 코드를 어수선하게 만들고 2,3배가 더 느리다.

### 정리
* 원소 시퀀스를 반환하는 메서드를 작성할 때는 양쪽을 다 만족시키려 노력하자.
* 컬렉션을 반환할 수 있다면 그렇게 하라.
* 반환 전부터 이미 원소들을 컬렉션에 담아 관리하고 있거나 컬렉션을 하나 더 만들어도 될 정도로 원소 개수가 적다면 ArrayList 같은 표준 컬렉션에 담아 반환하라.
* 그렇지 않으면 전용 컬렉션을 구현할지 고민하라.
* 컬렉션을 반환하는 게 불가능하면 스트림과 Iterable 중 더 자연스러운 것을 반환하라.
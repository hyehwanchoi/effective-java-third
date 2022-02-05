# 타입 안전 이종 컨테이너를 고려하라

* 제네릭은 `Set<E>, Map<K,V>` 등의 컬렉션과 `ThreadLocal<T>, AtomicReference<T>` 등의 단일원소 컨테이너에도 사용된다.
* Set 에는 원소의 타입을 뜻하는 단 하나의 타입 매개변수만 있으면 되며,
* Map 에는 키와 값의 타입을 뜻하는 2개만 필요한 식이다.

### 타입 안전 이종 컨테이너 패턴을 사용해 더 유연하게 사용할 수 있다.
* 컨테이너 대신 키를 매개변수화한 다음, 컨테이너에 값을 넣거나 뺄 때 매개변수화한 키를 함께 제공하면 된다.
* 한편, 컴파일타임 타입 정보와 런타임 타입 정보를 알아내기 위해 메서드들이 주고 받는 class 리터럴을 타입 토큰이라 한다.
####
    // 타입 안전 이종 컨테이너 패턴 - API
    public class Favorites {
        public <T> void putFavorite(Class<T> type, T instance);
        public <T> T getFavorite(Class<T> type);
    }

    // 타입 안전 이종 컨테이너 패턴 - 클라이언트
    public static void main(String[] args) {
        Favorites f = new Favorites();
        
        f.putFavorite(String.class, "Java");
        f.putFavorite(Integer.class, 0xcafebabe);
        f.putFavorite(Class.class, Favorites.class);
    
        String favoriteString = f.getFavorite(String.class);
        int favoriteInteger = f.getFavorite(Integer.class);
        Class<?> favoriteClass = f.getFavorite(Class.class);
    
        System.out.printf("%s %x %s\\n", favoriteString, favoriteInteger, favoriteClass.getName());
    }

### 와일드카드 중첩
모든 키가 서로 다른 매개변수화 타입일 수 있다.

### favorites 클래스의 제약
* 악의적인 클라이언트가 Class 객체를 로 타입으로 넘기면 favorites 인스턴스의 타입 안정성이 쉽게 깨진다.
  * 하지만 이렇게 짜여진 클라이언트 코드는 컴파일할 때 비검사 경고가 뜰 것이다.
  * Favorites 가 타입 불변식을 어기는 일이 없도록 보장하려면 putFavorite 메서드와 같이 instance 의 타입이 type 으로 명시한 타입과 같은지 확인하면 된다. 
  * java.util.Collections 에는 checkedSet, checkedList, checkedMap 같은 메서드가 있는데 바로 이 방식을 적용한 컬렉션 래퍼들이다.
* 실체화 불가 타입에는 사용할 수 없다는 것이다.
  * String, String[] 은 저장할 수 있어도 List<String> 은 저장할 수 없다.
  * List<String>을 저장하려는 코드는 컴파일되지 않을 것이다. List<String>용 Class 객체를 얻을 수 없기 때문이다. 
  * List<String>.class 라고 쓰면 문법 오류가 난다. List<String>과 List<Integer>는 List.class 라는 객체를 공유하기 때문이다.
  * `한정적 타입 토큰`을 활용 하면 가능하다.

### 한정적 타입 토큰
* 한정적 타입 매개변수나 한정적 와일드카드를 사용하여 표현 가능한 타입을 제한하는 타입 토큰이다.
* 애노테이션 API는 한정적 타입 토큰을 적극적으로 사용한다.  
  * `public <T extends Annotation> T getAnnotation(Class<T> annotationType);`
  * 여기서 `annotationType`인수는 애너테이션 타입을 뜻하는 한정적 타입 토큰이다. 
  * 이 메서드는 토큰으로 명시한 타입의 에너테이션이 대상 요소에 달려 있다면 그 애너테이션을 반환하고 없다면 null 을 반환한다. 즉, 애너테이션된 요소는 그 키가 애너테이션 타입인, `타입 안전 이종 컨테이너`이다.

### Class<?> 타입의 객체가 있고, 이를 한정적 타입 토큰을 받는 메서드에 넘기려면 어떻게 해야 할까 ?
* 형변환을 안전하게 수행해주는 인스턴스 메서드 asSubclass 메서드를 제공한다.
* 호출된 인스턴스 자신의 Class 객체를 인수가 명시한 클래스로 형변환한다.
* 형변환에 성공하면 인수로 받은 클래스 객체를 반환하고 실패하면 ClassCastException 을 던진다.
####
    // asSubclass 를 사용해 한정적 타입 토큰을 안전하게 형변환한다.
    static Annotation getAnnotation(AnnotatedElement element, String annotationTypeName) { 
	    Class<?> annotationType = null; // 비한정적 타입 토큰
	    try {
		    annotationType = Class.forName(annotationTypeName);
	    } catch (Exception ex) {
		    throw new IllegalArgumentException(ex);
	    } 
	    return element.getAnnotation(annotationType.asSubclass(Annotation.class));
    }

### 정리
* 일반적인 제네릭 형태에서는 한 컨테이너가 다룰 수 있는 타입 매개변수의 수가 고정되어 있다.
* 컨테이너 자체가 아닌 키를 타입 매개변수로 바꾸면 이런 `제약이 없는 타입 안전 이종 컨테이너`를 만들 수 있다.
* 타입 안전 이종 컨테이너는 Class 를 키로 쓰며, 이 Class 객체를 `타입 토큰` 이라 한다.
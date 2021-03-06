# clone 재정의는 주의해서 진행하라

### cloneable 은 복제해도 되는 클래스임을 명시하는 용도

### clone 메서드의 규약
* x.clone() != x
* x.clone().getClass() == x.getClass()
    * 필수는 아니다.
* x.clone.equals(x)
    * 필수는 아니다.
* x.clone().getClass() == x.getClass()
    * super.clone 의 반환 객체와 clone 의 반환 객체가 같다면 true

### clone 재정의 시 주의사항 1
* clone 메서드가 super.clone()이 아닌, 생성자를 호출해 얻은 인스턴스를 반환해도 컴파일러는 알지 못한다.
    * 이 클래스의 하위 클래스에서 super.clone()을 호출한다면 잘못된 클래스의 객체가 만들어져, 결국 하위 클래스의 clone() 메서드가 제대로 동작하지 않게 된다.
    * clone() 을 재정의한 클래스가 final 클래스인 경우에는 하위 클래스가 없으니 상관없다.
* 재정의한 메서드의 반환 타입은 상위 클래스의 메서드가 반환하는 타입의 하위 타입일 수 있다.
    * 클라이언트가 형변환 하지 않도록 인터페이스를 제공한다.
* try-catch 블록으로 Object 의 clone 메서드가 검사 예외(checked exception) 로 제공되는 것을 비검사 예외(unchecked exception) 로 처리하도록 한다.

### clone 재정의 시 주의사항 2 - 가변 객체를 참조하는 경우
* clone 메서드가 단순히 super.clone() 의 결과를 그대로 받아오는 경우
    * 반환된 Stack 인스턴스의 size 필드는 올바른 값을 갖겠지만, elements 필드는 원본 Stack 인스턴스와 똑같은 배열을 참조할 것이다.
    * 원본이나 복제본 중 하나를 수정하면 다른 하나도 수정되어 불변식을 해친다.
    * 프로그램이 이상하게 동작하거나 NullPointerException 이 발생하게 된다.
* clone 메서드는 사실상 생성자와 같은 효과를 낸다.
    * 즉, clone 은 원본 객체에 아무런 해를 끼치지 않는 동시에 복제된 객체의 불변식을 보장해야 한다.
* 가변 객체를 갖는 클래스를 복제 하기 위해서는 내부 정보를 복사해야 한다.
    * 가장 쉬운 방법은 elements 배열의 clone 을 재귀적으로 호출해주는 것이다.

### clone 재정의 시 주의사항 3 - 근본적인 문제점
* Cloneable 아키텍처는 '가변 객체를 참조하는 필드는 final 로 선언하라' 는 일반 용법과 충돌한다.
* 복제할 수 있는 클래스를 만들기 위해 일부 필드에서 final 한정자를 제거해야 할 수도 있다는 말이다.
* 배열은 원본과 같은 연결 리스트를 참조하여 원본과 복제본 모두 예기치 않게 동작할 가능성이 생긴다.
* 이를 해결하기 위해서는 각 버킷을 구성하는 연결 리스트를 복사 해야 한다.

#### 연결 리스트를 복제하는 경우 주의사항
* 연결리스트 전체를 복사하려는 경우 위해서는 재귀 호출 대신에 반복문을 사용하여 순회하는 방향으로 수정해야 한다.

### 복잡한 가변 객체를 복제하는 방법
* super.clone() 을 호출하여 얻은 객체의 모든 필드를 초기 상태로 설정
* 원본 객체의 상태를 다시 생성하는 고수준 메서드들을 호출
* 하지만 이 방법은 저수준에서 바로 처리할 때보다는 느리다.
* Cloneable 아키텍처의 기초가 되는 필드 단위 객체 복사를 우회하기 때문에 전체 Cloneable 아키텍처와는 어울리지 않는 방식이다.

### clone() 메서드 내에서 상태 값을 수정하는 코드 작성 주의사항
* 생성자에서는 재정의될 수 있는 메서드를 호출하지 않아야 하는 것처럼 clone() 메서드도 마찬가지이다.

### clone() 재정의 시 주의사항 4 - 예외처리
* clone() 메서드는 CloneNotSupportedException 을 던진다고 선언되어 있지만 재정의한 메서드는 수정해야 한다.
* public clone 메서드에서는 throws 절을 없애야 한다.
* 검사 예외를 비검사예외로 수정해야 그 메서드를 사용하기에 편리하다.

### 복제가 필요한 클래스를 설계하는 방법
* 상속용 클래스는 Cloneable 을 구현해서는 안된다.
1. clone 메서드를 구현해 protected 로 두고 CloneNotSupportedException 을 던지도록 선언한다.
    * 이 방식은 Cloneable 구현 여부를 하위 클래스에서 선택하도록 해준다.
2. clone 을 동작하지 않게 구현해놓고 하위 클래스에서 재정의하지 못하도록 할 수 있다.

### 동기화가 필요한 프로세스에서 Cloneable 구현
* Cloneable 을 구현한 스레드 안전 클래스를 작성할 때는 clone 메서드 역시 적절히 동기화해줘야 한다.
* super.clone() 호출 외에 다른 할 일이 없더라도 clone 을 재정의하고 동기화 해줘야 한다.
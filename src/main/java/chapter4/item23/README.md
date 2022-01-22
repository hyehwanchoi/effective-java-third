# 태그 달린 클래스보다는 클래스 계층구조를 활용하라

### 태그 달린 클래스의 단점
* 열거 타입 선언, 태그 필드, switch 문 등 쓸데없는 코드가 많다.
* 여러 구현이 한 클래스에 혼합돼 있어서 가독성이 나쁘다.
* 다른 의미를 위한 코드도 있어서 메모리도 많이 사용한다.
* 쓰지 않는 필드를 초기화하는 불필요한 코드가 늘어난다.
* 엉뚱한 필드를 초기화해도 런타임 시에 알 수 있다.
* 또 다른 의미를 추가하려면 코드를 수정해야 한다.
* 새로운 의미를 추가할 때마다 코드 수정이 필요하며 빠진다면 런타임 문제가 발생할 수 있다.
* 인스턴스 타입만으로 나타내는 의미를 알기 어렵다.

***결론 : 태그 달린 클래스는 장황하고, 오류를 내기 쉽고, 비효율적이다.***

    /* 태그 달린 클래스 */ 
    class Figure {
        enum Shape { RECTANGLE, CIRCLE };
        final Shape shape;
    
        double length;
        double width;
    
        double radius;
    
        Figure(double radius) {
            shape = Shape.CIRCLE;
            this.radius = radius;
        }
    
        Figure(double length, double widht) {
            shape = Shape.RECTANGLE;
            this.length = length;
            this.width = width;
        }
    
        double area() {
            switch(shape) {
                case RECTANGLE:
                    return length * width;
                case CIRCLE:
                    return Math.PI * (radius * radius);
                default:
                    throw new AssertionError(shape);
            }
        }
    }

### 클래스 계층구조로 바꾸는 방법
1. 계층구조의 루트가 될 추상 클래스 정의
2. 태그 값에 따라 동작이 달라지는 메서드들을 루트 클래스의 추상 메서드로 선언
3. 태그 값에 상관없이 동작이 일정한 메서드들을 루트 클래스에 일반 메서드로 추가한다.
4. 모든 하위 클래스에서 공통으로 사용하는 데이터 필드들도 전부 루트 클래스로 올린다.


    /* 클래스 계층 구조 변환 */
    abstract class Figure {
        abstract double area();
    }

    class Circle extends Figure {
        final double radius;
        
        Circle(double radius) { this.radius = radius; }
        
        @overrdie double area() { return Math.PI * (radius * radius); }
    }

    class Rectangle extends Figure {
        final double length;
        final double width;

        Rectangle(double length, double widht) {
            this.length = length;
            this.width = width;
        }

        @override double area() {
            return length * width;
        }
    }
        
### 클래스 계층구조의 장점
* 런타임, 컴파일 오류의 문제가 발생할 일이 줄어든다.
* 타입이 의미별로 따로 존재하니, 변수의 의미를 명시하거나 제한할 수 있고, 특정 의미만 매개변수로 받을 수 있다.
* 타입 사이의 자연스러운 계층 관계를 반영할 수 있어서 유연성은 물론 컴파일타임 타입 검사 능력을 높여준다.
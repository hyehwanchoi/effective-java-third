# 톱레벨 클래스는 한 파일에 하나만 담으라

    // 두 클래스를 한 파일에 정의
    class Utensil {
        static final String NAME = "pan";
    }
    
    class Dessert {
        static final String NAME = "cake";
    }

    // 정적 멤버 클래스로 변경
    // 읽기 좋고 private 선언으로 접근 범위도 최소로 관리할 수 있다.
    public class Test {
        public static void main(String[] args) {
            System.out.println(Utensil.NAME + Dessert.NAME);
        }

        private static class Utensil {
            static final String NAME = "pan";
        }

        private static class Dessert {
            static final String NAME = "cake";
        }
    }


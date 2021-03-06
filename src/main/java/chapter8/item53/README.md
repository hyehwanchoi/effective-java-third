# 가변인수는 신중히 사용하라

* 가변인수 메서드는 명시한 타입의 인수를 0개 이상 받을 수 있다.
* 가변인수 메서드를 호출하면, 가장 먼저 인수의 개수와 길이가 같은 배열을 만들고 인수들을 이 배열에 저장하여 가변인수 메서드를 건네준다.
* 인수를 0개만 받을 수 있도록 설계하는 건 좋지 않다.
  * 런타임에 실패해서 위험
  * 코드도 지저분해진다.
####
    // 코드가 1개 이상이어야 하는 가변인수 메서드 잘못 사용한 예
    static int min(int... args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("인수가 1개 이상 필요합니다.");
        }
     ...

    // 인수가 1개 이상이어야 할 때 가변인수를 제대로 사용한 예
    static int min(int firstArg, int... remainingArgs) {
        int min = firstArg;
        for (int arg : remainingArgs) {
            if(arg < min) {
                min = arg;
            }
        }
        return min;
    }
* 가변인수는 인수 개수가 정해지지 않았을 때 아주 유용하다.
* 가변인수 메서드는 호출될 때마다 배열을 새로 하나 할당하고 초기화한다.
  * 성능에 민감한 상황이라면 문제가 될 수 있다.
  * 인수가 0개인 것 부터 4개인 것 까지 총 5개를 다중정의하자.
  * ex) EnumSet

### 정리
* 인수 개수가 일정하지 않은 메서드를 정의해야 한다면 가변인수가 반드시 필요하다.
* 메서드를 정의할 때 필수 매개변수는 가변인수 앞에 두고, 가변인수를 사용할 때는 성능 문제까지 고려하자.
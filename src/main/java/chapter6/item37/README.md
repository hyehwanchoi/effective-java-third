# ordinal 인덱싱 대신 EnumMap 을 사용하라

* `ordinal` 값을 이용한 배열을 사용했을 때의 단점은 앞에서 언급한 단점들과 같다.
####

    // ordinal() 을 배열 인덱스로 사용
    Set<Plant>[] plantsByLifeCycle = (Set<Plant>[] new Set[Plant.LifeCycle.values().length];
    for (int i=0; i<plantsByLifeCycle.length; i++) {
        plantsByLifeCycle[i] = new HashSet<>();
    }

    for (Plant p : garden) {
        plantsByLifeCycle[p.lifeCycle.ordinal()].add(p);
    }

    for (int i=0; i<plantsByLifeCycle.length; i++) {
        System.out.printf("%s: %s%n", Plant.LifeCycle.values()[i], plantsByLifeCycle[i]);
    }

    // EnumMap 을 사용해 데이터와 열거 타입을 매핑한다.
    Map<Plant.LifceCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(Plant.LifeCycle.class);
    for (Plant.LifeCycle lc : Plant.LifeCycle.values()) {
        plantsByLifeCycle.put(lc, new HashSet<>());
    }
    for (Plant p : garden) {
        plantsByLifeCycle.get(p.lifeCycle).add(p);
    }
    System.out.println(plantsByLifeCycle);

### EnumMap 을 사용한 코드의 장점
* 더 짧고 명료하고 안전하다.
* 출력도 간단하게 할 수 있다.
* `EnumMap` 은 내부에서 배열을 사용한다.
####

### 스트림과 EnumMap 을 사용하면 더 코드가 간단해진다.
    System.out.println(Arrays.stream(garden).collect(groupingBy(p->p.lifeCycle,
        () -> new EnumMap<>(LifeCycle.class), toSet())));
####

    // 배열들의 배열의 인덱스에 ordinal() 을 사용
    public enum Phase {
        SOLID, LIQUID, GAS;

        public enum Transition {
            MELT, FREEZE, BOIL, CONDENSE, SUBLIME, DEPOSIT;

            private static final Transition[][] TRANSITIONS = {
                { null, MELT, SUBLIME },
                { FREEZE, NULL, BOIL },
                { DEPOSIT, CONDENSE, null }
            };

            public static Transition from(Phase from, Phase to) {
                return TRANSITIONS[from.ordinal()][to.ordinal()];
            }
        }
    }

    // 위의 코드를 중첩 EnumMap 으로 변경
    public enum Phase {
        SOLID, LIQUID, GAS;

        public enum Transition {
            MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID),
            BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID),
            SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID);

            private final Phase from;
            private final Pahse to;

            Transition(Phase from, Phase to) {
                this.from = from;
                this.to = to;
            }

            private static final Map<Phase, Map<Phase, Transition>> m = 
            Stream.of(values()).collect(groupingBy(t -> t.from,
                () -> new EnumMap<>(Phase.class),
                toMap(t->t.to, t->t, (x,y) -> y, () -> new EnumMap<>(Phase.class))));

            public static Transition from(Phase from, Phase to) {
                return m.get(from).get(to);
            }
        }
    }
* 중첩 EnumMap 으로 작성한 것이 복잡해 보이지만 낭비되는 공간 없이 명확해보인다.

### 정리
* `Enum` 을 사용해서 배열이나 리스트를 만들어 사용할 때 EnumMap 을 사용해보자 !
* 왜 난 이런 코드를 작성해볼 일이 없었을까를 생각해보면 다양하게? 작성해보지 않았나 라는 생각이 든다.
* 코드를 작성할 때 여러가지로 시도를 해봐야겠다. 우선 다양한 지식부터 쌓을 것...
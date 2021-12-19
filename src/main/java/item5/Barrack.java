package item5;

import java.util.function.Supplier;

// 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라.
public class Barrack {

    private final Produce produce;

    public Barrack(Supplier<? extends Produce> produce) {
        this.produce = produce.get();
    }
}

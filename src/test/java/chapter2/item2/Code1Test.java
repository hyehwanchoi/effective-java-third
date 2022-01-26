package chapter2.item2;

import org.junit.jupiter.api.Test;

class Code1Test {

    @Test
    void builderTest() {
        Code1 code = new Code1.Builder("hong", 30).company("muhayu").build();

        System.out.println(code.toString());
    }
}
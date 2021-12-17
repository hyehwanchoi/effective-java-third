package item3;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Code2Test {

    @Test
    void singletonTest() {
        Code2 code1 = Code2.getInstance();
        Code2 code2 = Code2.getInstance();

        assertThat(code1).isEqualTo(code2);
    }
}
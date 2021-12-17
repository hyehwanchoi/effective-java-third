package item3;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Code3Test {

    @Test
    void test() {
        assertThat(Code3.getInstance()).isEqualTo(Code3.getInstance());
    }
}
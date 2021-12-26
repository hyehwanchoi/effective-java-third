package Chapter2.item6;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Code2Test {

    @Test
    void test() {
        assertThat(Code2.isRomanNumeral("MDCLXV")).isTrue();
    }
}
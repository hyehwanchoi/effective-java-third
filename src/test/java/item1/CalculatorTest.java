package item1;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {

    @Test
    void test() {
        assertThat(Calculator.newEvenInstance()).isNotNull();
        assertThat(Calculator.newEvenInstance().calc(3) == false).isTrue();
    }
}
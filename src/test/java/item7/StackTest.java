package item7;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StackTest {

    @Test
    void test() {
        Stack stack = new Stack();
        stack.push(1);

        assertThat(stack.pop()).isEqualTo(1);
    }
}
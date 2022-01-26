package chapter2.item3;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Code1Test {

  @Test
  void singletonTest() {
    Code1 code1 = Code1.INSTANCE;
    Code1 code2 = Code1.INSTANCE;

    assertThat(code1).isEqualTo(code2);
  }
}
package chpater2.item3;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Code4Test {

    @Test
    void serializableTest() {
        Code4 code = Code4.getInstance();
        Serializable serializableTest = new Serializable();
        byte[] serializedData = serializableTest.serializable(code);
        Code4 result = (Code4)serializableTest.deserializable(serializedData);

        assertThat(code).isEqualTo(result);
        assertThat(code == result).isTrue();
    }
}
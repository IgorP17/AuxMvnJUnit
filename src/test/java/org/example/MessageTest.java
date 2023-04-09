package org.example;

import org.example.MyMessage;
import org.example.MyMessageFields;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MessageTest {


    @ParameterizedTest(name = "{index}. msg is {0}")
    @MethodSource("provideMessages")
    public void test(MyMessage incoming, MyMessage expected) {
        System.out.println("Incoming: " + incoming);
        System.out.println("Expecting: " + expected);
        Assertions.assertEquals(incoming, expected);
    }

    private static Stream<Arguments> provideMessages() {
        return Stream.of(
                Arguments.of(generateDefaultMessage(), generateDefaultMessage()),
                Arguments.of(
                        generateDefaultMessage()
                                .setField(MyMessageFields.FIELD_1, "ААААА крокодилы"),
                        generateDefaultMessage()
                                .setField(MyMessageFields.FIELD_1, "ААААА крокодилы")),
                Arguments.of(
                        generateDefaultMessage()
                                .setField(MyMessageFields.FIELD_2, "ААААА бегемоты"),
                        generateDefaultMessage()
                                .setField(MyMessageFields.FIELD_2, "ААААА бегемоты")),
                Arguments.of(
                        generateDefaultMessage()
                                .setField(MyMessageFields.FIELD_3, "И зеленый попугай"),
                        generateDefaultMessage()
                                .setField(MyMessageFields.FIELD_3, "И зеленый попугай"))
        );
    }

    private static MyMessage generateDefaultMessage() {
        return new MyMessage("defaultA", "defaultB", "defaultC");
    }


}

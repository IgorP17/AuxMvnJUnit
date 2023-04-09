package org.example;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;


public class MyMathTest {
    @Test
    public void checkZeroDenomShouldThrowException() {
        Assertions.assertThrowsExactly(ArithmeticException.class, () -> MyMath.divide(1, 0));
    }

    @ParameterizedTest(name = "{index}. {0} / {1} == {2}")
    @CsvSource({
            "3, 2, 1",
            "6, 2, 3"
    })
    public void test(int a, int b, int expected) {
        Assertions.assertEquals(MyMath.divide(a, b), expected);
    }

    @ParameterizedTest(name = "{index}. {0} / {1} == {2}")
    @CsvFileSource(resources = "for_divide.csv", numLinesToSkip = 1, delimiter = '#')
    public void testFromFile(int a, int b, int expected) {
        Assertions.assertEquals(MyMath.divide(a, b), expected);
    }

    /*
        Example how to use steps
     */
    @Step("Проверка суммы числа {num1} и числа {num2}")
    public static void checkSumStep(int num1, int num2, int expectedResult) {
        Assertions.assertEquals(expectedResult, MyMath.sum(num1, num2), "Результат не соответствует ожидаемому значению");
    }

    // @Epic → @Feature → @Story
    @Epic(value = "Математика")
    @Feature(value = "Простые математические операции")
    @Story(value = "Сложение")
    @Test
    public void sumTest() {
        checkSumStep(3, 2, 5);
        checkSumStep(5, 4, 9);
    }

    @Step("Проверка разности числа {num1} и числа {num2}")
    public static void checkSubtractionStep(int num1, int num2, int expectedResult) {
        Assertions.assertEquals(expectedResult, MyMath.subtraction(num1, num2), "Результат не соответствует ожидаемому значению");
    }

    @Epic(value = "Математика")
    @Feature(value = "Простые математические операции")
    @Story(value = "Вычитание")
    @Test
    public void substTest() {
        checkSubtractionStep(3, 2, 1);
        checkSubtractionStep(3, 4, -1);
    }

    @Step("Проверка гипотенузы с катетами {num1} и {num2}")
    public static void checkHypotenuseStep(int num1, int num2, int expectedResult) {
        Assertions.assertEquals(expectedResult, MyMath.hypotenuse(num1, num2), "Результат не соответствует ожидаемому значению");
    }

    @Epics(value = {@Epic(value = "Математика"), @Epic(value = "Геометрия")})
    @Features(value = {@Feature(value = "Тригонометрия"), @Feature(value = "Простые математические операции")})
    @Stories(value = {@Story(value = "Треугольники"), @Story(value = "Прямоугольные треугольники")})
    @Test
    public void checkHypotenuseTest() {
        checkHypotenuseStep(3, 4, 5);
    }

    /*
        Example of attachments
     */
    @Attachment
//    @Attachment(value = "Вложение", type = "application/json", fileExtension = ".txt")
    public static byte[] getBytes(String resourceName) throws IOException {
        return Files.readAllBytes(Paths.get("src/test/resources", resourceName));
    }

    @Step("Проверка эквивалентности строки {str1} строке {str2}")
    public static void checkStringEqualsStep(String str1, String str2) throws IOException {
        Assertions.assertTrue(str1.equals(str2), "Строки не эквивалентны");
        getBytes("picture.png");
        getBytes("text.txt");
//        Allure.addAttachment("Результат", "text/plain", link);
    }

    @Test
    @Description(value = "Тест с аттачментами")
    public void testWithAttachment() throws IOException {
        String darkSouls = "Dark souls 3";
        checkStringEqualsStep(darkSouls, darkSouls);
    }

    /*
        Unstable test
     */
    @Test
    @Flaky
    public void testDemoFlaky() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 2);
        if (randomNum == 0) {
            Assertions.assertTrue(true);
        } else {
            Assertions.assertTrue(false);
        }
    }

    /*
        Linking and owner
     */

    @Test
    @Issue(value = "FGY-4627")
    @TmsLinks({@TmsLink(value = "TL-135"), @TmsLink(value = "TL-158")})
    @Links(value = {@Link(name = "Ссылка1", url = "http://sberbank.ru"),
            @Link(name = "Ссылка2", url = "http://yandex.ru")})
    @Owner(value = "Пупкин Валерий Иванович")
    @Severity(value = SeverityLevel.CRITICAL)
    public void linksTest() {
        Assertions.assertTrue(1 == 1);
    }
}

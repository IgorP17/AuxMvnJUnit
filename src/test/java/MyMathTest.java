import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.example.MyMath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


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

    @Test
    public void sumTest() {
        checkSumStep(3, 2, 5);
        checkSumStep(5, 4, 9);
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

}

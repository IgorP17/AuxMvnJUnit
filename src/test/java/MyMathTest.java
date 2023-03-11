import org.example.MyMath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;


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
    public void test(int a, int b, int expected){
        Assertions.assertEquals(MyMath.divide(a, b), expected);
    }

    @ParameterizedTest(name = "{index}. {0} / {1} == {2}")
    @CsvFileSource(resources = "for_divide.csv", numLinesToSkip = 1, delimiter = '#')
    public void testFromFile(int a, int b, int expected){
        Assertions.assertEquals(MyMath.divide(a, b), expected);
    }
}

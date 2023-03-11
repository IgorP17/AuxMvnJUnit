import org.example.MyMath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MyMathTest {
    @Test
    public void checkZeroDenomShouldThrowException() {
        Assertions.assertThrowsExactly(ArithmeticException.class, () -> MyMath.divide(1, 0));
    }
}

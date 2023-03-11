import org.example.MyMath;
import org.junit.Test;

public class MyMathTest {


    @Test(expected = ArithmeticException.class)
    public void checkZeroDenomShouldThrowException() {
        int a = MyMath.divide(1, 0);
    }
}

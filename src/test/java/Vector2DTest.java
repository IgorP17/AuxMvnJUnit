import org.example.Vector2D;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Vector2DTest {

    private static final double EPS = 1e-9;
    private Vector2D v1;

    @Before
    public void createNewVector(){
        v1 = new Vector2D(); // свой для каждого метода, если нужно 1 раз перед тестами - static BeforeClass
    }

    @Test
    public void newVectorShouldHaveZeroLength() {
        Assert.assertEquals(0, v1.length(), EPS);
    }

    @Test
    public void newVectorShouldHaveZeroX() {
        Assert.assertEquals(0, v1.getX(), EPS);
    }

    @Test
    public void newVectorShouldHaveZeroY() {
        Assert.assertEquals(0, v1.getY(), EPS);
    }
}

import org.example.NetworkUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class NetworkUtilsTest {

    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    public void getConnectionShouldBeFasterThanOneSecond() {
        NetworkUtils.getConnection();
    }
}

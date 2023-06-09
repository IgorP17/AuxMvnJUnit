package org.example;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.example.Vector2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Vector2DTest {

    private static final double EPS = 1e-9;
    private Vector2D v1;

    @BeforeEach
    public void createNewVector(){
        v1 = new Vector2D(); // свой для каждого метода, если нужно 1 раз перед тестами - static BeforeClass/BeforeAll JUnit 5
    }

    @Test
    public void newVectorShouldHaveZeroLength() {
        Assertions.assertEquals(0, v1.length(), EPS);
    }

    @Test
    public void newVectorShouldHaveZeroX() {
        Assertions.assertEquals(0, v1.getX(), EPS);
    }

    @Test
    public void newVectorShouldHaveZeroY() {
        Assertions.assertEquals(0, v1.getY(), EPS);
    }

    @Test
    public void thisTestWillFail1(){
        Assertions.assertEquals("aaa", "2");
    }

    @Test
    @Severity(value = SeverityLevel.BLOCKER)
    public void thisTestWillFail2(){
        Assertions.assertEquals("aaa", "3");
    }

    @Test
    public void thisTestWillFail3(){
        Assertions.assertEquals("bbb", "4");
    }
}

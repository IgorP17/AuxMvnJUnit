package org.example;

public class MyMath {
    public static int divide(int n1, int n2) {
        if (n2 == 0) {
            throw new ArithmeticException("Can't divide by zero!");
        }
        return n1 / n2;
    }

    public static int sum(int a, int b) {
        return a + b;
    }

    public static int subtraction(int a, int b) {
        return a - b;
    }

    public static int hypotenuse(int a, int b) {
        return (int) Math.sqrt(a * a + b * b);
    }
}

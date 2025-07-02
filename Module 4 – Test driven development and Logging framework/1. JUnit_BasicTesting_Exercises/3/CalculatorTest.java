package com.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        System.out.println("Setting up Calculator...");
        calculator = new Calculator();
    }

    @AfterEach
    void tearDown() {
        System.out.println("Cleaning up after test...");
        calculator = null;
    }

    @Test
    void testAddition() {
        int result = calculator.add(4, 6);
        assertEquals(10, result);
    }

    @Test
    void testSubtraction() {
        int result = calculator.subtract(10, 2);
        assertEquals(8, result);
    }
}

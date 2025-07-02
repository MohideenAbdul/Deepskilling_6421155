package com.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
public class OrderedTests {

    @Test
    @Order(2)
    void secondTest() {
        System.out.println("Second test");
    }

    @Test
    @Order(1)
    void firstTest() {
        System.out.println("First test");
    }
}
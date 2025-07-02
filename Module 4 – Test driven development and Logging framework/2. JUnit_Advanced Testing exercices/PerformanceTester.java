package com.example;

public class PerformanceTester {
    public void performTask() {
        try {
            Thread.sleep(200); // Simulate delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
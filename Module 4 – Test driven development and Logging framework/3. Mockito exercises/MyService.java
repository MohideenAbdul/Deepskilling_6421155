package com.example;

public class MyService {
    private ExternalApi api;

    public MyService(ExternalApi api) {
        this.api = api;
    }

    public String fetchData() {
        return api.getData();
    }

    public void performAction(String input) {
        api.logAction(input);
    }

    public void runDangerousOperation() {
        api.dangerousOperation();
    }
}
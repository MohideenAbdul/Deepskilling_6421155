package com.example;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.InOrder;

public class MyServiceTest {

    @Test
    public void testExternalApiMockingAndStubbing() {
        System.out.println("------ Running testExternalApiMockingAndStubbing ------");
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("Mock Data");
        MyService service = new MyService(mockApi);

        System.out.println("Calling fetchData on service with mocked ExternalApi...");
        String result = service.fetchData();
        System.out.println("Result: " + result);

        assertEquals("Mock Data", result);
    }

    @Test
    public void testVerifyInteraction() {
        System.out.println("------ Running testVerifyInteraction ------");
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        System.out.println("Calling service.fetchData()...");
        service.fetchData();

        System.out.println("Verifying if getData() was called...");
        verify(mockApi).getData();
        System.out.println("Verification passed.");
    }

    @Test
    public void testArgumentMatching() {
        System.out.println("------ Running testArgumentMatching ------");
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        System.out.println("Performing action: Hello");
        service.performAction("Hello");

        System.out.println("Verifying logAction with 'Hello'");
        verify(mockApi).logAction("Hello");
        System.out.println("Argument verification passed.");
    }

    @Test
    public void testHandleVoidMethod() {
        System.out.println("------ Running testHandleVoidMethod ------");
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        doNothing().when(mockApi).logAction(anyString());

        MyService service = new MyService(mockApi);
        System.out.println("Calling performAction with 'Logged'...");
        service.performAction("Logged");

        System.out.println("Verifying logAction was called...");
        verify(mockApi).logAction("Logged");
        System.out.println("Void method verified.");
    }

    @Test
    public void testMultipleReturns() {
    System.out.println("------ Running testMultipleReturns ------");
    ExternalApi mockApi = Mockito.mock(ExternalApi.class);
    when(mockApi.getData()).thenReturn("First", "Second", "Third");

    MyService service = new MyService(mockApi);

    String first = service.fetchData();
    System.out.println("First call: " + first);
    assertEquals("First", first);

    String second = service.fetchData();
    System.out.println("Second call: " + second);
    assertEquals("Second", second);

    String third = service.fetchData();
    System.out.println("Third call: " + third);
    assertEquals("Third", third);
}


    @Test
    public void testVerifyInteractionOrder() {
        System.out.println("------ Running testVerifyInteractionOrder ------");
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        System.out.println("Calling performAction and then fetchData...");
        service.performAction("Step 1");
        service.fetchData();

        System.out.println("Verifying call order...");
        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).logAction("Step 1");
        inOrder.verify(mockApi).getData();
        System.out.println("Interaction order verified.");
    }

    @Test
    public void testVoidMethodThrowsException() {
        System.out.println("------ Running testVoidMethodThrowsException ------");
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        doThrow(new RuntimeException("Boom")).when(mockApi).dangerousOperation();

        MyService service = new MyService(mockApi);
        System.out.println("Calling runDangerousOperation which should throw exception...");
        assertThrows(RuntimeException.class, service::runDangerousOperation);
        System.out.println("Exception was thrown and caught successfully.");
    }
}

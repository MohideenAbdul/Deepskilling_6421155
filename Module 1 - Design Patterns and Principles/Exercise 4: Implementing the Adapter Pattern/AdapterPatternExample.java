public class AdapterPatternExample {
    public static void main(String[] args) {
        PaymentProcessor paypalProcessor = new PayPalAdapter(new PayPalGateway());
        PaymentProcessor stripeProcessor = new StripeAdapter(new StripeGateway());

        paypalProcessor.processPayment(100.0);
        stripeProcessor.processPayment(200.0);
    }
}

interface PaymentProcessor {
    void processPayment(double amount);
}

class PayPalGateway {
    public void makePayment(double amountInDollars) {
        System.out.println("Processing PayPal payment of $" + amountInDollars);
    }
}

class StripeGateway {
    public void pay(double amount) {
        System.out.println("Processing Stripe payment of $" + amount);
    }
}

class PayPalAdapter implements PaymentProcessor {
    private PayPalGateway payPalGateway;
    public PayPalAdapter(PayPalGateway payPalGateway) {
        this.payPalGateway = payPalGateway;
    }
    @Override
    public void processPayment(double amount) {
        payPalGateway.makePayment(amount);
    }
}

class StripeAdapter implements PaymentProcessor {
    private StripeGateway stripeGateway;
    public StripeAdapter(StripeGateway stripeGateway) {
        this.stripeGateway = stripeGateway;
    }
    @Override
    public void processPayment(double amount) {
        stripeGateway.pay(amount);
    }
}

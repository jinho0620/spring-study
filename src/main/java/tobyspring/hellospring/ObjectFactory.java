package tobyspring.hellospring;

// Create object and inject dependency
public class ObjectFactory {
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    public ExRateProvider exRateProvider() {
        return new SimpleExRateProvider();
    }
}

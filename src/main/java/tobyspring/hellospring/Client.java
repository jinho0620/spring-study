package tobyspring.hellospring;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {

    public static void main(String[] args) throws IOException {
        PaymentService paymentService = new SimpleExRatePaymentService();
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.74));
        System.out.println(payment);
    }
}

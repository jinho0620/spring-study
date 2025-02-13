package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring.payment.ExRateProvider;
import tobyspring.hellospring.exrate.WebAPIExRateProvider;
import tobyspring.hellospring.payment.PaymentService;

import java.time.Clock;

// Create object and inject dependency
@Configuration
@ComponentScan
public class PaymentConfig {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(), clock());
    }

    @Bean // Bean을 만드는 method다.
    public ExRateProvider exRateProvider() {
        return new WebAPIExRateProvider();
    }

//    @Bean // Bean을 만드는 method다.
//    public ExRateProvider cachedExRateProvider() {
//        return new CachedExRateProvider(exRateProvider());
//    }
    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

}

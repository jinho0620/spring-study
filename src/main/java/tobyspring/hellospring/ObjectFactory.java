package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring.exrate.CachedExRateProvider;
import tobyspring.hellospring.payment.ExRateProvider;
import tobyspring.hellospring.exrate.WebAPIExRateProvider;
import tobyspring.hellospring.payment.PaymentService;

// Create object and inject dependency
@Configuration
@ComponentScan
public class ObjectFactory {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider());
    }

    @Bean // Bean을 만드는 method다.
    public ExRateProvider exRateProvider() {
        return new WebAPIExRateProvider();
    }

    @Bean // Bean을 만드는 method다.
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }
}

package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring.api.ApiTemplate;
import tobyspring.hellospring.api.ExRateApiExtractor;
import tobyspring.hellospring.api.SimpleApiExecutor;
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

    @Bean
    public ApiTemplate apiTemplate() {
        return new ApiTemplate(new SimpleApiExecutor(), new ExRateApiExtractor());
    }

    @Bean // Bean을 만드는 method다.
    public ExRateProvider exRateProvider() {
        return new WebAPIExRateProvider(apiTemplate());
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

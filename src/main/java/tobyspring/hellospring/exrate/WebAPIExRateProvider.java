package tobyspring.hellospring.exrate;

import tobyspring.hellospring.api.*;
import tobyspring.hellospring.payment.ExRateProvider;

import java.math.BigDecimal;


public class WebAPIExRateProvider implements ExRateProvider {
    ApiTemplate apiTemplate = new ApiTemplate(); // Thread끼리 공유해도 상관없기 때문에 class에 배속시킨다.

    // client(getExRate())가 callback(new SimpleApiExecutor())을 담아서 template(runExRateApi())를 호출한다.
    @Override
    public BigDecimal getExRate(String currency) {

        String url = "https://open.er-api.com/v6/latest/" + currency;
        // Template에서는 callback은 method 1개짜리 interface를 구현한 것만 가능하다. -> lambda식 가능
        return apiTemplate.getExRate(url, new SimpleApiExecutor(), new ErApiExRateExtractor()); // SimpleApiExecutor : callback
    }


}

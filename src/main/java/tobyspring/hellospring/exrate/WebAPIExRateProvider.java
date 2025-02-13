package tobyspring.hellospring.exrate;

import tobyspring.hellospring.api.*;
import tobyspring.hellospring.payment.ExRateProvider;

import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class WebAPIExRateProvider implements ExRateProvider {
    private final ApiTemplate apiTemplate; // 매번 만들지 않고 thread 끼리 공유 -> 상태값을 저장하면 singleton으로 사용하면 안됨 (ex. user정보)

    public WebAPIExRateProvider(ApiTemplate apiTemplate) {
        this.apiTemplate = apiTemplate;
    }

    @Override
    public BigDecimal getExRate(String currency) {

        String url = "https://open.er-api.com/v6/latest/" + currency;
        // Template에서는 callback은 method 1개짜리 interface를 구현한 것만 가능하다. -> lambda식 가능
        return apiTemplate.getExRate(url); // SimpleApiExecutor : callback
    }
}

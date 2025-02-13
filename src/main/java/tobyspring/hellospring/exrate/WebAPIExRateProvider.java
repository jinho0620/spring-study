package tobyspring.hellospring.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tobyspring.hellospring.api.ApiExecutor;
import tobyspring.hellospring.api.ErApiExRateExtractor;
import tobyspring.hellospring.api.ExRateExtractor;
import tobyspring.hellospring.api.SimpleApiExecutor;
import tobyspring.hellospring.payment.ExRateProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;


public class WebAPIExRateProvider implements ExRateProvider {

    // client(getExRate())가 callback(new SimpleApiExecutor())을 담아서 template(runExRateApi())를 호출한다.
    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;

        return runExRateApi(url, new SimpleApiExecutor(), new ErApiExRateExtractor()); // SimpleApiExecutor : callback
    }

    // template (환율 정보 API 의 기본 틀)
    private BigDecimal runExRateApi(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = apiExecutor.execute(uri); // execute logic이 변하더라도 WebAPIExRateProvider class는 변경할 필요가 없다.
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return exRateExtractor.extract(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

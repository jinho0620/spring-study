package tobyspring.hellospring.exrate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true) // Json에 있어도 record에서 안 쓰는 데이터는 무시한다.
public record ExRateData(
        String result,
        Map<String, BigDecimal> rates
) {
}

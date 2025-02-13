package tobyspring.hellospring.payment;

import java.io.IOException;
import java.math.BigDecimal;

public interface ExRateProvider {
    BigDecimal getExRate(String currency); // interface의 모든 method는 기본적으로 public이다.
}

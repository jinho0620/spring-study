package tobyspring.hellospring.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tobyspring.hellospring.exrate.WebAPIExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    @Test
//    @DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
    void convertedAmount() throws IOException {
        testAmount(BigDecimal.valueOf(500), BigDecimal.valueOf(5_000));
        testAmount(BigDecimal.valueOf(1_000), BigDecimal.valueOf(10_000));
        testAmount(BigDecimal.valueOf(3_000), BigDecimal.valueOf(30_000));


        // 원화환산금액의 유효시간 계산
//        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
//        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));

    }

    private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate));

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 환율 정보를 가져온다. (isEqualTo는 소수점 유효숫자 갯수까지 다 비교한다.)
        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);

        // 원화 환산금액 계산
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
    }
}
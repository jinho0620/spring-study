package tobyspring.hellospring;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import tobyspring.hellospring.data.OrderRepository;
import tobyspring.hellospring.order.Order;
import tobyspring.hellospring.payment.PaymentService;

import java.math.BigDecimal;

public class DataClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository orderRepository = beanFactory.getBean(OrderRepository.class);
        JpaTransactionManager transactionManager = beanFactory.getBean(JpaTransactionManager.class);

        try {
            new TransactionTemplate(transactionManager).execute(status -> {
                Order order = new Order("100", BigDecimal.TEN);

                orderRepository.save(order);

                System.out.println(order);

                Order order2 = new Order("100", BigDecimal.ONE);
                orderRepository.save(order2);
                return null;
            });
        }
        catch (DataIntegrityViolationException e) {
            System.out.println("주문번호 중복 복구 작업");
        }


    }
}

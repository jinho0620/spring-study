package tobyspring.hellospring.order;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String no; // 주문 번호 (유저들한테 보여주는 의미있는 값)
    private BigDecimal total; // 금액

    // jpa를 사용할 때는 parameter가 있는 생성자를 만들 때 기본 생성자도 만들어줘야 한다.
    public Order() {
    }

    public Order(String no, BigDecimal total) {
        this.no = no;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public String getNo() {
        return no;
    }

    public BigDecimal getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", no='" + no + '\'' +
                ", total=" + total +
                '}';
    }
}

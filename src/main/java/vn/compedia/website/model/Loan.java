package vn.compedia.website.model;

import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "loan")
@SessionScoped
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id ;

    @Column(name = "cus_id")
    private Long cusId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "month")
    private int month;

    @Column(name = "bank_rate")
    private float bankRate;

    @Column(name = "monthly_profit")
    private double monthlyProfit;

    @Column(name = "amount_remain")
    private double amountRemain;

}

package vn.compedia.website.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "information")
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private  String name;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "type")
    private String type;

    @Column(name = "job")
    private String job;

    @Column(name = "income")
    private Double income;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "bank_rate")
    private float bankRate;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "status")
    private int status;

    @Column(name = "numberOfMonth")
    private int numberOfMonth;

}

package vn.compedia.website.model;

import lombok.Getter;
import lombok.Setter;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "customer")
@SessionScoped
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id ;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "idCard")
    private String idCard;

    @Column(name = "idBank_Card")
    private Long idBankCard;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "email")
    private String email;

}

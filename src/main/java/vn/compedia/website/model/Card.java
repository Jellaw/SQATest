package vn.compedia.website.model;

import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.SessionScoped;
import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "bank_card")
@SessionScoped
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id ;

    @Column(name = "number")
    private String number;

    @Column(name = "type")
    private String type;

    @Column(name = "amount")
    private double amount;
}

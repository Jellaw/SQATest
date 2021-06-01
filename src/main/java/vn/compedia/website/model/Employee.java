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
@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@SessionScoped
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "e_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "address")
    private String address;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "phone")
    private String phone;

    @Column(name = "permission")
    private String permission;

    @Column(name = "e_code")
    private String eCode;

    @Column(name = "salt")
    private String salt;

    @Column(name = "status")
    private String status;

    @Column(name = "gender")
    private String gender;
}

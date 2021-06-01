package vn.compedia.website.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "account")
@NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private int accountId;
    private String email;
    private String fullname;
    private Date insertDate;
    private int mngLocationId;
    private String password;
    private int roleId;
    private int status;
    private Date updateDate;
    private String username;

    public Account() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }


    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "insert_date")
    public Date getInsertDate() {
        return this.insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }


    @Column(name = "mng_location_id")
    public int getMngLocationId() {
        return this.mngLocationId;
    }

    public void setMngLocationId(int mngLocationId) {
        this.mngLocationId = mngLocationId;
    }


    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Column(name = "role_id")
    public int getRoleId() {
        return this.roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }


    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

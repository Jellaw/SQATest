package vn.compedia.website.controller;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import vn.compedia.website.model.Employee;
import vn.compedia.website.repository.EmployeeRepository;
import vn.compedia.website.util.Constant;
import vn.compedia.website.util.FacesUtil;
import vn.compedia.website.util.StringUtil;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Named
@SessionScoped
public class AuthorizationController implements Serializable {

    @Inject
    private HttpServletRequest request;

     @Autowired
    private EmployeeRepository employeeRepository;

     private int role;
     private String password1, rePassword;
     private Employee employee ,newEmployee;
     public InformationController informationController;


     public void init(){
         if (!FacesContext.getCurrentInstance().isPostback()) {
             if (hasLogged()) {
                 FacesUtil.redirect("/mn_customer/dashboard.xhtml");
                 informationController.em_id= employee.getId();
                 return;
             }
             resetAll();
         }
     }

     public void resetAll(){
            role =0;
            employee = new Employee();
            newEmployee = new Employee();
     }

    public void login() {
        if (StringUtils.isBlank(employee.getUsername().trim())) {
            FacesUtil.addErrorMessage(Constant.ERROR_GROWL, "Bạn phải nhập tên đăng nhập");
            return;
        }
        if (StringUtils.isBlank(employee.getPassword())) {
            FacesUtil.addErrorMessage(Constant.ERROR_GROWL, "Bạn phải nhập mật khẩu");
            return;
        }
        Employee employeeCheck = employeeRepository.findByUsername(employee.getUsername().trim());
        if (employeeCheck == null) {
            FacesUtil.addErrorMessage(Constant.ERROR_GROWL, "Tên đăng nhập hoặc mật khẩu không chính xác");
            return;
        }
        if (!employeeCheck.getPassword().equals(StringUtil.encryptPassword(employee.getPassword(), employeeCheck.getSalt()))) {
            FacesUtil.addErrorMessage(Constant.ERROR_GROWL, "Tên đăng nhập hoặc mật khẩu không chính xác");
            return;
        }
        if (employeeCheck.getPassword().equals(StringUtil.encryptPassword(employee.getPassword(), employeeCheck.getSalt()))){
                FacesUtil.redirect("/mn_customer.xhtml");
            return;
        }


    }
    public void onSave(){
         if(StringUtils.isBlank(newEmployee.getName())){
             FacesUtil.addErrorMessage("Tên nhân viên không được để trống");
             return;
         }
        if(StringUtils.isBlank(newEmployee.getECode())){
            FacesUtil.addErrorMessage("Mã nhân viên không được để trống");
            return;
        }
        if(StringUtils.isBlank(newEmployee.getUsername())){
            FacesUtil.addErrorMessage("Tên đăng nhập không được để trống");
            return;
        }

        if(!rePassword.trim().equals(password1.trim())){
            FacesUtil.addErrorMessage("Mật khẩu không trùng khớp");
            return;
        }
            newEmployee.setSalt(RandomStringUtils.randomAlphanumeric(10));
            newEmployee.setPassword(StringUtil.encryptPassword(password1,newEmployee.getSalt()));
            employeeRepository.save(newEmployee);
            FacesUtil.addSuccessMessage("Thành công");
            FacesUtil.closeDialog("addUser");
    }
    public boolean hasLogged() {
        return role != Constant.NOT_LOGIN_ID;
    }

}

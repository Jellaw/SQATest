package vn.compedia.website.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import vn.compedia.website.model.Employee;
import vn.compedia.website.repository.EmployeeRepository;
import vn.compedia.website.util.StringUtil;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorizationControllerTest {
    @MockBean
    private MockMvc mockMvc;
    @Autowired
    EmployeeRepository employeeRepository;
    @Test
    public void login() throws Exception{
        Employee employee = new Employee();
        employee.setUsername("SqaDacTa12");
        employee.setPassword("123456");
        Employee employeeCheck = employeeRepository.findByUsername(employee.getUsername().trim());
        when(employeeCheck.getPassword().equals(StringUtil.encryptPassword(employee.getPassword(), employeeCheck.getSalt()))).thenReturn(true);
        RequestBuilder request = post("/")
                .param("authorizationController.employee.username", "SqaDacTa12")
                .param("authorizationController.employee.password", "123456");

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(redirectedUrl("dashboard"));
    }
}
package vn.compedia.website.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import vn.compedia.website.model.Employee;
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>, EmployeeRepositoryCustom {
    Employee findByUsername(String username);

}

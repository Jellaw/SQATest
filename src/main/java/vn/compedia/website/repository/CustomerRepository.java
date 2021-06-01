package vn.compedia.website.repository;

import org.springframework.data.repository.CrudRepository;
import vn.compedia.website.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer,Long> ,CustomerRepositoryCustom{

}

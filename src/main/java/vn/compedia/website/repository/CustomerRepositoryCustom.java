package vn.compedia.website.repository;

import vn.compedia.website.dto.CustomerSearchDto;
import vn.compedia.website.dto.InformationSearchDto;
import vn.compedia.website.model.Customer;

import java.util.List;

public interface CustomerRepositoryCustom {
    List<Customer> search(CustomerSearchDto searchDto);
    Long countSearch(CustomerSearchDto searchDto);
}

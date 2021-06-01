package vn.compedia.website.repository;

import org.springframework.data.repository.CrudRepository;
import vn.compedia.website.model.Loan;

public interface LoanRepository extends CrudRepository<Loan , Long> ,LoanRepositoryCustom{
}

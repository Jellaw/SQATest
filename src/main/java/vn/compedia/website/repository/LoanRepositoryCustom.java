package vn.compedia.website.repository;

import vn.compedia.website.dto.InformationSearchDto;
import vn.compedia.website.dto.LoanSearchDto;
import vn.compedia.website.model.Loan;

import java.util.List;

public interface LoanRepositoryCustom {
    List<Loan> search(LoanSearchDto searchDto);
    Long countSearch(LoanSearchDto searchDto);
}

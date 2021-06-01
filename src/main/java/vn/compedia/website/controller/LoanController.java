package vn.compedia.website.controller;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.SessionScope;
import vn.compedia.website.dto.CustomerSearchDto;
import vn.compedia.website.dto.LoanSearchDto;
import vn.compedia.website.model.Customer;
import vn.compedia.website.model.Information;
import vn.compedia.website.model.Loan;
import vn.compedia.website.repository.LoanRepository;
import vn.compedia.website.util.FacesUtil;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@Named
@SessionScope
public class LoanController implements Serializable {

    @Autowired
    private LoanRepository loanRepository ;

    LazyDataModel lazyDataModel;
    LoanSearchDto searchDto;



    public void init() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            reserAll();
        }
    }

    public void reserAll() {
        searchDto = new LoanSearchDto();
        onSearch();
    }


    public void onSearch(){
        lazyDataModel = new LazyDataModel<Loan>() {
            @Override
            public List<Loan> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, FilterMeta> filterBy) {
                searchDto.setPageIndex(first);
                searchDto.setPageSize(pageSize);
                searchDto.setSortField(sortField);
                String sort = "";
                if (sortOrder.equals(SortOrder.ASCENDING)) {
                    sort = "ASC";
                } else {
                    sort = "DESC";
                }
                searchDto.setSortOrder(sort);
                return loanRepository.search(searchDto);
            }
            @Override
            public Loan getRowData(String rowKey) {
                List<Loan> loanList = getWrappedData();
                Long value = Long.valueOf(rowKey);
                for (Loan  loan : loanList) {
                    if (loan.getId().equals(value)) {
                        return loan;
                    }
                }
                return null;
            }
        };
        int count = loanRepository.countSearch(searchDto).intValue();
        lazyDataModel.setRowCount(count);
        FacesUtil.updateView("searchForm");

    }


}

package vn.compedia.website.controller;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.SessionScope;
import vn.compedia.website.dto.CustomerSearchDto;
import vn.compedia.website.model.Card;
import vn.compedia.website.model.Customer;
import vn.compedia.website.model.Information;
import vn.compedia.website.repository.CardRepository;
import vn.compedia.website.repository.CustomerRepository;
import vn.compedia.website.repository.InformationRepository;
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
public class CustomerController implements Serializable {

    @Autowired
    private InformationRepository inFormationRepository ;

    @Autowired
    private CustomerRepository customerRepository ;

    @Autowired
    private CardRepository cardRepository ;

    LazyDataModel lazyDataModel;
    private Information information;
    Customer customer;
    CustomerSearchDto searchDto;

    public void init() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            reserAll();
        }
    }

    public void reserAll() {
        searchDto = new CustomerSearchDto();
        information = new Information();
        onSearch();
    }
    public void onSaveCustomer() {

        customerRepository.save(customer);
        FacesUtil.closeDialog("addCustomer");
        FacesUtil.addSuccessMessage("Thành công");
    }

    public void onSave() {
        information.setStatus(1);
        inFormationRepository.save(information);
        FacesUtil.closeDialog("Infomation");
        FacesUtil.addSuccessMessage("Thành công");
    }

    public void onSearch() {
        lazyDataModel = new LazyDataModel<Customer>() {
            @Override
            public List<Customer> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, FilterMeta> filterBy) {
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
                return customerRepository.search(searchDto);
            }
            @Override
            public Customer getRowData(String rowKey) {
                List<Customer> customerList = getWrappedData();
                Long value = Long.valueOf(rowKey);
                for (Customer customer : customerList) {
                    if (customer.getId().equals(value)) {
                        return customer;
                    }
                }
                return null;
            }
        };
        int count = customerRepository.countSearch(searchDto).intValue();
        lazyDataModel.setRowCount(count);
        FacesUtil.updateView("searchForm");
    }

    public void onEdit(Customer dto) {
        customer = new Customer();
        BeanUtils.copyProperties(dto, customer);
    }


    public String idCardToNumberCard (Long id){
        Card card = cardRepository.findCardById(id);
        return card.getNumber().toString();
    }
    public void refresh(){
        searchDto.setKeyword("");
        searchDto.setType("");
        onSearch();
    }



}

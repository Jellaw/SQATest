package vn.compedia.website.controller;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.SessionScope;
import vn.compedia.website.dto.InformationSearchDto;
import vn.compedia.website.model.Card;
import vn.compedia.website.model.Customer;
import vn.compedia.website.model.Information;
import vn.compedia.website.model.Loan;
import vn.compedia.website.repository.CardRepository;
import vn.compedia.website.repository.CustomerRepository;
import vn.compedia.website.repository.InformationRepository;
import vn.compedia.website.repository.LoanRepository;
import vn.compedia.website.util.FacesUtil;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Setter
@Getter
@Named
@SessionScope
public class InformationController implements Serializable {

    @Autowired
    private InformationRepository inFormationRepository ;

    @Autowired
    private CardRepository cardRepository ;

    @Autowired
    private CustomerRepository customerRepository ;

    @Autowired
    private LoanRepository loanRepository ;

    private Information information;
    LazyDataModel lazyDataModel;
    InformationSearchDto searchDto;
    Customer customer;
    Loan loan;
    Card card;
    Long em_id;

    public void init() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            reserAll();
        }
    }

    public void reserAll() {
        searchDto = new InformationSearchDto();
        onSearch();
    }

    public void onSearch() {
        lazyDataModel = new LazyDataModel<Information>() {
            @Override
            public List<Information> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, FilterMeta> filterBy) {
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
                return inFormationRepository.search(searchDto);
            }
            @Override
            public Information getRowData(String rowKey) {
                List<Information> informationList = getWrappedData();
                Long value = Long.valueOf(rowKey);
                for (Information information : informationList) {
                    if (information.getId().equals(value)) {
                        return information;
                    }
                }
                return null;
            }
        };
        int count = inFormationRepository.countSearch(searchDto).intValue();
        lazyDataModel.setRowCount(count);
        FacesUtil.updateView("searchForm");
        }

    public void onDelete(Information information){
       inFormationRepository.deleteById(information.getId());
        FacesUtil.addSuccessMessage("Xóa thành công");
        FacesUtil.updateView("growl");
        onSearch();
    }
    public void refresh(){
        searchDto.setKeyword("");
        searchDto.setType("");
        onSearch();
    }

    public void onSaveCusandLoan(Information information){

        card = new Card();

        String str = "10000";
        String str2 = RandomStringUtils.randomNumeric(6);
        String idBankCard = (str+str2).trim();
        // tao card
        card.setNumber(idBankCard);
        card.setType(information.getType());
        card.setAmount(information.getAmount());
        cardRepository.save(card);
        OnSaveCus(information);
        OnSaveLoan(information);

        onEdit(information);
        FacesUtil.addSuccessMessage("Thành công");
        FacesUtil.updateView("growl");
    }

    public void OnSaveCus(Information information){
        // tao danh sach khach hang
        customer = new Customer();
        customer.setName(information.getName());
        customer.setAddress(information.getAddress());
        customer.setPhone(information.getPhone());
        customer.setIdCard(information.getIdCard());
        customer.setIdBankCard(card.getId());
        customer.setDob(information.getDob());
        customer.setEmail(information.getEmail());
        customerRepository.save(customer);
    }
    public void OnSaveLoan(Information information){
        //tao danh sach khoan vay
        loan = new Loan();
        loan.setCusId(customer.getId());
        loan.setAmount(information.getAmount());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(date);
        loan.setStartDate(strDate);
        loan.setMonth(information.getNumberOfMonth());
        loan.setBankRate(information.getBankRate());
        loan.setAmountRemain(information.getAmount());

        double money = (information.getAmount() * information.getBankRate()/100)/(1-(1/Math.pow((1+information.getBankRate()),12)));
        loan.setMonthlyProfit(money);
        loanRepository.save(loan);
    }





    public void onEdit(Information dto) {
        information= new Information();
        dto.setStatus(0);
        BeanUtils.copyProperties(dto, information);
        inFormationRepository.save(information);
    }

}

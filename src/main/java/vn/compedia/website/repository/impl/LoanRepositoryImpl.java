package vn.compedia.website.repository.impl;

import org.apache.commons.lang3.StringUtils;
import vn.compedia.website.dto.CustomerSearchDto;
import vn.compedia.website.dto.LoanSearchDto;
import vn.compedia.website.model.Customer;
import vn.compedia.website.model.Loan;
import vn.compedia.website.repository.LoanRepositoryCustom;
import vn.compedia.website.util.ValueUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class LoanRepositoryImpl  implements LoanRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Loan> search(LoanSearchDto searchDto) {
        StringBuilder sb = new StringBuilder();
        sb.append(" select l.id, " +
                "    l.cus_id, " +
                "    l.amount, " +
                "    l.start_date, " +
                "    l.bank_rate, " +
                "    l.monthly_profit, " +
                "    l.amount_remain, " +
                "    l.month ");

        appendQuery(sb, searchDto);
        if (searchDto.getSortField() != null) {
            sb.append(" order by ");
            if (searchDto.getSortField().equals("amount")) {
                sb.append(" l.amount ");
            }
            if (searchDto.getSortField().equals("startDate")) {
                sb.append(" l.start_date ");
            }
            if (searchDto.getSortField().equals("month")) {
                sb.append(" l.month ");
            }
            if (searchDto.getSortField().equals("bankRate")) {
                sb.append(" l.bank_rate ");
            }
            if (searchDto.getSortField().equals("monthlyProfit")) {
                sb.append(" l.monthly_profit ");
            }
            if (searchDto.getSortField().equals("amountRemain")) {
                sb.append(" l.amount_remain ");
            }
            sb.append(searchDto.getSortOrder());
        }else {
            sb.append(" order by l.id DESC ");
        }
        Query query = createQuery(sb, searchDto);
        if (searchDto.getPageSize() > 0) {
            query.setFirstResult(searchDto.getPageIndex());
            query.setMaxResults(searchDto.getPageSize());
        } else {
            query.setFirstResult(0);
            query.setMaxResults(Integer.MAX_VALUE);
        }

        List<Loan> loanList = new ArrayList<>();
        List<Object[]> result = query.getResultList();
        for (Object[] obj : result) {
            Loan loan = new Loan();
            loan.setId(ValueUtil.getLongByObject(obj[0]));
            loan.setCusId(ValueUtil.getLongByObject(obj[1]));
            loan.setAmount(ValueUtil.getDoubleByObject(obj[2]));
            loan.setStartDate(ValueUtil.getStringByObject(obj[3]));
            loan.setBankRate(ValueUtil.getFloatByObject(obj[4]));
            loan.setMonthlyProfit(ValueUtil.getDoubleByObject(obj[5]));
            loan.setAmountRemain(ValueUtil.getDoubleByObject(obj[6]));
            loan.setMonth(ValueUtil.getIntegerByObject(obj[7]));

            loanList.add(loan);
        }


        return loanList;
    }

    @Override
    public Long countSearch(LoanSearchDto searchDto) {
        StringBuilder sb = new StringBuilder();
        sb.append(" select count(l.id) ");
        appendQuery(sb, searchDto);
        Query query = createQuery(sb, searchDto);
        return Long.valueOf(query.getSingleResult().toString());
    }



    public void appendQuery(StringBuilder sb, LoanSearchDto searchDto) {
        sb.append(" from loan l where 1 = 1 ");

        if (StringUtils.isNotBlank(searchDto.getKeyword())) {
            sb.append(" and (l.start_date like :keywords " );
        }
    }

    public Query createQuery(StringBuilder sb, LoanSearchDto searchDto) {
        Query query = entityManager.createNativeQuery(sb.toString());
        if (StringUtils.isNotBlank(searchDto.getKeyword())) {
            query.setParameter("keywords", buildLike(searchDto.getKeyword()));
        }

        return query;
    }

    public String buildLike(String param) {
        return "%" + param + "%";
    }
}


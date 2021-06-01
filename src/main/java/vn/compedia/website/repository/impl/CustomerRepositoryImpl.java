package vn.compedia.website.repository.impl;

import org.apache.commons.lang3.StringUtils;
import vn.compedia.website.dto.CustomerSearchDto;
import vn.compedia.website.dto.InformationSearchDto;
import vn.compedia.website.model.Customer;
import vn.compedia.website.model.Information;
import vn.compedia.website.repository.CustomerRepositoryCustom;
import vn.compedia.website.repository.InformationRepositoryCustom;
import vn.compedia.website.util.ValueUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Customer> search(CustomerSearchDto searchDto) {
        StringBuilder sb = new StringBuilder();
        sb.append("select c.id," +
                "       c.name," +
                "       c.dob," +
                "       c.phone," +
                "       c.email," +
                "       c.address," +
                "       c.idCard," +
                "       c.idBank_card ");

        appendQuery(sb, searchDto);
        if (searchDto.getSortField() != null) {
            sb.append(" order by ");
            if (searchDto.getSortField().equals("name")) {
                sb.append(" c.name ");
            }
            if (searchDto.getSortField().equals("dob")) {
                sb.append(" c.dob ");
            }
            if (searchDto.getSortField().equals("phone")) {
                sb.append(" c.phone ");
            }
            if (searchDto.getSortField().equals("email")) {
                sb.append(" c.email ");
            }
            if (searchDto.getSortField().equals("address")) {
                sb.append(" c.address ");
            }
            if (searchDto.getSortField().equals("idCard")) {
                sb.append(" c.idCard ");
            }
            if (searchDto.getSortField().equals("idBank_card")) {
                sb.append(" c.idBank_Card ");
            }
            if (searchDto.getSortField().equals("type")) {
                sb.append(" c.type ");
            }
            sb.append(searchDto.getSortOrder());
        }else {
            sb.append(" order by c.id DESC ");
        }
        Query query = createQuery(sb, searchDto);
        if (searchDto.getPageSize() > 0) {
            query.setFirstResult(searchDto.getPageIndex());
            query.setMaxResults(searchDto.getPageSize());
        } else {
            query.setFirstResult(0);
            query.setMaxResults(Integer.MAX_VALUE);
        }

        List<Customer> customerList = new ArrayList<>();
        List<Object[]> result = query.getResultList();
        for (Object[] obj : result) {
            Customer customer = new Customer();
            customer.setId(ValueUtil.getLongByObject(obj[0]));
            customer.setName(ValueUtil.getStringByObject(obj[1]));
            customer.setDob(ValueUtil.getDateByObject(obj[2]));
            customer.setPhone(ValueUtil.getStringByObject(obj[3]));
            customer.setEmail(ValueUtil.getStringByObject(obj[4]));
            customer.setAddress(ValueUtil.getStringByObject(obj[5]));
            customer.setIdCard(ValueUtil.getStringByObject(obj[6]));
            customer.setIdBankCard(ValueUtil.getLongByObject(obj[7]));

            customerList.add(customer);
        }


        return customerList;
    }

    @Override
    public Long countSearch(CustomerSearchDto searchDto) {
        StringBuilder sb = new StringBuilder();
        sb.append(" select count(c.id) ");
        appendQuery(sb, searchDto);
        Query query = createQuery(sb, searchDto);
        return Long.valueOf(query.getSingleResult().toString());
    }

    public void appendQuery(StringBuilder sb, CustomerSearchDto searchDto) {
        sb.append(" from customer c where 1 = 1 ");

        if(StringUtils.isNotBlank(searchDto.getType())){
            sb.append(" and c.type = :type ");
        }
        if (StringUtils.isNotBlank(searchDto.getKeyword())) {
            sb.append(" and (c.name like :keywords " +
                    "            or c.phone like :keywords " +
                    "            or c.email like :keywords) " );
        }
    }

    public Query createQuery(StringBuilder sb, CustomerSearchDto searchDto) {
        Query query = entityManager.createNativeQuery(sb.toString());
        if(StringUtils.isNotBlank(searchDto.getType())) {
            query.setParameter("type", buildLike(searchDto.getType()));
        }
        if (StringUtils.isNotBlank(searchDto.getKeyword())) {
            query.setParameter("keywords", buildLike(searchDto.getKeyword()));
        }

        return query;
    }

    public String buildLike(String param) {
        return "%" + param + "%";
    }
}

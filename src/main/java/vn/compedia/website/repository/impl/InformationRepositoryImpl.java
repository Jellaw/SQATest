package vn.compedia.website.repository.impl;

import org.apache.commons.lang3.StringUtils;
import vn.compedia.website.dto.InformationSearchDto;
import vn.compedia.website.model.Information;
import vn.compedia.website.repository.InformationRepositoryCustom;
import vn.compedia.website.util.ValueUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class InformationRepositoryImpl implements InformationRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Information> search(InformationSearchDto searchDto) {
        StringBuilder sb = new StringBuilder();
        sb.append("select i.id," +
                "       i.name," +
                "       i.dob," +
                "       i.phone," +
                "       i.email," +
                "       i.address," +
                "       i.id_card," +
                "       i.job," +
                "       i.income," +
                "       i.company_name," +
                "       i.amount," +
                "       i.bank_rate," +
                "       i.purpose," +
                "       i.type," +
                "       i.numberOfMonth");
        appendQuery(sb, searchDto);
        if (searchDto.getSortField() != null) {
            sb.append(" order by ");
            if (searchDto.getSortField().equals("name")) {
                sb.append(" i.name ");
            }
            if (searchDto.getSortField().equals("dob")) {
                sb.append(" i.dob ");
            }
            if (searchDto.getSortField().equals("phone")) {
                sb.append(" i.phone ");
            }
            if (searchDto.getSortField().equals("email")) {
                sb.append(" i.email ");
            }
            if (searchDto.getSortField().equals("address")) {
                sb.append(" i.address ");
            }
            if (searchDto.getSortField().equals("idCard")) {
                sb.append(" i.id_card ");
            }
            if (searchDto.getSortField().equals("job")) {
                sb.append(" i.job ");
            }
            if (searchDto.getSortField().equals("income")) {
                sb.append(" i.income ");
            }
            if (searchDto.getSortField().equals("companyName")) {
                sb.append(" i.company_name ");
            }
            if (searchDto.getSortField().equals("amount")) {
                sb.append(" i.amount ");
            }
            if (searchDto.getSortField().equals("bankRate")) {
                sb.append(" i.bank_rate ");
            }
            if (searchDto.getSortField().equals("purpose")) {
                sb.append(" i.purpose ");
            }
            if (searchDto.getSortField().equals("type")) {
                sb.append(" i.type ");
            }
            if (searchDto.getSortField().equals("numberOfMonth")) {
                sb.append(" i.numberOfMonth ");
            }
            sb.append(searchDto.getSortOrder());
        }else {
            sb.append(" order by i.id DESC ");
        }
        Query query = createQuery(sb, searchDto);
        if (searchDto.getPageSize() > 0) {
            query.setFirstResult(searchDto.getPageIndex());
            query.setMaxResults(searchDto.getPageSize());
        } else {
            query.setFirstResult(0);
            query.setMaxResults(Integer.MAX_VALUE);
        }

        List<Information> informationList = new ArrayList<>();
        List<Object[]> result = query.getResultList();
        for (Object[] obj : result) {
            Information information = new Information();
            information.setId(ValueUtil.getLongByObject(obj[0]));
            information.setName(ValueUtil.getStringByObject(obj[1]));
            information.setDob(ValueUtil.getDateByObject(obj[2]));
            information.setPhone(ValueUtil.getStringByObject(obj[3]));
            information.setEmail(ValueUtil.getStringByObject(obj[4]));
            information.setAddress(ValueUtil.getStringByObject(obj[5]));
            information.setIdCard(ValueUtil.getStringByObject(obj[6]));
            information.setJob(ValueUtil.getStringByObject(obj[7]));
            information.setIncome(ValueUtil.getDoubleByObject(obj[8]));
            information.setCompanyName(ValueUtil.getStringByObject(obj[9]));
            information.setAmount(ValueUtil.getDoubleByObject(obj[10]));
            information.setBankRate(ValueUtil.getFloatByObject(obj[11]));
            information.setPurpose(ValueUtil.getStringByObject(obj[12]));
            information.setType(ValueUtil.getStringByObject(obj[13]));
            information.setNumberOfMonth(ValueUtil.getIntegerByObject(obj[14]));
            informationList.add(information);
        }

        return informationList;
    }

    @Override
    public Long countSearch(InformationSearchDto searchDto) {
        StringBuilder sb = new StringBuilder();
        sb.append(" select count(i.id) ");
        appendQuery(sb, searchDto);
        Query query = createQuery(sb, searchDto);
        return Long.valueOf(query.getSingleResult().toString());
    }

    public void appendQuery(StringBuilder sb, InformationSearchDto searchDto) {
        sb.append(" from information i where 1 = 1 and i.status = 1");

        if(StringUtils.isNotBlank(searchDto.getType())){
            sb.append(" and i.type = :type ");
        }
        if (StringUtils.isNotBlank(searchDto.getKeyword())) {
            sb.append(" and (i.name like :keywords " +
                    "            or i.phone like :keywords " +
                    "            or i.email like :keywords) " );
        }
    }

    public Query createQuery(StringBuilder sb, InformationSearchDto searchDto) {
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

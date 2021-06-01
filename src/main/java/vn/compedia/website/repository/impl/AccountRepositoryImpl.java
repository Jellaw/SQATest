package vn.compedia.website.repository.impl;

import org.springframework.stereotype.Repository;
import vn.compedia.website.repository.AccountRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class AccountRepositoryImpl implements AccountRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean checkExistEmail(String email) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT count(accountId) ");
        sb.append("FROM Account ");
        sb.append("WHERE email=:email ");

        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("email", email);

        return (long) query.getSingleResult() > 0;
    }
}

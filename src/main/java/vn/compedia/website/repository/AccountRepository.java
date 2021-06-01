package vn.compedia.website.repository;

import org.springframework.data.repository.CrudRepository;
import vn.compedia.website.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long>, AccountRepositoryCustom {
    @Override
    <S extends Account> S save(S entity);

    @Override
    Iterable<Account> findAll();

}

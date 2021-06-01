package vn.compedia.website.repository;

import org.springframework.data.repository.CrudRepository;
import vn.compedia.website.model.Card;

public interface CardRepository extends CrudRepository<Card , Long> {
    Card findCardById(Long id);
}

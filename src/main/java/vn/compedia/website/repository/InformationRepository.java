package vn.compedia.website.repository;

import org.springframework.data.repository.CrudRepository;
import vn.compedia.website.model.Information;

public interface InformationRepository extends CrudRepository<Information , Long> , InformationRepositoryCustom{

}

package vn.compedia.website.repository;

import vn.compedia.website.dto.InformationSearchDto;
import vn.compedia.website.model.Information;

import java.util.List;

public interface InformationRepositoryCustom {
    List<Information>  search(InformationSearchDto searchDto);
    Long countSearch(InformationSearchDto searchDto);
}

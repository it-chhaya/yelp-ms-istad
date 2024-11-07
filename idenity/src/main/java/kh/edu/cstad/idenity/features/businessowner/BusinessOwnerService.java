package kh.edu.cstad.idenity.features.businessowner;

import kh.edu.cstad.idenity.features.businessowner.dto.BusinessOwnerResponse;
import org.springframework.data.domain.Page;

public interface BusinessOwnerService {

    Page<BusinessOwnerResponse> findList(int pageNumber, int pageSize);

    BusinessOwnerResponse findByUsername(String username);

    BusinessOwnerResponse findByUsernameTrashed(String username);

    String disable(String username);

    String enable(String username);

}

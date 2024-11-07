package kh.edu.cstad.idenity.features.businessowner;

import kh.edu.cstad.idenity.domain.BusinessOwner;
import kh.edu.cstad.idenity.features.businessowner.dto.BusinessOwnerResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BusinessOwnerMapper {

    BusinessOwnerResponse toBusinessOwnerResponse(BusinessOwner businessOwner);

}

package kh.edu.cstad.idenity.features.subscriber;

import kh.edu.cstad.idenity.domain.Subscriber;
import kh.edu.cstad.idenity.features.subscriber.dto.SubscriberResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriberMapper {

    SubscriberResponse toSubscriberResponse(Subscriber subscriber);

}

package kh.edu.cstad.idenity.features.subscriber;

import kh.edu.cstad.idenity.features.subscriber.dto.SubscriberResponse;
import org.springframework.data.domain.Page;

public interface SubscriberService {

    Page<SubscriberResponse> findList(int pageNumber, int pageSize);

    SubscriberResponse findByUsername(String username);

    SubscriberResponse findByUsernameTrashed(String username);

    String disable(String username);

    String enable(String username);

}

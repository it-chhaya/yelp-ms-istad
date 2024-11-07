package kh.edu.cstad.idenity.features.subscriber;

import kh.edu.cstad.idenity.domain.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    Optional<Subscriber> findByUserIdAndUserIsEnabledTrue(Long userId);

    Optional<Subscriber> findByUserIdAndUserIsEnabledFalse(Long userId);

}

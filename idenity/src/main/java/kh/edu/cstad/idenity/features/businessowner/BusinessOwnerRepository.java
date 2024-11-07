package kh.edu.cstad.idenity.features.businessowner;

import kh.edu.cstad.idenity.domain.BusinessOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessOwnerRepository extends JpaRepository<BusinessOwner, Long> {

    Optional<BusinessOwner> findByUserIdAndUserIsEnabledTrue(Long userId);

    Optional<BusinessOwner> findByUserIdAndUserIsEnabledFalse(Long userId);

}

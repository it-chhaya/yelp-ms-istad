package kh.edu.cstad.idenity.features.authority;

import kh.edu.cstad.idenity.domain.Authority;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Integer> {

    @Query("select e from #{#entityName} e where e.name='USER'")
    Authority AUTH_USER();

    @Query("select e from #{#entityName} e where e.name='SUBSCRIBER'")
    Authority AUTH_SUBSCRIBER();

    @Query("select e from #{#entityName} e where e.name='ADMIN'")
    Authority AUTH_ADMIN();

    @Query("select e from #{#entityName} e where e.name='BUSINESS_OWNER'")
    Authority AUTH_BUSINESS_OWNER();

    Optional<Authority> findByName(String name);

}

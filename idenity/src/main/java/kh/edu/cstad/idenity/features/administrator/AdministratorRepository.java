package kh.edu.cstad.idenity.features.administrator;

import kh.edu.cstad.idenity.domain.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

}

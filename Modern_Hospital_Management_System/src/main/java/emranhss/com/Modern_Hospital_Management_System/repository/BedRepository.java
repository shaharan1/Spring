package emranhss.com.Modern_Hospital_Management_System.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BedRepository extends JpaRepository<BedRepository, Long> {
}

package nashtech.phucldh.ecommerce.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Orderstatus;

@Repository
public interface OrderstatusRepository extends JpaRepository<Orderstatus, String> {

}

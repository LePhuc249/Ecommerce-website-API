package nashtech.phucldh.ecommerce.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Order_Status;

@Repository
public interface Order_StatusRepository extends JpaRepository<Order_Status, Integer> {

}

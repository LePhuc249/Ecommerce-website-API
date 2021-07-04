package nashtech.phucldh.ecommerce.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, String> {

}

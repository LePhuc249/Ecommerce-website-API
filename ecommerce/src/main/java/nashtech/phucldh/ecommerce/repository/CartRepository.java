package nashtech.phucldh.ecommerce.repository;

import nashtech.phucldh.ecommerce.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Cart;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByAccount(Account customer);

}
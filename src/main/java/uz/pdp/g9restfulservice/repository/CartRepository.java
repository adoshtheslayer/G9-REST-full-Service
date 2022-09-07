package uz.pdp.g9restfulservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.g9restfulservice.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}

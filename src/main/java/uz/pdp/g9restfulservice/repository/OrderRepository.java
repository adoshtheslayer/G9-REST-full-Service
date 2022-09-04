package uz.pdp.g9restfulservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.g9restfulservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long > {
}

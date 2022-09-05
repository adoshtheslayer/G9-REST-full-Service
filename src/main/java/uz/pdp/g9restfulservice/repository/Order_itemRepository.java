package uz.pdp.g9restfulservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.g9restfulservice.entity.Order_item;

public interface Order_itemRepository extends JpaRepository<Order_item, Long> {
}

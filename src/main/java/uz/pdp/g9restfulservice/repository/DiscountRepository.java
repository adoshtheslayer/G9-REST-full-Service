package uz.pdp.g9restfulservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.g9restfulservice.entity.Discount;

public interface DiscountRepository extends JpaRepository<Discount,Long> {
}

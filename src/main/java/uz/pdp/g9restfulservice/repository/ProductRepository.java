package uz.pdp.g9restfulservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.g9restfulservice.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}

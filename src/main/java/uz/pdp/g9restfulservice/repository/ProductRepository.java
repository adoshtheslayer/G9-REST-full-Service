package uz.pdp.g9restfulservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.g9restfulservice.entity.Product;
import uz.pdp.g9restfulservice.projection.ProductListProjection;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

     Page<ProductListProjection> findProductByNameContains(Pageable page, String productName);
}

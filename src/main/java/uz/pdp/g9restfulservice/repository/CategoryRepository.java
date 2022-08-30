package uz.pdp.g9restfulservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.g9restfulservice.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}

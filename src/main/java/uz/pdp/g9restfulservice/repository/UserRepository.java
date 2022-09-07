package uz.pdp.g9restfulservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.g9restfulservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

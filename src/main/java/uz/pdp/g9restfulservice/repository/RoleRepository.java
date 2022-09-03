package uz.pdp.g9restfulservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.g9restfulservice.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
}

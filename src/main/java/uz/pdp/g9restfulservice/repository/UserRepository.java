package uz.pdp.g9restfulservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.g9restfulservice.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);
    boolean existsByUsernameAndIdNot(String username, Long id);

    Optional<User>findByUsername(String username);

}

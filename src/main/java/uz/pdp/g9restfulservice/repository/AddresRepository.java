package uz.pdp.g9restfulservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.g9restfulservice.entity.Address;

public interface AddresRepository extends JpaRepository<Address,Long> {

}

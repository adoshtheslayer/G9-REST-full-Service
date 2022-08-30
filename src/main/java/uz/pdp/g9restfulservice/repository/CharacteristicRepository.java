package uz.pdp.g9restfulservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.g9restfulservice.entity.Characteristic;

public interface CharacteristicRepository extends JpaRepository<Characteristic,Long> {
}

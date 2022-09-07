package uz.pdp.g9restfulservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.g9restfulservice.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}

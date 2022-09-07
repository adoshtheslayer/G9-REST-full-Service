package uz.pdp.g9restfulservice.service;

import org.springframework.stereotype.Service;
import uz.pdp.g9restfulservice.entity.Payment;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.repository.PaymentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        return optionalPayment.orElse(null);
    }


    public ApiResponse deletePayment(Long id) {
        try {
            paymentRepository.deleteById(id);
            return new ApiResponse("Payment delete", true);
        } catch (Exception e) {
            return new ApiResponse("Payment like this id is not exist", false);
        }
    }


    public ApiResponse addPayment(Payment paymentDto) {

        Payment payment = Payment.builder()
                .amount(paymentDto.getAmount())
                .paymentType(paymentDto.getPaymentType())
                .payDate(paymentDto.getPayDate())
//                .order(paymentDto.getOrder())
                .build();

        Payment save = paymentRepository.save(payment);

        if (save != null) {
            return new ApiResponse("Payment saved", true);
        }
        return new ApiResponse("Not saved Payment", false);
    }


    public ApiResponse updatePayment(Long id, Payment paymentDto) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);

        if (optionalPayment.isEmpty()) {
            return new ApiResponse("Payment like this id is not found", false);
        }

        Payment payment = optionalPayment.get();
        if (paymentDto.getAmount() != 0) {
            payment.setAmount(paymentDto.getAmount());
        }
        if (paymentDto.getPaymentType() != null) {
            payment.setPaymentType(paymentDto.getPaymentType());
        }
        if (paymentDto.getPayDate() != null) {
            payment.setPayDate(paymentDto.getPayDate());
        }

//        payment.setOrder(paymentDto.getOrder());

        paymentRepository.save(payment);

        return new ApiResponse("Payment update", true);
    }
}

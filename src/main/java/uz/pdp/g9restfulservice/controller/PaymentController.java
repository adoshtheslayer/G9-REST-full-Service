package uz.pdp.g9restfulservice.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.g9restfulservice.entity.Payment;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.service.PaymentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @GetMapping()
    public HttpEntity<?> getPayments(){
        return ResponseEntity.status(paymentService.getAllPayments() != null ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(paymentService.getAllPayments());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        return ResponseEntity.status(
                payment != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(payment);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deletePayment(@PathVariable Long id) {
        ApiResponse apiResponse = paymentService.deletePayment(id);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> savePayment(@Valid @RequestBody Payment payment) {
        ApiResponse apiResponse = paymentService.addPayment(payment);

        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updatePayment(@PathVariable Long id, @Valid @RequestBody Payment payment) {
        ApiResponse apiResponse =paymentService.updatePayment(id, payment);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);

    }
}

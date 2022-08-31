package uz.pdp.g9restfulservice.controller;

import org.apache.catalina.mbeans.SparseUserDatabaseMBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.pdp.g9restfulservice.dto.CategoryDto;
import uz.pdp.g9restfulservice.entity.Category;
import uz.pdp.g9restfulservice.entity.Discount;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.service.DiscountService;

import javax.validation.Valid;

@RestController
@RequestMapping("/discount")
public class DiscountController {
    final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping
    public HttpEntity<?> getDiscounts() {
        return ResponseEntity.status(discountService.getAll() != null ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(discountService.getAll());
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getDiscountById(@PathVariable Long id) {
        Discount discountById = discountService.getFindById(id);
        return ResponseEntity.status(
                discountById != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(discountById);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteDiscount(@PathVariable Long id) {
        ApiResponse apiResponse = discountService.getDelete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }
    @PostMapping
    public HttpEntity<?> saveDiscount(@RequestBody Discount discount) {
        ApiResponse apiResponse = discountService.save(discount);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> updateCategory(@PathVariable Long id,@RequestBody Discount discount) {
        ApiResponse apiResponse = discountService.update(id, discount);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
}

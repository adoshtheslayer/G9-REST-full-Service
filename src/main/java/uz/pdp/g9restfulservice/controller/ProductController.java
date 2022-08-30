package uz.pdp.g9restfulservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.g9restfulservice.dto.ProductDto;
import uz.pdp.g9restfulservice.entity.Product;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.service.ProductService;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    private HttpEntity<?> getAllProductWithPage(@RequestParam int page) {

        return ResponseEntity.ok(productService.findPageProduct(page));
    }

    @PostMapping
    public HttpEntity<?> addProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.save(productDto));
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.editingProduct(id,productDto));

    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteById(id));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getProduct(@PathVariable Long id) {

        return ResponseEntity.ok(productService.getProduct(id));

    }

}

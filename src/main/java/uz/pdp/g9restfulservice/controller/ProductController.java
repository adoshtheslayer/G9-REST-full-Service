package uz.pdp.g9restfulservice.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.g9restfulservice.dto.ProductDto;
import uz.pdp.g9restfulservice.entity.Product;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.service.ProductService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    private HttpEntity<?> getAllProductWithPage(@RequestParam int page) {

        return ResponseEntity.status(productService.findPageProduct(page) != null ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(productService.findPageProduct(page));
    }

    @PostMapping
    public HttpEntity<?> addProduct(@RequestBody ProductDto productDto) {
        ApiResponse apiResponse = productService.save(productDto);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editProduct(@Valid @PathVariable Long id, @RequestBody ProductDto productDto) {
        ApiResponse apiResponse = productService.editingProduct(id,productDto);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);

    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteProduct(@PathVariable Long id) {
        ApiResponse apiResponse = productService.deleteById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getProduct(@PathVariable Long id) {
        Product productById = productService.getProduct(id);
        return ResponseEntity.status(
                productById != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(productById);
    }
}

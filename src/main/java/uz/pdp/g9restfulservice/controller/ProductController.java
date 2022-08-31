package uz.pdp.g9restfulservice.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.g9restfulservice.dto.ProductDto;
import uz.pdp.g9restfulservice.entity.Attachment;
import uz.pdp.g9restfulservice.entity.Product;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.service.ProductService;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    private HttpEntity<?> getAllProductWithPage(@RequestParam(defaultValue = "1") int page) {

        return ResponseEntity.status(productService.findPageProduct(page).isEmpty() ?
                HttpStatus.CONFLICT : HttpStatus.OK).body(productService.findPageProduct(page));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getProduct(@PathVariable Long id) {
        Product productById = productService.getProductById(id);
        return ResponseEntity.status(
                productById != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(productById);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public HttpEntity<?> addProduct(@Valid @RequestPart ProductDto productDto, @RequestPart("attachment") MultipartFile attachment) throws IOException {

        ApiResponse apiResponse = productService.save(productDto, attachment);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        ApiResponse apiResponse = productService.editingProduct(id, productDto);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteProduct(@PathVariable Long id) {


        ApiResponse apiResponse = productService.deleteById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }
}

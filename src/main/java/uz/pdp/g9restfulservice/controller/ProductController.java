package uz.pdp.g9restfulservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.g9restfulservice.dto.ProductDto;
import uz.pdp.g9restfulservice.entity.Attachment;
import uz.pdp.g9restfulservice.entity.Product;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.projection.ProductListProjection;
import uz.pdp.g9restfulservice.service.ProductService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    private HttpEntity<?> getAllProductWithPage(@RequestParam(defaultValue = "0") int page) {

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

    @PutMapping(value = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public HttpEntity<?> editProduct(@PathVariable Long id, @Valid @RequestPart ProductDto productDto, @RequestPart("attachment") MultipartFile attachment) throws IOException {
        ApiResponse apiResponse = productService.editingProduct(id, productDto,attachment);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteProduct(@PathVariable Long id) {

        ApiResponse apiResponse = productService.deleteById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }



    @GetMapping("/search/{productName}")
    public HttpEntity<?> searchUser(@PathVariable("productName") String productName ,
                             @RequestParam(defaultValue = "0") Integer page,
                             @RequestParam(defaultValue = "5") Integer size )
    {
        List<ProductListProjection> searchUser  =   productService.findByProductname(productName ,page,size);
        return ResponseEntity.status(searchUser.isEmpty()?404:200).body(searchUser);
    }






}

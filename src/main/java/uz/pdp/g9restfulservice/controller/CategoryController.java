package uz.pdp.g9restfulservice.controller;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.g9restfulservice.dto.CategoryDto;
import uz.pdp.g9restfulservice.entity.Category;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.service.CategoryService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class  CategoryController {

    final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public HttpEntity<?> getCategories() {
        return ResponseEntity.status(categoryService.getAllCategories() != null ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getCategoryById(@PathVariable Long id) {
        Category categoryById = categoryService.getCategoryById(id);
        return ResponseEntity.status(
                categoryById != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(categoryById);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCategory(@PathVariable Long id) {
        ApiResponse apiResponse = categoryService.deleteCategory(id);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> saveCategory(@Valid @RequestBody CategoryDto categoryDto) {
        ApiResponse apiResponse = categoryService.addCategory(categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateCategory(@PathVariable Long id,@Valid @RequestBody CategoryDto categoryDto) {
        ApiResponse apiResponse = categoryService.updateCategory(id, categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
}

package uz.pdp.g9restfulservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.g9restfulservice.dto.CategoryDto;
import uz.pdp.g9restfulservice.service.CategoryService;

@RestController
@RequestMapping("/api/categorys")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    /**
     * List of all categories
     *
     * @return Categorys
     */
    @GetMapping
    public HttpEntity getCategorys() {
        return ResponseEntity.ok(categoryService.getCategorys());
    }


    /**
     * Id bo'yicha faqat bitta Category ni qaytaradi
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public HttpEntity getCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }


    /**
     * ID bo'yicha Category ni delete qiladi
     *
     * @param id
     * @return ApiResponse(message, success)
     */
    @DeleteMapping("/{id}")
    public HttpEntity deleteCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }


    /**
     * Save Category
     *
     * @param categoryDto
     * @return ApiResponse
     */
    @PostMapping
    public HttpEntity saveCategory(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.addCategory(categoryDto));
    }


    /**
     * Category update
     * @param id
     * @param categoryDto
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public HttpEntity updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDto));
    }

}

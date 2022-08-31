package uz.pdp.g9restfulservice.service;

import org.springframework.stereotype.Service;
import uz.pdp.g9restfulservice.dto.CategoryDto;
import uz.pdp.g9restfulservice.entity.Category;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    public ApiResponse deleteCategory(Long id) {
        try {
            categoryRepository.deleteById(id);
            return new ApiResponse("Category deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Category like this id is not exist", false);
        }
    }


    public ApiResponse addCategory(CategoryDto categoryDto) {

        for (Category category : categoryRepository.findAll()) {
            if (category.getName().equals(categoryDto.getName())) {
                return new ApiResponse("Category like this name already exist", false);
            }
        }

        Category category = new Category();
        if (categoryDto.getParentId() != null) {
            Optional<Category> optional = categoryRepository.findById(categoryDto.getParentId());

            //DB yo'q Category ID kelsa ParentId ga
            if (optional.isEmpty()) {
                return new ApiResponse("Category like this id is not found", false);
            }
            //DB bor Category ID kelsa ParentId ga
            category.setParentCategory(optional.get());
        }
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        return new ApiResponse("Category saved", true);
    }

    public ApiResponse updateCategory(Long id, CategoryDto categoryDto) {

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());

        if (categoryDto.getParentId() != null) {
            Optional<Category> optionalCategoryByPaerentId = categoryRepository.findById(categoryDto.getParentId());
            if (optionalCategoryByPaerentId.isEmpty()) {
                return new ApiResponse("Category like this parent id is not exist", false);
            }
            category.setParentCategory(optionalCategoryByPaerentId.get());
        }

        categoryRepository.save(category);
        return new ApiResponse("Category updated", true);
    }
}
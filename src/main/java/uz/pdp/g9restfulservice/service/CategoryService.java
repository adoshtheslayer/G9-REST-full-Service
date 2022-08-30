package uz.pdp.g9restfulservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.g9restfulservice.dto.CategoryDto;
import uz.pdp.g9restfulservice.entity.Category;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    /**
     * List of all categories
     *
     * @return Categorys
     */
    public ApiResponse getCategorys() {
        List<Category> categories = categoryRepository.findAll();
        return new ApiResponse("success",true,categories);
    }


    /**
     * Agar id orqali Category qaytaradi. Agar topilmasa bo'sh Category qaytaradi
     *
     * @param id
     * @return
     */
    public ApiResponse getCategoryById(Long id) {

        Optional<Category> optionalCategory = categoryRepository.findById(id);
//        Optional<Category> byId = categoryRepository.findById(optional.get().getParent_id().getId());
        if (optionalCategory.isPresent()){
            return new ApiResponse("succes",true,optionalCategory);
        }

//        return Category;
        return new ApiResponse("error",false);
    }


    /**
     * ID bo'yicha delete qiladi
     *
     * @param id
     * @return ApiResponse
     */
    public ApiResponse deleteCategory(Long id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()) {
            categoryRepository.deleteById(id);
            return new ApiResponse("Category delete", true);
        }
        return new ApiResponse("Bunday ID Category yo'q", false);
    }


    /**
     * Save Category
     *
     * @param categoryDto
     * @return ApiResponse
     */
    public ApiResponse addCategory(CategoryDto categoryDto) {

        // shunday Category name bormi shunga tekshiradi
        for (Category category : categoryRepository.findAll()) {
            if (category.getName().equals(categoryDto.getName())) {
                return new ApiResponse("Bunday Category mavjud", false);
            }
        }


        Category category = new Category();


        if (categoryDto.getParentId() != null) {
            Optional<Category> optional = categoryRepository.findById(categoryDto.getParentId());

            //DB yo'q Category ID kelsa ParentId ga
            if (optional.isEmpty()) {
                return new ApiResponse("Bunday ID Category mavjud emas", false);
            }
            //DB bor Category ID kelsa ParentId ga
            category.setParent_id(optional.get());
        }

        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        return new ApiResponse("Category save", true,category);
    }


    /**
     * Category update
     *
     * @param id
     * @param categoryDto
     * @return ApiResponse
     */
    public ApiResponse updateCategory(Long id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            return new ApiResponse("Bunday Category mavjud emas", false);
        }

        Optional<Category> categoryDtoParentIdOptional = categoryRepository.findById(categoryDto.getParentId());


        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        category.setParent_id(categoryDtoParentIdOptional.get());

        categoryRepository.save(category);

        return new ApiResponse("Success update", true);

    }
}

package uz.pdp.g9restfulservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.g9restfulservice.dto.ProductDto;
import uz.pdp.g9restfulservice.entity.*;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.repository.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CharacteristicRepository characteristicRepository;
    private final DiscountRepository discountRepository;
    private final CategoryRepository categoryRepository;
    private final AttachmentRepository attachmentRepository;

    public ProductService(ProductRepository productRepository, CharacteristicRepository characteristicRepository, DiscountRepository discountRepository, CategoryRepository categoryRepository, AttachmentRepository attachmentRepository) {
        this.productRepository = productRepository;
        this.characteristicRepository = characteristicRepository;
        this.discountRepository = discountRepository;
        this.categoryRepository = categoryRepository;
        this.attachmentRepository = attachmentRepository;
    }

    public ApiResponse findPageProduct(int page) {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Product> productPage = productRepository.findAll(pageable);

        return new ApiResponse("success", true, productPage);

    }

    public ApiResponse save(ProductDto productDto) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getAttachmentId());
        if (optionalAttachment.isEmpty()) return new ApiResponse("attachment not found", false);

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (optionalAttachment.isEmpty()) return new ApiResponse("category not found", false);

        Optional<Discount> optionalDiscount = discountRepository.findById(productDto.getDiscountId());
        if (optionalDiscount.isEmpty()) return new ApiResponse("discound not found", false);

        List<Characteristic> characteristicsById = characteristicRepository.findAllById(productDto.getCharacteristicIds());
        if (characteristicsById.isEmpty()) return new ApiResponse("characteristic not found", false);

        Product product = Product.builder()
                .attachment(optionalAttachment.get())
                .category(optionalCategory.get())
                .characteristics(characteristicsById)
                .discount(optionalDiscount.get())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .build();
        return new ApiResponse("successfully product added", true, product);
    }

    public ApiResponse editingProduct(Long id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getAttachmentId());
            if (optionalAttachment.isEmpty()) return new ApiResponse("attachment not found", false);

            Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
            if (optionalAttachment.isEmpty()) return new ApiResponse("category not found", false);

            Optional<Discount> optionalDiscount = discountRepository.findById(productDto.getDiscountId());
            if (optionalDiscount.isEmpty()) return new ApiResponse("discound not found", false);

            List<Characteristic> characteristicsById = characteristicRepository.findAllById(productDto.getCharacteristicIds());
            if (characteristicsById.isEmpty()) return new ApiResponse("characteristic not found", false);

            Product editProduct = optionalProduct.get();

            editProduct.setAttachment(optionalAttachment.get());
            editProduct.setCategory(optionalCategory.get());
            editProduct.setCharacteristics(characteristicsById);
            editProduct.setDiscount(optionalDiscount.get());
            editProduct.setPrice(productDto.getPrice());
            editProduct.setQuantity(productDto.getQuantity());
            editProduct.setName(productDto.getName());

            return new ApiResponse("successfully edited product", true, editProduct);

        }
        return new ApiResponse("error", true);
    }

    public ApiResponse deleteById(Long id) {
        productRepository.deleteById(id);
        return new ApiResponse("product deleted successfully", true);

    }

    public ApiResponse getProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return new ApiResponse("success", true, optionalProduct);
        }
        return new ApiResponse("error",false);
    }
}

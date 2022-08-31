package uz.pdp.g9restfulservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.g9restfulservice.dto.ProductDto;
import uz.pdp.g9restfulservice.entity.*;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.repository.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CharacteristicRepository characteristicRepository;
    private final DiscountRepository discountRepository;
    private final CategoryRepository categoryRepository;
    private final AttachmentContentRepository attachmentContentRepository;
    private final AttachmentService attachmentService;

    public ProductService(ProductRepository productRepository, CharacteristicRepository characteristicRepository, DiscountRepository discountRepository, CategoryRepository categoryRepository, AttachmentRepository attachmentRepository, AttachmentContentRepository attachmentContentRepository, AttachmentService attachmentService) {
        this.productRepository = productRepository;
        this.characteristicRepository = characteristicRepository;
        this.discountRepository = discountRepository;
        this.categoryRepository = categoryRepository;
        this.attachmentContentRepository = attachmentContentRepository;
        this.attachmentService = attachmentService;
    }

    public Page<Product> findPageProduct(int page) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        return productRepository.findAll(pageable);
    }

    public ApiResponse save(ProductDto productDto, MultipartFile attachment) throws IOException {

        Attachment saveAttachment = attachmentService.saveAttachment(attachment);
        if (saveAttachment == null) {
            return new ApiResponse("attachment is null", false);
        }


        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());

        if (optionalCategory.isEmpty()) {
            return new ApiResponse("category not found", false);
        }


        Optional<Discount> optionalDiscount = null;
        if (productDto.getDiscountId() != null) {
            optionalDiscount = discountRepository.findById(productDto.getDiscountId());
        }

        List<Characteristic> characteristicsById = null;
        if (productDto.getCharacteristicIds() != null) {
            characteristicsById = characteristicRepository.findAllById(productDto.getCharacteristicIds());
        }

        for (Product product : productRepository.findAll()) {
            if (product.getName().equals(productDto.getName())) {
                return new ApiResponse("Product like this name already exist", false);
            }
        }

        Product product = Product.builder()
                .attachment(saveAttachment)
                .category(optionalCategory.get())
                .characteristics(characteristicsById)
                .name(productDto.getName())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .build();
<<<<<<< HEAD
=======
        if (optionalDiscount != null) {
            product.setDiscount(optionalDiscount.get());
        }
>>>>>>> ae2f14681381c3bba1335061c741766dd82542b9
        productRepository.save(product);
        return new ApiResponse("successfully product added", true);
    }

    public ApiResponse editingProduct(Long id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
//            if (productDto.getAttachment().isEmpty())
//                return new ApiResponse("attachment not found", false);

            Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
            if (optionalCategory.isEmpty()) return new ApiResponse("category not found", false);

            Optional<Discount> optionalDiscount = discountRepository.findById(productDto.getDiscountId());
//            if (optionalDiscount.isEmpty()) return new ApiResponse("discound not found", false);

            List<Characteristic> characteristicsById = characteristicRepository.findAllById(productDto.getCharacteristicIds());
            if (characteristicsById.isEmpty()) return new ApiResponse("characteristic not found", false);

            Product editProduct = optionalProduct.get();

//            editProduct.setAttachment(optionalAttachment.get());
            editProduct.setCategory(optionalCategory.get());
            editProduct.setCharacteristics(characteristicsById);
            editProduct.setDiscount(optionalDiscount.get());
            editProduct.setPrice(productDto.getPrice());
            editProduct.setQuantity(productDto.getQuantity());
            editProduct.setName(productDto.getName());
            productRepository.save(editProduct);

            return new ApiResponse("product edited", true);

        }
        return new ApiResponse("error", true);
    }

    @Transactional
    public ApiResponse deleteById(Long id) {
        try {
            Optional<Product> optionalProduct = productRepository.findById(id);
            if (optionalProduct.isEmpty()) {
                return new ApiResponse("Product like this id is not exist", false);
            }
            Attachment productAttachment = optionalProduct.get().getAttachment();
            if (productAttachment != null) {
                boolean isExist = attachmentContentRepository.existsByAttachmentId(productAttachment.getId());
                if (isExist) {
                    attachmentContentRepository.deleteByAttachmentId(productAttachment.getId());
                }
            }
            productRepository.deleteById(id);
            return new ApiResponse("Product deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Product like this id is not exist", false);
        }
    }

    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }
}

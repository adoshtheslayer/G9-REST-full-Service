package uz.pdp.g9restfulservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.g9restfulservice.entity.Discount;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.repository.DiscountRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountService {
    final DiscountRepository discountRepository;

    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public ApiResponse save(Discount discount) {
        System.out.println(discount);
        Discount save = discountRepository.save(discount);
        return new ApiResponse("Saved", true);
    }

    public ApiResponse getAll() {
        List<Discount> list = discountRepository.findAll();

        return new ApiResponse("Succes", true);

    }

    public ApiResponse getFindById(Long id) {
        Optional<Discount> optionalDiscount = discountRepository.findById(id);
        if (optionalDiscount.isEmpty()) {
            return new ApiResponse("Discount not found", false);
        }
        return new ApiResponse("Success", true);
    }

    public ApiResponse getDelete(Long id) {
        Optional<Discount> optionalDiscount = discountRepository.findById(id);
        if (optionalDiscount.isEmpty()) {

            return new ApiResponse("Discount not found", false);
        }
        discountRepository.delete(optionalDiscount.get());
        return new ApiResponse("Deleted", true);
    }

    public ApiResponse update(Long id, Discount discount) {
        Optional<Discount> optionalDiscount = discountRepository.findById(id);
        if (optionalDiscount.isEmpty()) {
            return new ApiResponse("Discount not found", false);
        }
        Discount save = discountRepository.save(discount);
        return new ApiResponse("Updated", true);
    }

    public ApiResponse getPageable(Integer page) {
        if (page < 1) {
            throw new IllegalStateException();
        }
        Pageable pageable = PageRequest.of(page - 1, 2);
        Page<Discount> all = discountRepository.findAll(pageable);
        return new ApiResponse("Success", true);
    }
}

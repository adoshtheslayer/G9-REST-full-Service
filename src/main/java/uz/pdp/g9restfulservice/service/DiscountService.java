package uz.pdp.g9restfulservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import uz.pdp.g9restfulservice.entity.Discount;
import uz.pdp.g9restfulservice.entity.Discount;
import uz.pdp.g9restfulservice.repository.DiscountRepository;
import uz.pdp.g9restfulservice.payload.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountService {
final DiscountRepository discountRepository;

    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }


    public List<Discount> getAll(){
        List<Discount> list=discountRepository.findAll();

        return list;

    }

    public Discount getFindById(Long id){
        Optional<Discount> optionalDiscount = discountRepository.findById(id);

        return optionalDiscount.orElse(null);
    }

    public ApiResponse save(Discount discount){
        for (Discount discount1 : discountRepository.findAll()) {
            if (discount1.getName().equals(discount.getName())) {
                return new ApiResponse("Discount like this name already exist", false);
            }
        }

        discountRepository.save(discount);
        return new ApiResponse("Discount saved", true);

    }
    public ApiResponse getDelete(Long id){
        try {
            discountRepository.deleteById(id);
            return new ApiResponse("Discount deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Discount like this id is not exist", false);
        }
    }
    public ApiResponse update(Long id, Discount discount){
        Optional<Discount> optionalDiscount = discountRepository.findById(id);
        if (optionalDiscount.isEmpty()) {
            return new ApiResponse("Discount not found",false);
        }
        discountRepository.save(discount);
        return  new ApiResponse("Updated",true);

    }



}

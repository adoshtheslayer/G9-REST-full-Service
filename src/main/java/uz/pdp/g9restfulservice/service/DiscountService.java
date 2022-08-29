package uz.pdp.g9restfulservice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.g9restfulservice.entity.Discount;
import uz.pdp.g9restfulservice.repository.DiscountRepository;
import uz.pdp.g9restfulservice.responce.ApiResponce;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountService {
final DiscountRepository discountRepository;

    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public ApiResponce save(Discount discount){
        System.out.println(discount);
        Discount save = discountRepository.save(discount);
        return new ApiResponce("Saved",save,true);

    }
    public ApiResponce getAll(){
        List<Discount> list=discountRepository.findAll();

        return new ApiResponce("Succes",list,true);

    }

    public ApiResponce getFindById(Long id){
        Optional<Discount> optionalDiscount = discountRepository.findById(id);
        if(optionalDiscount.isEmpty()){
           return new ApiResponce("Discount not found", false);
        }
        return new ApiResponce("Success",optionalDiscount.get(),true);
    }
    public ApiResponce getDelete(Long id){
        Optional<Discount> optionalDiscount = discountRepository.findById(id);
        if (optionalDiscount.isEmpty()) {

            return  new ApiResponce("Discount not found",false);
        }
        discountRepository.delete(optionalDiscount.get());
        return new ApiResponce("Deleted",true);
    }
    public ApiResponce update(Long id, Discount discount){
        Optional<Discount> optionalDiscount = discountRepository.findById(id);
        if (optionalDiscount.isEmpty()) {
            return new ApiResponce("Discount not found",false);
        }
        Discount save = discountRepository.save(discount);
        return  new ApiResponce("Updated",save,true);

    }

}

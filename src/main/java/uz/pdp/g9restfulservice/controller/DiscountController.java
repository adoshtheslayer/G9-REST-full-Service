package uz.pdp.g9restfulservice.controller;

import org.apache.catalina.mbeans.SparseUserDatabaseMBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.pdp.g9restfulservice.entity.Discount;
import uz.pdp.g9restfulservice.service.DiscountService;

@RestController
@RequestMapping("/discount")
public class DiscountController {
    final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }
    @GetMapping
    public HttpEntity getPage(@RequestParam("page") Integer page){
        return  ResponseEntity.ok(discountService.getPageable(page));
    }
//     @GetMapping
//    public HttpEntity getAll(){
//        return ResponseEntity.ok(discountService.getAll());
//     }

     @GetMapping("{id}")
    public HttpEntity  getFinById(@PathVariable Long id){
        return  ResponseEntity.ok(discountService.getFindById(id));
     }

     @DeleteMapping("{id}")
    public HttpEntity  deleteDiscount (@PathVariable Long id){
        return ResponseEntity.ok(discountService.getDelete(id));

    }
    @PostMapping
    public HttpEntity addDiscount(@RequestBody Discount discount){
        return ResponseEntity.ok(discountService.save(discount));

    }
    @PutMapping("{id}")
    public HttpEntity updateDiscount(@PathVariable Long id,@RequestBody Discount discount){
        return ResponseEntity.ok(discountService.update(id,discount));

    }
}

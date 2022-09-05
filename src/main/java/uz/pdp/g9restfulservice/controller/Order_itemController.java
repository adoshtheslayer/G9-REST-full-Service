package uz.pdp.g9restfulservice.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.g9restfulservice.dto.Order_itemDto;
import uz.pdp.g9restfulservice.entity.Order_item;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.service.Order_itemService;

@RestController
@RequestMapping("/order_item")
public class Order_itemController {

  private final  Order_itemService order_itemService;

    public Order_itemController(Order_itemService order_itemService) {
        this.order_itemService = order_itemService;
    }
    @GetMapping
    public HttpEntity<?> getAllOrderItem(){
        return ResponseEntity.status(order_itemService.getAllOrderItem()!=null? HttpStatus.OK:HttpStatus.CONFLICT).body(order_itemService.getAllOrderItem());

    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOrderItemById(@PathVariable Long id) {
        Order_item order_item = order_itemService.getFindById(id);
        return ResponseEntity.status(order_item != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(order_item);
    }

    @PostMapping
    public HttpEntity<?> addOrderItem(@RequestBody Order_itemDto order_itemDto){
        ApiResponse apiResponse=order_itemService.addOrderItem(order_itemDto);
        return ResponseEntity.status(apiResponse!=null ?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateOrderItem(@PathVariable Long id,Order_itemDto order_itemDto){
        ApiResponse apiResponse=order_itemService.updateOrderItem(id,order_itemDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOrderItem(@PathVariable Long id){
        ApiResponse apiResponse=order_itemService.deleteOrderItem(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

}

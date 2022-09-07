package uz.pdp.g9restfulservice.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.g9restfulservice.dto.Order_itemDto;
import uz.pdp.g9restfulservice.entity.OrderItem;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.service.OrderItemService;

@RestController
@RequestMapping("/order_item")
public class Order_itemController {

  private final OrderItemService orderItemService;

    public Order_itemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }
    @GetMapping
    public HttpEntity<?> getAllOrderItem(){
        return ResponseEntity.status(orderItemService.getAllOrderItem()!=null? HttpStatus.OK:HttpStatus.CONFLICT).body(orderItemService.getAllOrderItem());

    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOrderItemById(@PathVariable Long id) {
        OrderItem order_item = orderItemService.getFindById(id);
        return ResponseEntity.status(order_item != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(order_item);
    }

    @PostMapping
    public HttpEntity<?> addOrderItem(@RequestBody Order_itemDto order_itemDto){
        ApiResponse apiResponse= orderItemService.addOrderItem(order_itemDto);
        return ResponseEntity.status(apiResponse!=null ?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateOrderItem(@PathVariable Long id,Order_itemDto order_itemDto){
        ApiResponse apiResponse= orderItemService.updateOrderItem(id,order_itemDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOrderItem(@PathVariable Long id){
        ApiResponse apiResponse= orderItemService.deleteOrderItem(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

}

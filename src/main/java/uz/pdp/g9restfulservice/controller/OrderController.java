package uz.pdp.g9restfulservice.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.g9restfulservice.dto.OrderDto;
import uz.pdp.g9restfulservice.entity.Order;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.service.OrderService;

import javax.persistence.GeneratedValue;

@RestController
@RequestMapping("/order")
public class OrderController {
    final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public HttpEntity<?> getOrders(){
        return ResponseEntity.status(orderService.getAllOrder()!=null? HttpStatus.OK:HttpStatus.CONFLICT).body(orderService.getAllOrder());
    }

    @GetMapping({"/id"})
    public HttpEntity<?> getOrderById(@PathVariable Long id){
        Order order = orderService.getFindById(id);
        return  ResponseEntity.status(order!=null?HttpStatus.OK:HttpStatus.CONFLICT).body(order);
    }

    @PostMapping
    public HttpEntity<?> addOrder(@RequestBody OrderDto orderDto){
        ApiResponse apiResponse=orderService.addOrder(orderDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }
    @PutMapping({"/id"})
    public HttpEntity<?> updateOrder(@PathVariable Long id,@RequestBody OrderDto orderDto){
        ApiResponse apiResponse=orderService.updateOrder(id,orderDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);

    }
    @DeleteMapping({"/id"})
    public HttpEntity<?> deleteOrder(@PathVariable Long id){
        ApiResponse apiResponse=orderService.deleteOrder(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
}

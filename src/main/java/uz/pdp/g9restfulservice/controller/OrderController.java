package uz.pdp.g9restfulservice.controller;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.g9restfulservice.entity.Order;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.service.OrderService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public HttpEntity<?> getOrders() {
        return ResponseEntity.status(orderService.getAllOrder() != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(orderService.getAllOrder());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOrderById(@PathVariable Long id) {
        Order order = orderService.getFindById(id);
        return ResponseEntity.status(order != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(order);
    }

//    @PostMapping
//    public HttpEntity<?> addOrder(@RequestBody OrderDto orderDto){
//        ApiResponse apiResponse=orderService.makeOrder(orderDto);
//        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
//    }

    @GetMapping("/make")
    public HttpEntity<?> makeOrder(HttpServletRequest request) {
        String cartProducts = null;

        System.out.println(request.getCookies());

        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("cart-products")) {
                cartProducts = cookie.getValue();
            }
        }

        if (cartProducts != null) {
            JSONObject jsonObject = org.json.Cookie.toJSONObject(cartProducts);
            System.out.println(jsonObject);
        }


        ApiResponse apiResponse = orderService.makeOrder();
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

//    @PutMapping("/{id}")
//    public HttpEntity<?> updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) {
//        ApiResponse apiResponse = orderService.updateOrder(id, orderDto);
//        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
//
//    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOrder(@PathVariable Long id) {
        ApiResponse apiResponse = orderService.deleteOrder(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }
}

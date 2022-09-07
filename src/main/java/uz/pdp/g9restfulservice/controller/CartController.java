package uz.pdp.g9restfulservice.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.g9restfulservice.dto.CartDto;
import uz.pdp.g9restfulservice.entity.Cart;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.service.CartService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public HttpEntity<?> getCarts() {
        return ResponseEntity.status(cartService.getAllCarts() != null ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(cartService.getAllCarts());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getCartById(@PathVariable Long id) {
        Cart cart = cartService.getCartById(id);
        return ResponseEntity.status(
                cart != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(cart);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCart(@PathVariable Long id) {
        ApiResponse apiResponse = cartService.deleteCart(id);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> saveCart(@Valid @RequestBody CartDto cartDto) {
        ApiResponse apiResponse = cartService.addCart(cartDto);

        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateCart(@PathVariable Long id, @Valid @RequestBody CartDto cartDto) {
        ApiResponse apiResponse = cartService.updateCart(id, cartDto);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);

    }
}

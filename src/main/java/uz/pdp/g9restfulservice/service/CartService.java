package uz.pdp.g9restfulservice.service;

import org.springframework.stereotype.Service;
import uz.pdp.g9restfulservice.dto.CartDto;
import uz.pdp.g9restfulservice.entity.Cart;
import uz.pdp.g9restfulservice.entity.Product;
import uz.pdp.g9restfulservice.entity.User;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.repository.CartRepository;
import uz.pdp.g9restfulservice.repository.ProductRepository;
import uz.pdp.g9restfulservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    final CartRepository cartRepository;

    final UserRepository userRepository;

    final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getCartById(Long id) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        return cartOptional.orElse(null);
    }

    public ApiResponse deleteCart(Long id) {
        try {
            cartRepository.deleteById(id);
            return new ApiResponse("Cart delete", true);
        } catch (Exception e) {
            return new ApiResponse("Cart like this id is not exist", false);
        }

    }


    public ApiResponse addCart(CartDto cartDto) {

        Optional<User> userOptional = userRepository.findById(cartDto.getUserId());

        List<Product> products = null;
        if (cartDto.getProductIds() != null) {
            products = productRepository.findAllById(cartDto.getProductIds());
        }


        Cart cart = Cart.builder()
                .created_at(cartDto.getCreated_at())
                .quantity(cartDto.getQuantity())
                .total(cartDto.getTotal())
//                .user(userOptional.get())
                .products(products)
                .build();
        Cart save = cartRepository.save(cart);
        if (save!=null) {
            return new ApiResponse("Cart saved", true);
        }
        return new ApiResponse("Not saved cart",false);
    }



    public ApiResponse updateCart(Long id,CartDto cartDto){
        Optional<Cart> optionalCart = cartRepository.findById(id);
        Optional<User> optionalUser = userRepository.findById(cartDto.getUserId());
        List<Product> productList = productRepository.findAllById(cartDto.getProductIds());

        if(optionalCart.isEmpty()){
            return new ApiResponse("Cart like this id is not found",false);
        }

        Cart cart = optionalCart.get();
        cart.setQuantity(cartDto.getQuantity());
        cart.setTotal(cartDto.getTotal());
        cart.setCreated_at(cartDto.getCreated_at());
//        cart.setUser(optionalUser.get());
        cart.setProducts(productList);

        cartRepository.save(cart);
        return new ApiResponse("Cart update",true);
    }

}

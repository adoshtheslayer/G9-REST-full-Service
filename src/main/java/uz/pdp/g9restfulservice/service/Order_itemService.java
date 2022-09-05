package uz.pdp.g9restfulservice.service;

import org.springframework.stereotype.Service;
import uz.pdp.g9restfulservice.dto.Order_itemDto;
import uz.pdp.g9restfulservice.entity.Order;
import uz.pdp.g9restfulservice.entity.Order_item;
import uz.pdp.g9restfulservice.entity.Product;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.repository.OrderRepository;
import uz.pdp.g9restfulservice.repository.Order_itemRepository;
import uz.pdp.g9restfulservice.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class Order_itemService {
   private final Order_itemRepository order_itemRepository;
   private final OrderRepository orderRepository;
   private final ProductRepository productRepository;

    public Order_itemService(Order_itemRepository order_itemRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.order_itemRepository = order_itemRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }
    public List<Order_item> getAllOrderItem(){
        return order_itemRepository.findAll();
    }
    public Order_item getFindById(Long id){
        Optional<Order_item> order_item = order_itemRepository.findById(id);
        return  order_item.orElse(null);
    }

    public ApiResponse addOrderItem(Order_itemDto order_itemDto){
        Optional<Product> product = productRepository.findById(order_itemDto.getProduct_id());
        if (product.isEmpty()) {
            return new ApiResponse("Product not found",false);

        }
        Optional<Order> order = orderRepository.findById(order_itemDto.getOrder_id());
        if (order.isEmpty()) {
            return new ApiResponse("Order not found",false);
        }
        Order_item order_item=Order_item.builder()
                        .order(order.get())
                         .product(product.get())
                        .quantity(order_itemDto.getQuantity()).build();
        order_itemRepository.save(order_item);
        return new ApiResponse("Saved",true);
    }

    public ApiResponse deleteOrderItem(Long id){
        try {
            order_itemRepository.deleteById(id);
            return new ApiResponse("Deleted",true);
        }catch (Exception e){
            return new ApiResponse("Order item not found",false);
        }
    }

    public ApiResponse updateOrderItem(Long id,Order_itemDto order_itemDto){
        Optional<Order_item> order_item = order_itemRepository.findById(id);
        Optional<Product> product = productRepository.findById(order_itemDto.getProduct_id());
        Optional<Order> order = orderRepository.findById(order_itemDto.getOrder_id());
        if (order_item.isEmpty()) {
            return new ApiResponse("Order item not found",false);
        }
        if (product.isEmpty()) {
            return new ApiResponse("Product not found",false);
        }
        if (order.isEmpty()) {
            return new ApiResponse("Order not found",false);
        }
        Order_item order1=Order_item.builder()
                .id(id)
                .product(product.get())
                .order(order.get())
                .quantity(order_itemDto.getQuantity()).build();
        order_itemRepository.save(order1);
        return new ApiResponse("Updated",true);

    }
}

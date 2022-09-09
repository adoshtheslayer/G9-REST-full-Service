package uz.pdp.g9restfulservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.g9restfulservice.entity.Order;
import uz.pdp.g9restfulservice.entity.OrderItem;
import uz.pdp.g9restfulservice.entity.User;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.repository.OrderItemRepository;
import uz.pdp.g9restfulservice.repository.OrderRepository;
import uz.pdp.g9restfulservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;

    private final EmailService emailService;


    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public Order getFindById(Long id) {
        Optional<Order> byId = orderRepository.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse deleteOrder(Long id) {
        try {
            orderRepository.deleteById(id);
            return new ApiResponse("Order deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Order not found", false);
        }
    }

    public ApiResponse makeOrder() {

//        Optional<User> optionalUser = userRepository.findById(orderDto.getUser_id());
//        if (optionalUser.isEmpty()) {
//            return new ApiResponse("User not found ", false);
//        }
//        User user = optionalUser.get();
        Double totalPrice = null;
        // TODO: 07/09/22 calc total price of products

        Order order = Order
                .builder()
                .status("NEW")
                .total_price(totalPrice)
//                .user(user)
//                .created_at(orderDto.getCreated_at())
                .build();
        orderRepository.save(order);

        // TODO: 07/09/22
        List<OrderItem> allOrderItemsByOrderId = orderItemRepository.findAllByOrderId(order.getId());
        StringBuilder productsStr = new StringBuilder();
        for (OrderItem orderItem : allOrderItemsByOrderId) {
            productsStr.append(orderItem.getProduct().getName());
            productsStr.append(", ");
        }
        String emailMsg = String.format("%s successfully ordered", productsStr);
        emailService.sendSimpleMessage("streo.94.94.94@gmail.com", "This is sarlavha", emailMsg);

        return new ApiResponse("Order saved", true);

    }

//    public ApiResponse updateOrder(Long id, OrderDto orderDto) {
//        Optional<Order> optionalOrder = orderRepository.findById(id);
//        Optional<User> optionalUser = userRepository.findById(orderDto.getUser_id());
//        if (optionalOrder.isEmpty()) {
//            return new ApiResponse("User not found", false);
//        }
//        if (optionalOrder.isEmpty()) {
//            return new ApiResponse("Order not found", false);
//        }
//        Order order = Order
//                .builder()
//                .id(id)
//                .status(orderDto.getStatus())
//                .total_price(orderDto.getTotal_price())
//                .created_at(orderDto.getCreated_at())
//                .user(optionalUser.get())
//                .build();
//        orderRepository.save(order);
//        return new ApiResponse("Order updated", true);
//
//    }


}

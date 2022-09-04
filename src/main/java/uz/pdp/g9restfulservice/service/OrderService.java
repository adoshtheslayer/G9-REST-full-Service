package uz.pdp.g9restfulservice.service;

import org.springframework.stereotype.Service;
import uz.pdp.g9restfulservice.dto.OrderDto;
import uz.pdp.g9restfulservice.entity.Order;
import uz.pdp.g9restfulservice.entity.User;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.repository.OrderRepository;
import uz.pdp.g9restfulservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    final OrderRepository orderRepository;
    final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }

    public Order getFindById(Long id){
        Optional<Order> byId = orderRepository.findById(id);
        return  byId.orElse(null);
    }

    public ApiResponse deleteOrder(Long id){
        try{
            orderRepository.deleteById(id);
            return  new ApiResponse("Order deleted",true);
        }catch (Exception e){
            return new ApiResponse("Order not found",false);
        }
    }

    public ApiResponse addOrder(OrderDto orderDto){
        Optional<User> byId= userRepository.findById(orderDto.getUser_id());
        if (byId.isEmpty()) {
            return new ApiResponse("User not found ",false);
        }
        Order order=Order
                .builder()
                .status(orderDto.getStatus())
                .total_price(orderDto.getTotal_price())
                .user(byId.get())
                .created_at(orderDto.getCreated_at())
                .build();
        orderRepository.save(order);
        return new ApiResponse("Order saved",true);

    }

    public ApiResponse updateOrder(Long id,OrderDto orderDto){
        Optional<Order> optionalOrder = orderRepository.findById(id);
        Optional<User> optionalUser = userRepository.findById(orderDto.getUser_id());
        if (optionalOrder.isEmpty()) {
            return new ApiResponse("User not found",false);
        }
        if (optionalOrder.isEmpty()) {
            return new ApiResponse("Order not found",false);
        }
        Order order= Order
                .builder()
                .id(id)
                .status(orderDto.getStatus())
                .total_price(orderDto.getTotal_price())
                .created_at(orderDto.getCreated_at())
                .user(optionalUser.get())
                .build();
        orderRepository.save(order);
        return new ApiResponse("Order updated",true);

    }


}

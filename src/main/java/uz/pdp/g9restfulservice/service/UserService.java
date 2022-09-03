package uz.pdp.g9restfulservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.g9restfulservice.dto.UserDto;
import uz.pdp.g9restfulservice.entity.Product;
import uz.pdp.g9restfulservice.entity.Role;
import uz.pdp.g9restfulservice.entity.User;
import uz.pdp.g9restfulservice.entity.enums.RoleEnum;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.repository.AddresRepository;
import uz.pdp.g9restfulservice.repository.RoleRepository;
import uz.pdp.g9restfulservice.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AddresRepository addresRepository;

    public List<User> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> addressPage = userRepository.findAll(pageable);
        return addressPage.getContent();
    }

    public User getUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        return optionalUser.orElse(null);
    }

    public ApiResponse addUser(UserDto userDto) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getUsername().equals(userDto.getUsername()) ||
                    user.getPhoneNumber().equals(userDto.getPhoneNumber())) {
                return new ApiResponse("username or phoneNumber error", false);
            }
        }
        Role role = roleRepository.findById(2l).get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User savedUser = User.builder()
                .roles(roles)
                .username(userDto.getUsername())
                .phoneNumber(userDto.getPhoneNumber())
                .password(userDto.getPassword())
                .fullName(userDto.getFullName())
                .build();
        userRepository.save(savedUser);
        return new ApiResponse("successfully added", true);
    }

    public ApiResponse editUser(Long id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
// id si shunga teng bo'lmagan userlar phoneNumber ini tekshiradi
            boolean existsByPhoneNumber = userRepository.existsByPhoneNumberAndIdNot(userDto.getPhoneNumber(), id);
            if (existsByPhoneNumber) {
                return new ApiResponse("phoneNumber is exists", false);
            }

            boolean existsByUsername = userRepository.existsByUsernameAndIdNot(userDto.getUsername(), id);
            if (existsByUsername) {
                return new ApiResponse("username is exists", false);
            }

            User editUser = optionalUser.get();
            editUser.setPassword(userDto.getPassword());
            editUser.setFullName(userDto.getFullName());
            editUser.setUsername(userDto.getUsername());
            editUser.setPhoneNumber(userDto.getPhoneNumber());
            userRepository.save(editUser);
            return new ApiResponse("successfully edited", true);
        }
        return new ApiResponse("user not found", false);
    }

    public ApiResponse deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return new ApiResponse("successfully deleted",true);
        }catch (Exception e){
            return new ApiResponse("user not found",false);
        }

    }
}

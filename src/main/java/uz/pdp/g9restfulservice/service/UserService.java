package uz.pdp.g9restfulservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.g9restfulservice.dto.UserDto;
import uz.pdp.g9restfulservice.entity.Role;
import uz.pdp.g9restfulservice.entity.User;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.repository.AddresRepository;
import uz.pdp.g9restfulservice.repository.RoleRepository;
import uz.pdp.g9restfulservice.repository.UserRepository;

import java.util.*;

@Service
public class UserService implements UserDetailsService {
//
    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final AddresRepository addresRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, AddresRepository addresRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.addresRepository = addresRepository;
    }

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
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .build();
        userRepository.save(savedUser);
        return new ApiResponse("successfully added", true);
    }

    public ApiResponse editUser(Long id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
// id si shunga teng bo'lmagan userlar phone    Number ini tekshiradi
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
            editUser.setFirstName(userDto.getFirstName());
            editUser.setLastName(userDto.getLastName());
            editUser.setEmail(userDto.getEmail());
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
            return new ApiResponse("successfully deleted", true);
        } catch (Exception e) {
            return new ApiResponse("user not found", false);
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("User not found");
        }
        return optionalUser.get();
    }
}

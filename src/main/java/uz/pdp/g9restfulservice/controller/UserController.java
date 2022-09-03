package uz.pdp.g9restfulservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.g9restfulservice.dto.UserDto;
import uz.pdp.g9restfulservice.entity.Product;
import uz.pdp.g9restfulservice.entity.User;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public HttpEntity<?> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        List<User> users = userService.getUsers(page, size);
        return ResponseEntity.ok(users);

    }

    @GetMapping("/{id}")
    public HttpEntity<?> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return ResponseEntity.status(user != null ? 200 : 409).body(user);
    }

    @PostMapping()
    public HttpEntity<?> addUser(@Valid @RequestBody UserDto userDto) {
        ApiResponse apiResponse = userService.addUser(userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED :
                HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        ApiResponse apiResponse = userService.editUser(id, userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK :
                HttpStatus.CONFLICT).body(apiResponse);

    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteUser(@PathVariable Long id) {
        ApiResponse deleted = userService.deleteUser(id);
        return ResponseEntity.status(deleted.isSuccess()?HttpStatus.OK:
                HttpStatus.NOT_FOUND).body(deleted);
    }

}

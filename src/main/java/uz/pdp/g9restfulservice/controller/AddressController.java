package uz.pdp.g9restfulservice.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.g9restfulservice.dto.AddressDto;
import uz.pdp.g9restfulservice.entity.Address;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final  AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    @GetMapping
    public HttpEntity<?> getAddresses(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
    List<Address> addresses=addressService.getAddresses(page,size);
    return ResponseEntity.ok(addresses);

    }

    @GetMapping("/{userId}")
    public HttpEntity<?> getUserAddress(@PathVariable Long userId){
        Address address=addressService.getUserAdress(userId);
        return ResponseEntity.status(address!=null?200:409).body(address);
    }

    @PostMapping("/{userId}")
    public HttpEntity<?> addAddress(@PathVariable Long userId, @RequestBody AddressDto addressDto){
       ApiResponse apiResponse= addressService.addAddress(userId,addressDto);
       return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:
               HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{userId}")
    public HttpEntity<?> updateAddress(@PathVariable Long userId,@RequestBody AddressDto addressDto){
        ApiResponse apiResponse=addressService.updateAddress(userId,addressDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @DeleteMapping("/{userId}")
    public HttpEntity<?> deleteAddress(@PathVariable Long userId){
        ApiResponse apiResponse=addressService.deleteAddress(userId);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}

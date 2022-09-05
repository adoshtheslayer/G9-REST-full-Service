package uz.pdp.g9restfulservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.g9restfulservice.dto.AddressDto;
import uz.pdp.g9restfulservice.entity.Address;
import uz.pdp.g9restfulservice.entity.User;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.repository.AddresRepository;
import uz.pdp.g9restfulservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    private final AddresRepository addresRepository;
    private final UserRepository userRepository;

    public AddressService(AddresRepository addresRepository, UserRepository userRepository) {
        this.addresRepository = addresRepository;
        this.userRepository = userRepository;
    }

    public List<Address> getAddresses(int page, int size) {
        Pageable pageable= PageRequest.of(page, size);
        Page<Address> addresses = addresRepository.findAll(pageable);
        return addresses.getContent();
    }

    public ApiResponse addAddress(Long userId, AddressDto addressDto) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {

            Address address = Address.builder()
                    .addressLine(addressDto.getAddressLine())
                    .street(addressDto.getStreet())
                    .city(addressDto.getCity())
                    .user(optionalUser.get())
                    .build();
            addresRepository.save(address);
            return new ApiResponse("successfully address added", true);
        }

        return new ApiResponse("user not found",false);
    }

    public ApiResponse updateAddress(Long userId, AddressDto addressDto) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            User user=optionalUser.get();

            List<Address> addresses = addresRepository.findAll();

            for (Address address : addresses) {
                if (address.getUser().equals(user)){
                    address.setAddressLine(addressDto.getAddressLine());
                    address.setCity(addressDto.getCity());
                    address.setStreet(addressDto.getStreet());
                    addresRepository.save(address);
                    return new ApiResponse("address updated successfully",true);
                }else {
                    return new ApiResponse("address not found",false);
                }
            }
        }
        return new ApiResponse("user not found",false);
    }

    public ApiResponse deleteAddress(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            User user=optionalUser.get();

            List<Address> addresses = addresRepository.findAll();

            for (Address address : addresses) {
                if (address.getUser().equals(user)){
                    addresRepository.delete(address);
                    return new ApiResponse("address deleted successfully",true);
                }else {
                    return new ApiResponse("address not found",false);
                }
            }
        }
        return new ApiResponse("user not found",false);
    }

    public Address getUserAdress(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            User user=optionalUser.get();

            List<Address> addresses = addresRepository.findAll();

            for (Address address : addresses) {
                if (address.getUser().equals(user)){

                    return address;
                }else {
                    return null;
                }
            }
        }
        return null;

    }
}

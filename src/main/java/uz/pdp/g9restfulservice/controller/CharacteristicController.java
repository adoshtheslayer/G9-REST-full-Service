package uz.pdp.g9restfulservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.g9restfulservice.dto.CategoryDto;
import uz.pdp.g9restfulservice.entity.Characteristic;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.service.AttachmentService;
import uz.pdp.g9restfulservice.service.CharacteristicService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/character")
@RequiredArgsConstructor
public class CharacteristicController {

    final CharacteristicService characteristicService;


    /**
     * Get Mapping method
     */


    @GetMapping()
    public HttpEntity<?> getAllCharacter() {
        return ResponseEntity.status(characteristicService.getCharacterAll() != null ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(characteristicService.getCharacterAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getByIdCharacter(@PathVariable Long id) throws IOException {
        return ResponseEntity.status(characteristicService.getCharacterById(id) != null ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(characteristicService.getCharacterById(id));
    }


    /**
     * Delete Mapping method
     */


    @DeleteMapping("/{id}")
    public ResponseEntity deleteByIdCharacter(@PathVariable Long id) throws Exception {
        ApiResponse apiResponse = characteristicService.deleteCharacter(id);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * Update Mapping method
     */

    @PutMapping("/{id}")
    public HttpEntity<?> updateById(@PathVariable Long id,@Valid @RequestBody Characteristic characteristic) {
        ApiResponse apiResponse = characteristicService.updateCharacterById(id, characteristic);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * Post Mapping method
     */


        @PostMapping
    public HttpEntity<?> saveCharacter(@Valid @RequestBody Characteristic characteristic) {
        ApiResponse apiResponse = characteristicService.saveCharacter(characteristic);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

}

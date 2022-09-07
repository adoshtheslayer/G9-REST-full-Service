package uz.pdp.g9restfulservice.exseption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {


    @ExceptionHandler(value = ProductNotfoundException.class)
    public ResponseEntity<Object> exception(ProductNotfoundException exception) {
        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> exception(NotFoundException exception) {
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }




}

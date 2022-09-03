package uz.pdp.g9restfulservice.exseption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NameExistException extends RuntimeException {

    private String fieldName;

    private Object fieldValue;


    public NameExistException(String fieldName, Object fieldValue) {

        super(String.format("'%s' nomli %s mavjud.", fieldValue, fieldName));

        this.fieldName = fieldName;

        this.fieldValue = fieldValue;

    }

}

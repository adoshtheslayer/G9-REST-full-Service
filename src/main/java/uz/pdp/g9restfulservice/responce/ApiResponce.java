package uz.pdp.g9restfulservice.responce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.g9restfulservice.entity.Discount;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiResponce {
    private String message;
    private Object  data;
    private  boolean success;
    public ApiResponce(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}

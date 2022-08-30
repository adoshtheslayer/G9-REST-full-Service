package uz.pdp.g9restfulservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private String message;

    private boolean success;

    private Object data;

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApiResponse {

    private String message;
    private boolean success;

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;

    }

    private Object data;

}

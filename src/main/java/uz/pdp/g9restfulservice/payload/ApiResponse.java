package uz.pdp.g9restfulservice.payload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApiResponse {

    private String message;
    private boolean success;

}

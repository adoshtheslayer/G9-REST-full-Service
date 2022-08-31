package uz.pdp.g9restfulservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class ProductDto {

    @NotNull(message = "must write name!!!")
    private String name;

    @NotNull
    private Integer quantity;

    @NotNull
    private Double price;

    private Long discountId;

    private List<Long> characteristicIds;

    @NotNull(message = "category is mandatory")
    private Long categoryId;
}

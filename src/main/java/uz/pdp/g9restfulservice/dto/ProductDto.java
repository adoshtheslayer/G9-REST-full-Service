package uz.pdp.g9restfulservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class ProductDto {

    private Long id;

    @NotNull(message = "must write name!!!")
    @NotBlank(message = "nameis mandatory")
    private String name;

    @NotNull
    @NotBlank(message = "quentity is mandatory")
    private Integer quantity;

    @Min(1)
    @Max(1000000000)
    private Double price;

    @NotNull(message = "discount is mandatory")
    private Long attachmentId;

    @NotNull(message = "discount is mandatory")
    private Long discountId;

    @NotNull(message = "characteristic is mandatory")
    private List<Long> characteristicIds;

    @NotNull(message = "category is mandatory")
    private Long categoryId;
}

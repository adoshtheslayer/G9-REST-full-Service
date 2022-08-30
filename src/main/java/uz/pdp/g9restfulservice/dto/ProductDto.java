package uz.pdp.g9restfulservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDto {

    private Long id;

    private String name;

    private Integer quantity;

    private Double price;

    private Long attachmentId;

    private Long discountId;

    private List<Long> characteristicIds;

    private Long categoryId;
}

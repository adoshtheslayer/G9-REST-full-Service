package uz.pdp.g9restfulservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CartDto {

    private int quantity;

    private double total;

    private LocalDate created_at;

    private List<Long>productIds;

    private Long userId;

}

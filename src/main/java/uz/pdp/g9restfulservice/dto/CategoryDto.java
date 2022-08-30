package uz.pdp.g9restfulservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {

    @NotNull(message = "category name is not be emptry")
    private String  name;

    private Long parentId;
}

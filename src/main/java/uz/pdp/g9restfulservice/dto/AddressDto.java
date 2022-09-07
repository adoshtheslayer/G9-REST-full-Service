package uz.pdp.g9restfulservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddressDto {
    @NotNull(message = "must write city")
    private String city;

    @NotNull(message = "must write street")
    private String street;

    @NotNull(message = "must write addressLine")
    private String addressLine;

}

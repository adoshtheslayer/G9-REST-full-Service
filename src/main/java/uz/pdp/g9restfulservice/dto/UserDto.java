package uz.pdp.g9restfulservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {

    @NotEmpty(message = "must write fullName")
    private String fullName;

    @Size(min = 3, max = 50, message = "The number of letters should be less than 3 and not more than 50")
    private String username;

    @NotNull(message = "must write password")
    private String password;

    @NotNull(message = "must write phoneNumber")
    private String phoneNumber;

}

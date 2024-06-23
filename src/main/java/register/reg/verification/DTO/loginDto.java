package register.reg.verification.DTO;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class loginDto {

    @NotNull
    @NotBlank
    @Length(min=3,max = 50)
    private String username;
    @NotNull
    @NotBlank
    @Length(min = 5)
    private String password;

}

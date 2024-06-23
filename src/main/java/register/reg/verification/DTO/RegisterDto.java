package register.reg.verification.DTO;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDto {

    @NotNull
    @NotBlank
    @Length(min=3,max = 50)
    private String firstName;

    @NotNull
    @NotBlank
    @Length(min=3,max = 50)
    private String lastName;

    @NotNull
    @NotBlank
    @Length(min=3,max = 50)
    private String username;

    @NotNull
    @NotBlank
    @Length(min=5)
    private String password;

    
    @NotNull
    @NotBlank
    @Email
    private String email;
}

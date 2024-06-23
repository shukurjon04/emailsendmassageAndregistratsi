package register.reg.verification.Entity;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import register.reg.verification.roles.RoleNAme;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class role implements  GrantedAuthority {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleNAme name;

    @Override
    public String getAuthority() {
        return name.name();
    }

}

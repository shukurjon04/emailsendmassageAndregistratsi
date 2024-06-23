package register.reg.verification.Entity.Domain;

import java.util.UUID;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Home {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
}

package register.reg.verification.Entity;

import java.util.UUID;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    public Product(String name) {
        this.name = name;
    }

    @Column(nullable = false,updatable=false)
    @CreatedBy
    private UUID createdBy;

    @Column(nullable=false)
    @LastModifiedBy
    private UUID updateBy;

    

}

package register.reg.verification.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import register.reg.verification.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,UUID> {

}

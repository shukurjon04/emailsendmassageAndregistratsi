package register.reg.verification.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import register.reg.verification.Entity.Domain.Home;

@Repository
public interface Homerepository extends JpaRepository<Home, UUID>{

}

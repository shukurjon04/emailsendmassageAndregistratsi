package register.reg.verification.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import register.reg.verification.Entity.Userreg;

import java.util.List;
import java.util.Optional;


public interface USerRepository extends JpaRepository<Userreg, UUID>{

    boolean existsByUsername(String Username);

    boolean existsByEmail(String email);

   Optional<Userreg> findByEmailAndEmailCode(String email, String emailCode);
   Optional<Userreg> findByUsername(String username);
}

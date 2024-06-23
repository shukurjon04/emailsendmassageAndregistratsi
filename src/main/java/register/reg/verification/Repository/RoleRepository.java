package register.reg.verification.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import register.reg.verification.Entity.role;
import java.util.List;
import register.reg.verification.roles.RoleNAme;


@Repository
public interface  RoleRepository extends JpaRepository<role, Long>{

    role findByName(RoleNAme name);

}

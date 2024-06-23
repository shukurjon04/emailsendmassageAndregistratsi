package register.reg.verification.Config;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import register.reg.verification.Entity.Userreg;

public class AuditingSecurity implements AuditorAware<UUID> {

    @Override
    public Optional<UUID> getCurrentAuditor() {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       if(authentication!=null&&authentication.isAuthenticated()&&!authentication.getPrincipal().equals("anonymousUser")){
           return Optional.of(((Userreg)authentication.getPrincipal()).getId());
       }
       return Optional.empty();
    }

}

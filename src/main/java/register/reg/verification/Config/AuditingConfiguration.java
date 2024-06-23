package register.reg.verification.Config;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditingConfiguration {

    @Bean
    public AuditorAware<UUID> auditorAware(){
        return new AuditingSecurity();
    }
}

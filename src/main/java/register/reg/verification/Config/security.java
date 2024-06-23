package register.reg.verification.Config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import register.reg.verification.Security.JwtFilter;
import register.reg.verification.Service.AuthService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class security {

 

     
   
        @Autowired
        AuthService authService;
        @Autowired(required=true)
        JwtFilter filter;

        @Autowired
        public void customizeUserDetails(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
                authenticationManagerBuilder.userDetailsService(authService);
        }


        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
                return authenticationConfiguration.getAuthenticationManager();
        }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        (authorize) ->
                                authorize
                                        .requestMatchers(HttpMethod.GET, "/", "/api/product")
                                        .permitAll()
                                        .requestMatchers(HttpMethod.POST,"/auth/user/**")
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated()
                )
                .formLogin(login -> login.disable())
                .httpBasic(basic -> basic.disable());
        http.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement((sessionManagementConfigurer) -> {
                sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        return http.build();
    }

   
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl impl = new JavaMailSenderImpl();
        impl.setHost("smtp.gmail.com");
        impl.setPort(587);

        impl.setPassword("ouvq gyor rvpa vgap");
        impl.setUsername("shukurjonboqiyev0@gmail.com");

        Properties properties = impl.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.debug", "true");

        return impl;


    }

}

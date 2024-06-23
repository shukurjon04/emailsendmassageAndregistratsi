package register.reg.verification.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.mail.internet.MimeMessage;
import register.reg.verification.DTO.ApiResponse;
import register.reg.verification.DTO.RegisterDto;
import register.reg.verification.DTO.loginDto;
import register.reg.verification.Entity.Userreg;
import register.reg.verification.Entity.role;
import register.reg.verification.Repository.RoleRepository;
import register.reg.verification.Repository.USerRepository;
import register.reg.verification.Security.JwtProvider;
import register.reg.verification.roles.RoleNAme;

@Service
public class AuthService implements UserDetailsService {

   
    USerRepository repository;


    PasswordEncoder passwordEncoder;
    
    RoleRepository roleRepository;
    
    JavaMailSender javaMailSender;
   
    AuthenticationManager authenticationManager;
   
    JwtProvider jwtp;


    @org.springframework.context.annotation.Lazy
    @Autowired
    public AuthService(AuthenticationManager authenticationManager, JavaMailSender javaMailSender, JwtProvider jwtp, PasswordEncoder passwordEncoder, USerRepository repository, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.javaMailSender = javaMailSender;
        this.jwtp = jwtp;
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
        this.roleRepository = roleRepository;
    }
    


    public ApiResponse register(@RequestBody RegisterDto dto){
        if (repository.existsByEmail(dto.getEmail())) {
            if(repository.existsByUsername(dto.getUsername())){
                return new ApiResponse("this username and email are already in use",false);
            }
            return new ApiResponse("this Email Already in use", false);
        }
        Userreg user = new Userreg();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setEmailCode(String.valueOf(new Random().nextInt(1,999999)));
        user.setRoles(Collections.singleton(roleRepository.findByName(RoleNAme.USER)));
        if (sendMailMassage(dto.getEmail(), user.getEmailCode())) {
            repository.save(user);
            return new ApiResponse("user sign in successfull", true);
        }
        return new ApiResponse("Failed ", false);

    }


    public boolean sendMailMassage(String sendingMail,String SecureCode){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper help = new MimeMessageHelper(message,"utf-8");
            help.setText("<p style =\"font-size:25px; font-family:sans-serif;\">verification code</p>\n"+"         <span style =\"font-size: 25px; background-color : rgb(152,150,241); letter-spacing: 2px;font-family: sans-serif; color: rgb(211,249,250); text-align: center; padding: 10px 20px; display inline-block;\">"+SecureCode+"</span>",true);
            help.setTo(sendingMail);
            help.setSubject("email varification by Shukurjon");
            help.setFrom("shukurjonboqiyev0@gmail.com");
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            return false;
        }
       
    }

    public ApiResponse verificate(String email, String emailCode) {
        Userreg byEmailAndEmailCode = repository.findByEmailAndEmailCode(email,emailCode).orElse(null);
        if(Objects.nonNull(byEmailAndEmailCode)){
            byEmailAndEmailCode.setEnabled(true);
            byEmailAndEmailCode.setEmailCode(null);
            repository.save(byEmailAndEmailCode);
            return new ApiResponse("email successfully verificate", true);
        }
        return  new ApiResponse("Something is wrong", false);
    }

    public ApiResponse login(loginDto dto){
        try{
           Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
           return new ApiResponse("successfully login in", true, jwtp.generateToken(dto.getUsername(), ((Userreg) authenticate.getPrincipal()).getRoles()));

        }catch(Exception e){
            return new ApiResponse(e.getMessage(), false);
        }

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("not found this user"));
        
    }
       

    

}

package register.reg.verification.Controller;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import register.reg.verification.DTO.ApiResponse;
import register.reg.verification.DTO.RegisterDto;
import register.reg.verification.DTO.loginDto;
import register.reg.verification.Service.AuthService;



@RestController
@RequestMapping("/auth/user")
public class UserController {


    private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity add(@RequestBody RegisterDto dto){
        ApiResponse register = authService.register(dto);
        return ResponseEntity.status(register.isSuccess()?201:409).body(register);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody loginDto dto){
      return ResponseEntity.ok(authService.login(dto));
    }

    @PostMapping("/verification")
    public ResponseEntity verification(@RequestParam String email,@RequestParam String emailCode){
        ApiResponse register = authService.verificate(email,emailCode);
        return ResponseEntity.status(register.isSuccess()?201:409).body(register);
    }
    
    
}

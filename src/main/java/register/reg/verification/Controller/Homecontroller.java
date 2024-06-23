package register.reg.verification.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import register.reg.verification.Service.HomeService;


@RestController
@RequestMapping("/")
public class Homecontroller {

    @Autowired
    HomeService homeservice;

    @GetMapping
    public ResponseEntity getMethodName(@RequestParam int page,@RequestParam int size) {
        return ResponseEntity.ok("welcome to home page");
    }
    
}

package register.reg.verification.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import register.reg.verification.DTO.productDto;
import register.reg.verification.Service.ProductService;



@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity getMethodName(@RequestParam int page,@RequestParam int size) {
        return ResponseEntity.ok(productService.getAll(page, size));
    }

    @PostMapping("/add")
    public ResponseEntity postMethodName(@RequestBody productDto dto) {
        return ResponseEntity.ok(productService.Add(dto));
    }
    
    
}

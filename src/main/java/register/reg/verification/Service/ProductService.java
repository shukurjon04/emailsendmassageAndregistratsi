package register.reg.verification.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import register.reg.verification.DTO.productDto;
import register.reg.verification.Entity.Product;
import register.reg.verification.Repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAll(@RequestParam int page,@RequestParam int size){
        return productRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public Product Add(productDto dto){
        if(!dto.getName().isEmpty()){
            return productRepository.save(new Product(dto.getName())); 
        }
        return null;
    }

}

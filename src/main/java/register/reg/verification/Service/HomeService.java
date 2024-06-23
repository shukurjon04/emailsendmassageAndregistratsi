package register.reg.verification.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import register.reg.verification.Entity.Domain.Home;
import register.reg.verification.Repository.Homerepository;

@Service
public class HomeService {

    @Autowired
    Homerepository homerepository;

    public List<Home> getAll(int page,int size){
        return homerepository.findAll(PageRequest.of(page, size)).getContent();
    }

}

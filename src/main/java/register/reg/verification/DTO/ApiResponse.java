package register.reg.verification.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class ApiResponse {
    private String massage;
    private boolean  success;
    private String token;

    public ApiResponse(String massage, boolean success) {
        this.massage = massage;
        this.success = success;
    }

    public ApiResponse(String massage, boolean success, String token) {
        this.massage = massage;
        this.success = success;
        this.token = token;
    }



    
    
}

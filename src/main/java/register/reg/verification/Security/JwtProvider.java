package register.reg.verification.Security;

import java.util.Date;
import java.util.Set;

import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import register.reg.verification.Entity.role;

@Component
public class JwtProvider {

    private final long expireDate = 1000*60*60*24;
    private final String secretKey = "appinit";

    public final String generateToken(String Subject,Set<role> roles){
        return Jwts
                   .builder()
                   .setSubject(Subject)
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis()+expireDate))
                   .claim("roles", roles)
                   .signWith(SignatureAlgorithm.HS512, secretKey)
                   .compact();
    }

    public final boolean validateToken(String token){
        try{
            Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public final String getSubject(String token){
        return Jwts
                   .parser()
                   .setSigningKey(secretKey)
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject();
    }
}

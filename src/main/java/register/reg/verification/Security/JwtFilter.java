package register.reg.verification.Security;

import java.io.IOException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import register.reg.verification.Service.AuthService;
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    AuthService authService;

    @Autowired
    JwtProvider jwtprovider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      
        String token = request.getHeader("Authentication");
        if(Objects.nonNull(token)&&token.startsWith("Bearer")){
            token = token.substring(7);
            if(jwtprovider.validateToken(token)){
                UserDetails userdetails = authService.loadUserByUsername(jwtprovider.getSubject(token));
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userdetails,null, userdetails.getAuthorities()));
            }

        }
        filterChain.doFilter(request, response);
    }



}

package register.reg.verification.Entity;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Userreg implements UserDetails {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable=false)
    private String firstName;

    @Column(nullable=false)
    private String lastName;

    @Column(nullable=false,unique=true)
    private String username;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false,unique=true)
    private String email;

    @Column(nullable=false)
    private String emailCode;

    @Column(nullable=false,updatable=false)
    @CreationTimestamp
    private Timestamp createdAtTime;

    @Column(nullable=false,updatable=false)
    @UpdateTimestamp
    private Timestamp updateAtTime;
    
    @Column(nullable=false)
    private boolean  isAccountNonExpired = true;

    @Column(nullable=false)
    private boolean  isAccountNonLocked = true;

    @Column(nullable=false)
    private boolean  isCredentialsNonExpired = true;

    @Column(nullable=false)
    private boolean  isEnabled = false;

    @ManyToMany
    private Set<role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return this.roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return this.isEnabled;
    }

}

package bits.pilani.sem2.dda.assignment1.dto;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author yogeshk
 */
public class CustomUserDetails implements UserDetails {

    private final UserDetails user;
    private final String role;
    private final String firstName;
    
    public CustomUserDetails(UserDetails user, String role, String firstName) {
        this.user = user;
        this.role = role;
        this.firstName = firstName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
    
    public String getRole() {
        return role;
    }
    
    public String getFirstName() {
        return firstName;
    }
}

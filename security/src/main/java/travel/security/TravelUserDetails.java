package travel.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import travel.domain.User;

import java.util.Collection;
import java.util.List;

public class TravelUserDetails implements UserDetails {
    private User user;

    public TravelUserDetails(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of((GrantedAuthority) () -> user.getRole().name());
    }

    @Override
    public String getPassword() {
        return user.getCredentials().getPassword();
    }

    @Override
    public String getUsername() {
        return user.getCredentials().getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

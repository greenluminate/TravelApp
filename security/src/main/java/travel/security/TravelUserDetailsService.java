package travel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import travel.persistence.TravelRepository;

@Service
public class TravelUserDetailsService implements UserDetailsService {

    @Autowired
    private TravelRepository travelRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new TravelUserDetails(travelRepository
                .getUsers()
                .stream()
                .filter(x -> x.getCredentials().getLoginName().equals(username))
                .findFirst().orElse(null));
    }
}

package travel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import travel.domain.Credentials;
import travel.domain.User;
import travel.persistence.UserRepository;
import travel.persistence.dto.CredentialsDto;
import travel.persistence.dto.UserDto;
import travel.security.TravelUserDetails;
import travel.service.AuthenticationException;
import travel.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    private User loggedInUser;

    @Override
    public User findUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertUserToDto).collect(Collectors.toList());
    }

    private UserDto convertUserToDto(User user) {
        UserDto userDTO = new UserDto();
        userDTO.setId(user.getId());
        userDTO.setRole(user.getRole());
        userDTO.setFullName(user.getFullName());

        CredentialsDto credDTO = new CredentialsDto();
        credDTO.setLoginName(user.getCredentials().getLoginName());
        credDTO.setPassword(user.getCredentials().getPassword());
        userDTO.setCredentials(credDTO);

        return userDTO;
    }


    @Override
    public User authenticateUser(Credentials credentials) {
        User user = userRepository.findAll().stream().filter(u ->
                        u.getCredentials().equals(credentials))
                .findFirst().orElse(null);

        if (user == null)
            throw new AuthenticationException("User does not exits with the given credentials!");

        loggedInUser = user;

        return loggedInUser;
    }

    @Override
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((TravelUserDetails) authentication.getPrincipal()).getUser();
    }
}

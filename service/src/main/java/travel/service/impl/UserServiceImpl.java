package travel.service.impl;

import org.springframework.stereotype.Service;
import travel.domain.Review;
import travel.domain.User;
import travel.persistence.UserRepository;
import travel.persistence.dto.CredentialsDto;
import travel.persistence.dto.ReviewDto;
import travel.persistence.dto.UserDto;
import travel.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserServiceImpl() {
    }


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
}

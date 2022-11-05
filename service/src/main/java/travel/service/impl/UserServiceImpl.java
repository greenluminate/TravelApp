package travel.service.impl;

import org.springframework.stereotype.Service;
import travel.domain.User;
import travel.persistence.UserRepository;
import travel.persistence.dto.UserDto;
import travel.service.UserService;

import java.util.List;

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
        return null;
    }

    @Override
    public List<UserDto> findAllUsers() {
        return null;
    }
}

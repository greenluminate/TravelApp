package travel.service;

import travel.domain.User;
import travel.persistence.dto.UserDto;

import java.util.List;

public interface UserService {
    User findUserById(long id);

    List<UserDto> findAllUsers();
}

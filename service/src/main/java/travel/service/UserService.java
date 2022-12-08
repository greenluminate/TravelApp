package travel.service;

import travel.domain.Credentials;
import travel.domain.User;
import travel.persistence.dto.UserDto;

import java.util.List;

public interface UserService {
    User findUserById(long id);

    List<UserDto> findAllUsers();

    User authenticateUser(Credentials credentials);

    User getLoggedInUser();
}

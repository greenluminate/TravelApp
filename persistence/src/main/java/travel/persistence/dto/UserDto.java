package travel.persistence.dto;

import travel.domain.Role;

public class UserDto {
    private long id;
    private String fullName;
    private Role role;
    private CredentialsDto credentials;
}

package rosol.userservice.service;

import rosol.userservice.dto.UserDto;
import rosol.userservice.model.AppUser;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<AppUser> getAllUsers();
    List<UserDto> getAllUsersDto();
    Optional<AppUser> getUserById(Long id);
}

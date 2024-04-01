package rosol.userservice.service;

import org.springframework.http.ResponseEntity;
import rosol.userservice.dto.UserDto;
import rosol.userservice.model.AppUser;

import java.time.LocalDate;

public interface UserCommandService {
    AppUser createActiveUser(String userName, String userLastname, LocalDate dateOfBirth, char gender, String nationality, String phone, String email,
                             String username, String password, String entityId, int nationalId, String role, int permission, String missionId);
    ResponseEntity<AppUser> updateUser(UserDto userToEdit, long userId);
    ResponseEntity<AppUser> deleteActiveUser(long userId);
}

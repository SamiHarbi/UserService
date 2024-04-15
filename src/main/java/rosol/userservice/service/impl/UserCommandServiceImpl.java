package rosol.userservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rosol.userservice.dto.UserDto;
import rosol.userservice.model.AppUser;
import rosol.userservice.repository.UserRepository;
import rosol.userservice.service.UserCommandService;
import rosol.userservice.service.impl.interservicecomm.RolesService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

/**
 * Service implementation for command actions coming from the Client side. This implementation follows the CQRS pattern
 */
@Service
public class UserCommandServiceImpl implements UserCommandService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesService rolesService;

    /**
     * This function considers the active attribute to operate
     *
     * @param userName     username
     * @param userLastname user lastname
     * @return created uer with id
     */
    @Override
    public AppUser createActiveUser(String userName, String userLastname, LocalDate dateOfBirth, char gender, String nationality, String phone, String email,
                                    String username, String password, String entityId, int nationalId, String role, int permission, String missionId) {
        AppUser appUser = new AppUser(userName, userLastname, dateOfBirth, gender, nationality, phone, email, username, password,
                entityId, nationalId, 1, permission, missionId, new Date(), new Date(), true);
        return this.userRepository.save(appUser);
    }

    @Override
    public ResponseEntity<AppUser> updateUser(UserDto userToEdit, long userId) {
            return this.userRepository.findByIdAndActiveTrue(userId).map(u -> {
                u.setName(userToEdit.getName());
                u.setLastname(userToEdit.getLastname());
                u.setDateOfBirth(userToEdit.getDateOfBirth());
                u.setGender(userToEdit.getGender());
                u.setNationality(userToEdit.getNationality());
                u.setPhone(userToEdit.getPhone());
                u.setEmail(userToEdit.getEmail());
                u.setUsername(userToEdit.getUsername());
                u.setPassword(userToEdit.getPassword());
                u.setEntityId(userToEdit.getEntityId());
                u.setNationalId(userToEdit.getNationalId());
                setUserRoleId(userToEdit, u);
                u.setPermission(userToEdit.getPermission());
                u.setMissionId(userToEdit.getMissionId());
                u.setModificationDate(new Date());
                return ResponseEntity.ok(this.userRepository.save(u));
            }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    private void setUserRoleId(UserDto userToEdit, AppUser u) {
        try {
            u.setRole(this.rolesService.getRoleIdByName(userToEdit.getRole()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This function considers the active attribute to operate
     *
     * @param userId id of the user to be modified
     * @return user with the active attribute modified
     */
    @Override
    public ResponseEntity<AppUser> deleteActiveUser(long userId) {
        return this.userRepository.findById(userId).map(u -> {
            u.setActive(false);
            u.setModificationDate(new Date());
            return ResponseEntity.ok(this.userRepository.save(u));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

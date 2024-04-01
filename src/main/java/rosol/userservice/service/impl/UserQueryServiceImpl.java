package rosol.userservice.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rosol.userservice.config.SystemConfiguration;
import rosol.userservice.dto.UserDto;
import rosol.userservice.model.AppRole;
import rosol.userservice.model.AppUser;
import rosol.userservice.repository.UserRepository;
import rosol.userservice.service.UserQueryService;
import rosol.userservice.service.impl.interservicecomm.RolesService;
import rosol.userservice.util.JsonConverterController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation for query actions coming from the Client side. This implementation follows the CQRS pattern
 */
@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesService rolesService;

    @Override
    public List<AppUser> getAllUsers() {
        return this.userRepository.findByActiveTrue();
    }

    @Override
    public List<UserDto> getAllUsersDto() {
        try {
            List<AppUser> users = this.userRepository.findByActiveTrue();
            List<AppRole> roles = this.rolesService.getAllRoles();
            List<UserDto> userDtoList = new ArrayList<>();

            for (AppUser user : users){
                userDtoList.add(new UserDto(user.getId(), user.getName(), user.getLastname(), user.getDateOfBirth(), user.getGender(),
                        user.getNationality(), user.getPhone(), user.getEmail(), user.getUsername(), user.getPassword(),
                        user.getEntityId(), user.getNationalId(), roles.stream()
                        .filter(role -> role.getId() == user.getRole())
                        .map(AppRole::getName)
                        .findFirst()
                        .orElse(String.valueOf(user.getRole())),
                        user.getPermission(), user.getMissionId()));
            }

            return userDtoList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<AppUser> getUserById(Long id) {
        Optional<AppUser> user = this.userRepository.findByIdAndActiveTrue(id);
        return (user.isPresent()) ? user : null;
    }
}

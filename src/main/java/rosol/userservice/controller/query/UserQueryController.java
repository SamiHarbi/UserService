package rosol.userservice.controller.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rosol.userservice.dto.UserDto;
import rosol.userservice.model.AppUser;
import rosol.userservice.service.UserQueryService;

import java.util.List;
import java.util.Optional;

/**
 * Controller class to handle all queries received from the Client side. This controller follows the CQRS pattern
 */
@Controller
public class UserQueryController {

    @Autowired
    private UserQueryService userQueryService;

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        Optional<AppUser> user = this.userQueryService.getUserById(id);
        if (user == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers(){
        List<UserDto> users = this.userQueryService.getAllUsersDto();
        if (users.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(users);
        }
    }
}

package rosol.userservice.controller.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rosol.userservice.dto.UserDto;
import rosol.userservice.model.AppUser;
import rosol.userservice.service.UserCommandService;

/**
 * Controller class to handle all commands received from the Client side. This controller follows the CQRS pattern
 */
@Controller
public class UserCommandController {

    @Autowired
    private UserCommandService userCommandService;

    /**
     * This function considers the active attribute to operate
     * @param dto user object to be saved
     * @return user with an id assigned by the system
     */
    @PostMapping("/newactive")
    public ResponseEntity<AppUser> createNewActiveUser(@RequestBody UserDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userCommandService.createActiveUser(dto.getName(), dto.getLastname(),
                dto.getDateOfBirth(), dto.getGender(), dto.getNationality(), dto.getPhone(), dto.getEmail(), dto.getUsername(),
                dto.getPassword(), dto.getEntityId(), dto.getNationalId(), dto.getRole(), dto.getPermission(), dto.getMissionId()));
    }

    @PutMapping("/update/{id}")
    //public ResponseEntity<AppUser> updateUser(@RequestBody AppUser userToEdit, @PathVariable Long id){
    public ResponseEntity<AppUser> updateUser(@RequestBody UserDto userToEdit, @PathVariable Long id){
        return this.userCommandService.updateUser(userToEdit, id);
    }

    /**
     * Function to delete an element from the database
     * @param id id of the element to be deleted
     * @return "no content" response
     */
    /*@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        this.userCommandService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }*/

    /**
     * This function considers the active attribute to operate
     * @param id id of the user to be deleted
     * @return user with the active attribute set to false
     */
    @PutMapping("/deleteactive/{id}")
    public ResponseEntity<AppUser> deleteActiveUser(@PathVariable Long id){
        return this.userCommandService.deleteActiveUser(id);
    }
}

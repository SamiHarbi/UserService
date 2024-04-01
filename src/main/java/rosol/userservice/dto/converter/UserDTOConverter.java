package rosol.userservice.dto.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import rosol.userservice.dto.UserDto;
import rosol.userservice.model.AppUser;

/**
 * Data Transfer Object converter class to convert an AppUser object to a UserDto instance when needed. These type of
 * classes are used to perform conversion between objects exchanged between Client and Server sides
 */
@Component
@RequiredArgsConstructor
public class UserDTOConverter {

    private final ModelMapper modelMapper;

    public UserDto convertToDto(AppUser user){
        return modelMapper.map(user, UserDto.class);
    }
}

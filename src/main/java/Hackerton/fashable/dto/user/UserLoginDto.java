package Hackerton.fashable.dto.user;

import Hackerton.fashable.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class UserLoginDto {
    private String email;
    private String pw;

    public static UserLoginDto toUserLoginDto(User user) {
        UserLoginDto userLoginDto = new UserLoginDto(user.getEmail(), user.getPw());
        return userLoginDto;
    }
}


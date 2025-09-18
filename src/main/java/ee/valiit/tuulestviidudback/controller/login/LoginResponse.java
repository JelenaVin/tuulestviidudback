package ee.valiit.tuulestviidudback.controller.login;

import ee.valiit.tuulestviidudback.persistance.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Data
public class LoginResponse  {
    private Integer userId;
    private String roleName;
}

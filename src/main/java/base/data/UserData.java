package base.data;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserData {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String role;

}

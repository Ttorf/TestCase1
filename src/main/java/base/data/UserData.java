package base.data;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserData {
    private Object username;
    private Object password;
    private Object email;
    private Object firstName;
    private Object lastName;
    private Object role;
    private Integer id;

    public UserData() {
    }

    public static UserData generationData(Object username, Object password, Object email,
                                          Object firstName, Object lastName, Object role) {
        return new UserData()
                .setUsername(username)
                .setPassword(password)
                .setEmail(email)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setRole(role);
    }


    public static UserData generationData(UserData userData) {
        return new UserData()
                .setUsername(userData.getUsername())
                .setPassword(userData.getPassword())
                .setEmail(userData.getEmail())
                .setFirstName(userData.getFirstName())
                .setLastName(userData.getLastName())
                .setRole(userData.getRole())
                .setId(userData.getId());
    }
}

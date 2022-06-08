package base.data;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class BaseData {

    @Setter
    @Getter
    public static int port = 80;

    @Setter
    @Getter
    public static int tempId;

    @Setter
    @Getter
    public static String productPath = "/product";

    @Setter
    @Getter
    public static String userPath = "/user";

    @Setter
    @Getter
    public static String userPathCreate = userPath + "/register";

    @Setter
    @Getter
    public static UserData user;

    @Setter
    @Getter
    public static ProductData productData;

    @Setter
    @Getter
    public static List<UserData> users;

    @Getter
    @Setter
    public static LoginData loginData;
}

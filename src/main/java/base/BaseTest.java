package base;

import base.data.BaseData;
import base.data.LoginData;
import base.data.ProductData;
import base.data.UserData;
import com.google.gson.Gson;
import org.junit.jupiter.api.AfterAll;
import steps.ApiSteps;
import utils.ApiHelper;

import java.util.stream.IntStream;

public class BaseTest {

    public ApiSteps loginAs(LoginData loginData) {
        return new ApiSteps(loginData);
    }

    public ApiSteps loginAsAdmin() {
        LoginData loginData = new LoginData().setUsername("admin").setPassword("admin");
        return new ApiSteps(loginData);
    }

    public ApiSteps changeLoginData(LoginData loginData) {
        BaseData.setLoginData(loginData);
        return new ApiSteps(loginData);
    }

    public ApiSteps changeLoginData(String id) {
        int idTemp = BaseData.getUser().getId();
        String userTemp = BaseData.getLoginData().getUsername();
        UserData[] userDataList = new Gson().fromJson(new ApiHelper(BaseData.getLoginData()).
                getRequest(BaseData.getUserPath()).getBody().asString(), UserData[].class);
        String pass = BaseData.getUser().getPassword().toString();
        if (!id.equals("1") && BaseData.getLoginData().getUsername().equals("admin")) {
            BaseData.setTempId(idTemp);
        }
        if (userDataList[userDataList.length - 1].getId().equals(Integer.parseInt(id))) {
            BaseData.setUser(UserData.generationData(userDataList[userDataList.length - 1]
                    .setPassword(pass).setId(idTemp)));
        } else {
            IntStream.range(0, userDataList.length - 1)
                    .filter(i -> userDataList[i].getId().equals(Integer.parseInt(id)))
                    .mapToObj(i -> UserData.generationData(userDataList[i].setPassword(pass).setId(idTemp)))
                    .forEach(BaseData::setUser);
        }
        if (userTemp.equals(BaseData.getUser().getUsername())) {
            throw new RuntimeException("UserData не изменился");
        } else
            return changeLoginData(new LoginData().setUsername(BaseData.getUser().getUsername().toString())
                    .setPassword(BaseData.getUser().getPassword().toString()));
    }

    public static void fillUser() {
        UserData[] userDataList = getAllUserData();
        for (int i = 0; i < userDataList.length - 1; i++) {
            if (userDataList[i].getUsername().equals(BaseData.loginData.getUsername())) {
                BaseData.setUser(userDataList[i]);
                break;
            }

        }

    }

    public static UserData[] getAllUserData() {
        return new Gson().fromJson(new ApiHelper(BaseData.getLoginData()).
                getRequest(BaseData.getUserPath()).getBody().asString(), UserData[].class);
    }


    public static ProductData[] getAllProductData() {
        return new Gson().fromJson(new ApiHelper(BaseData.getLoginData()).
                getRequest(BaseData.getProductPath()).getBody().asString(), ProductData[].class);
    }

    @AfterAll
    public static void deleteAllObjects() {
        new BaseTest().loginAsAdmin();
        ApiHelper apiHelper = new ApiHelper(BaseData.getLoginData());

            UserData[] userDataList = getAllUserData();
            for (int i = 1; i < userDataList.length - 1; i++) {
                apiHelper.deleteRequest(BaseData.getUserPath() + "/" + userDataList[i].getId());
            }

            ProductData[] productDataList = getAllProductData();
            for (int i = 0; i < productDataList.length - 1; i++) {
                apiHelper.deleteRequest(BaseData.getProductPath() + "/" + productDataList[i].getId());

        }
    }
}

package apiTests.userTests.negativeTest;

import base.BaseTest;
import base.data.BaseData;
import base.data.UserData;
import com.google.gson.Gson;
import dataProvider.UserDataProvider;
import io.qameta.allure.Allure;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.HashMap;

import static base.data.BaseData.userPath;
import static base.data.BaseData.userPathCreate;

@Story("Negative check validation  User fields")
public class NegativeValidationTest extends BaseTest {
    @ParameterizedTest(name = "{1}")
    @ArgumentsSource(UserDataProvider.NegativeTest.class)
    @DisplayName("check validation fields")
    public void validationTestAdmin(UserData userData, String allureDesc, HashMap<String,
            String> expectedErrorFieldMap) {
        Allure.description(allureDesc);
        String body = new Gson().toJson(userData);

        loginAsAdmin()
                .postRequest(userPathCreate, body, 400, expectedErrorFieldMap);
    }

    @ParameterizedTest(name = "{1}")
    @ArgumentsSource(UserDataProvider.NegativeTestUser.class)
    @DisplayName("check validation fields")
    public void validationTestAnotherProfile(UserData userData, String allureDesc, UserData userDataSecond) {
        Allure.description(allureDesc);
        String body = new Gson().toJson(userData);
        String bodySecond = new Gson().toJson(userDataSecond);

        loginAsAdmin()
                .postRequest(userPathCreate, userPath, body)
                .changeLoginData(BaseData.getUser().getId().toString())
                .postRequest(userPathCreate, bodySecond, 403);
    }

}

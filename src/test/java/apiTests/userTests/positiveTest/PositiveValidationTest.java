package apiTests.userTests.positiveTest;

import base.BaseTest;
import base.data.UserData;
import com.google.gson.Gson;
import dataProvider.UserDataProvider;
import io.qameta.allure.Allure;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static base.data.BaseData.userPath;
import static base.data.BaseData.userPathCreate;

@Story("Positive check validation User fields")
public class PositiveValidationTest extends BaseTest {
    @ParameterizedTest(name = "{1}")
    @ArgumentsSource(UserDataProvider.PositiveValidationTest.class)
    @DisplayName("check validation fields")
    public void validationTest(UserData userData, String allureDesc) {
        Allure.description(allureDesc);
        String body = new Gson().toJson(userData);

        loginAsAdmin()
                .postRequest(userPathCreate, userPath, body);
    }

}

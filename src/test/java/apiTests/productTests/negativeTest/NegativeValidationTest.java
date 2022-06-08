package apiTests.productTests.negativeTest;

import base.BaseTest;
import base.data.BaseData;
import base.data.ProductData;
import base.data.UserData;
import com.google.gson.Gson;
import dataProvider.ProductDataProvider;
import io.qameta.allure.Allure;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.HashMap;

import static base.data.BaseData.*;

@Story("Negative check validation  Product fields")
public class NegativeValidationTest extends BaseTest {

    @ParameterizedTest(name = "{1}")
    @ArgumentsSource(ProductDataProvider.NegativeTestAdmin.class)
    @DisplayName("check validation fields")
    public void validationTestAdmin(ProductData productData, String allureDesc, HashMap<String,
            String> expectedErrorFieldMap) {
        Allure.description(allureDesc);
        String body = new Gson().toJson(productData);

        loginAsAdmin()
                .postRequest(productPath, body, 400, expectedErrorFieldMap);
    }

    @ParameterizedTest(name = "{1}")
    @ArgumentsSource(ProductDataProvider.NegativeTestUser.class)
    @DisplayName("check validation fields")
    public void validationTestUser(ProductData productData, String allureDesc, HashMap<String,
            String> expectedErrorFieldMap, UserData userData) {

        Allure.description(allureDesc);
        String bodyProduct = new Gson().toJson(productData);
        String bodyUser = new Gson().toJson(userData);
        loginAsAdmin()
                .postRequest(userPathCreate, userPath, bodyUser)
                .changeLoginData(BaseData.getUser().getId().toString())
                .postRequest(productPath, bodyProduct, 400, expectedErrorFieldMap);
    }

}

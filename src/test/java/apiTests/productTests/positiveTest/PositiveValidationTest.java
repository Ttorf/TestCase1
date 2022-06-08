package apiTests.productTests.positiveTest;

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

import static base.data.BaseData.*;

@Story("Positive check validation Product fields")
public class PositiveValidationTest extends BaseTest {

    @ParameterizedTest(name = "{1}")
    @ArgumentsSource(ProductDataProvider.PositiveTestAsAdmin.class)
    @DisplayName("check validation fields with creat product as Admin")
    public void validationTestAdmin(ProductData productData, String allureDesc) {
        Allure.description(allureDesc);
        String body = new Gson().toJson(productData);

        loginAsAdmin()
                .postRequest(productPath, productPath, body)
                .deleteRequest(productPath, String.valueOf(BaseData.getProductData().getId()));
    }

    @ParameterizedTest(name = "{1}")
    @ArgumentsSource(ProductDataProvider.PositiveTestAsUser.class)
    @DisplayName("check validation fields with creat product as User")
    public void validationTestUser(ProductData productData, String allureDesc, UserData userData) {
        Allure.description(allureDesc);
        String bodyProduct = new Gson().toJson(productData);
        String bodyUser = new Gson().toJson(userData);

        loginAsAdmin()
                .postRequest(userPathCreate, userPath, bodyUser)
                .changeLoginData(BaseData.getUser().getId().toString())
                .postRequest(productPath, productPath, bodyProduct);
    }

}

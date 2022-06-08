package steps;

import base.BaseTest;
import base.data.BaseData;
import base.data.ErrorData.ErrorData;
import base.data.LoginData;
import base.data.ProductData;
import base.data.UserData;
import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import utils.ApiHelper;

import java.util.HashMap;

import static base.data.BaseData.loginData;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiSteps extends BaseTest {


    public ApiSteps(LoginData loginData) {
        BaseData.setLoginData(loginData);
        BaseTest.fillUser();
    }

    @Step("Проверка создания объекта")
    public ApiSteps postRequest(String pathPost, String pathGet, String body) {
        Response response = new ApiHelper(loginData).postResponse(pathPost, body, 200);
        Integer id = response.getBody().jsonPath().get("id");
        if (pathGet.contains("user")) {
            String pass = new Gson().fromJson(body, UserData.class).getPassword().toString();
            BaseData.getUser().setPassword(pass).setId(id);
            getRequest(pathGet + "/", String.valueOf(BaseData.getUser().getId()));
        } else {
            BaseData.setProductData(new ProductData().setId(id));
            getRequest(pathGet + "/", String.valueOf(BaseData.getProductData().getId()));
        }
        return this;
    }

    @Step("Проверка создания объекта c ошибкой")
    public ApiSteps postRequest(String path, String body, int statusCode, HashMap<String, String> expectedErrorMap) {
        ErrorData errors = new Gson().fromJson(new ApiHelper(loginData).postResponse(path, body, statusCode)
                .getBody().asString(), ErrorData.class);
        expectedErrorMap.forEach((k, v) -> {
            String fieldName = errors.getErrors()[0].getField();
            String errorMessages = String.join("", errors.getErrors()[0].getMessages());
            assertAll(
                    () -> assertEquals(k, fieldName, fieldName),
                    () -> assertEquals(v, errorMessages)
            );
        });
        return this;
    }

    @Step("Проверка создания объекта c ошибкой без body")
    public ApiSteps postRequest(String path, String body, int statusCode) {
        new ApiHelper(loginData).postResponse(path, body, statusCode);
        return this;
    }

    @Step("Проверка созданного объекта по id {id}")
    public ApiSteps getRequest(String path, String id) {
        new ApiHelper(loginData).getRequest(path + id).then().log().all();
        return this;
    }


    @Step("Проверка удаления объекта по id {id}")
    public ApiSteps deleteRequest(String path, String id) {
        new ApiHelper(loginData).deleteRequest(path + "/" + id).then().log().all();
        return this;
    }


}

package steps;

import io.qameta.allure.Step;
import utils.ApiHelper;

import java.util.HashMap;

public class ApiSteps {

    @Step("Проверка создания объекта")
    public static void postRequest(String path, String body) {
        ApiHelper.postRequest(path, body);
    }

    @Step("Проверка создания объекта c ошибкой")
    public static void postRequest(String path, String body, int statusCode, HashMap<String, String> expectedErrorFieldMap) {
        ApiHelper.postRequest(path, body, statusCode, expectedErrorFieldMap);
    }

}

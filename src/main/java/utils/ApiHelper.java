package utils;

import base.data.Data;
import base.data.LoginData;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.HashMap;


public class ApiHelper {

    @Getter
    @Setter
    public static String token;

    private ApiHelper() {
        setToken(generationToken());
    }

    public static void postRequest(String path, String body) {
        Response response = new ApiHelper().postResponse(path, body, 200);
        getRequest(path + "/", response.getBody().jsonPath().get("id").toString());
    }

    public static void postRequest(String path, String body, int statusCode, HashMap<String, String> map) {
        new ApiHelper().postResponseWithError(path, body, statusCode, map);
    }

    @Step("Проверка созданного объекта по id {id}")
    public static void getRequest(String path, String id) {
        RestAssured.given().port(Data.getPort())
                .header("Authorization", "Bearer " + getToken())
                .contentType(ContentType.JSON)
                .when().get(path + id).then().log().all().statusCode(200);
    }

    private static String generationToken() {
        String json = new Gson().toJson(loginAsAdmin());
        return RestAssured.given().port(Data.getPort())
                .contentType(ContentType.JSON)
                .when()
                .body(json)
                .post("/user/token")
                .then().log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("jwt");
    }

    private static LoginData loginAsAdmin() {
        return new LoginData().setUsername("admin").setPassword("admin");
    }

    private Response postResponse(String path, String body, int statusCode) {
        Response response = RestAssured.given().port(Data.getPort())
                .header("Authorization", "Bearer " + getToken())
                .contentType(ContentType.JSON)
                .when()
                .body(body)
                .log().body()
                .post(path);
        response.then().log().body().statusCode(statusCode);
        return response;
    }

    private void postResponseWithError(String path, String body, int statusCode, HashMap<String, String> expectedErrorFieldMap) {
        LinkedTreeMap<String, ArrayList<LinkedTreeMap<String, Object>>> errorMap =
                new Gson().fromJson(postResponse(path, body, statusCode)
                        .getBody().asString(), LinkedTreeMap.class);
        expectedErrorFieldMap.forEach((k, v) -> {
            String errorMessages = errorMap.get("errors").get(0).get("messages").toString();
            String fieldName = errorMap.get("errors").get(0).get("field").toString();

            Assertions.assertEquals(fieldName, k);
            Assertions.assertTrue(errorMap.get("errors").get(0).get("messages").toString().contains(v),
                    "Ошибка не совпала! \n ожидаемая: " + v + ", фактическая: " + errorMessages);
        });
    }

}

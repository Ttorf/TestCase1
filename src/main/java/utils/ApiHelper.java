package utils;

import base.data.BaseData;
import base.data.LoginData;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;


public class ApiHelper {

    @Getter
    @Setter
    public String token;

    public ApiHelper(LoginData loginData) {
        setToken(generationToken(loginData));
    }

    private String generationToken(LoginData loginData) {
        String json = new Gson().toJson(loginData);
        return RestAssured.given().port(BaseData.getPort())
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

    public Response postResponse(String path, String body, int statusCode) {
        Response response = RestAssured.given().port(BaseData.getPort())
                .header("Authorization", "Bearer " + getToken())
                .contentType(ContentType.JSON)
                .when()
                .body(body)
                .log().body()
                .post(path);
        response.then().log().body().statusCode(statusCode);
        return response;
    }

    public Response deleteRequest(String path) {
        Response response = RestAssured.given().port(BaseData.getPort())
                .header("Authorization", "Bearer " + getToken())
                .contentType(ContentType.JSON)
                .when()
                .delete(path);
        response.then().statusCode(204);
        return response;
    }

    public Response getRequest(String path) {
        Response response = RestAssured.given().port(BaseData.getPort())
                .header("Authorization", "Bearer " + getToken())
                .contentType(ContentType.JSON)
                .when()
                .get(path);
        response.then().statusCode(200);
        return response;
    }

}

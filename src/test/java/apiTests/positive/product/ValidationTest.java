package apiTests.positive.product;

import dataProvider.ProductDataProvider;
import base.data.ProductData;
import com.google.gson.Gson;
import io.qameta.allure.Allure;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static base.data.Data.productPath;
import static steps.ApiSteps.postRequest;

@Story("Positive check validation")
public class ValidationTest {

    @ParameterizedTest(name = "{1}")
    @ArgumentsSource(ProductDataProvider.PositiveTest.class)
    @DisplayName("check validation fields")
    public void validationTest(ProductData productData, String allureDesc) {
        Allure.description(allureDesc);
        String body = new Gson().toJson(productData);
        postRequest(productPath, body);
    }

}

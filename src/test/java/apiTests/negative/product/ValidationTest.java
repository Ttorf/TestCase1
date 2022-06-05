package apiTests.negative.product;

import base.data.ProductData;
import com.google.gson.Gson;
import dataProvider.ProductDataProvider;
import io.qameta.allure.Allure;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.HashMap;

import static base.data.Data.productPath;
import static steps.ApiSteps.postRequest;

@Story("Negative check validation")
public class ValidationTest {

    @ParameterizedTest(name = "{1}")
    @ArgumentsSource(ProductDataProvider.NegativeTest.class)
    @DisplayName("check validation fields")
    public void validationTest(ProductData productData, String allureDesc, HashMap<String, String> expectedErrorFieldMap) {
        Allure.description(allureDesc);
        String body = new Gson().toJson(productData);
        postRequest(productPath, body, 400, expectedErrorFieldMap);
    }


}

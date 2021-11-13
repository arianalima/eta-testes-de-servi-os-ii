package requests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.QuerySchema;

import static io.restassured.RestAssured.given;

public class LanguageRequest {
    public static Response getLanguageResponse(RequestSpecification SPEC, String code) {
        return given().
                    spec(SPEC).
                    body(QuerySchema.getLanguageAsJson(code)).
                when().
                    post();
    }

    public static String formatResponseKey(String key){
        return String.format("data.language.%s", key);
    }
}

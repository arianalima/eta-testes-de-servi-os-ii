import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;


import static io.restassured.RestAssured.*;

public class CountryQueryTest {
    @Test
    public void shouldReturnAllFieldsAndStatus200() {
        given().
                header("Content-Type", "application/json").
                body("{\"query\":\"query {\\n country (code: \\\"BR\\\") {\\n    name\\n    native\\n    capital\\n    emoji\\n    currency\\n    languages {\\n        code\\n        name\\n    }\\n  }\\n}\", \"variables\": {}}").
            when().
                post(Constants.BASE_URL).
            then().
                assertThat().
                statusCode(200).
                body(containsString("{\"data\":{\"country\":{\"name\":\"Brazil\",\"native\":\"Brasil\",\"capital\":\"Brasília\",\"emoji\":\"\uD83C\uDDE7\uD83C\uDDF7\",\"currency\":\"BRL\",\"languages\":[{\"code\":\"pt\",\"name\":\"Portuguese\"}]}}}"));
    }

    @Test
    public void shouldReturnErrorAndStatus400ForInvalidField() {
        given().
                header("Content-Type", "application/json").
                body("{\"query\":\"query {\\n country (code: \\\"BR\\\") {\\n    nam\\n    native\\n    capital\\n    emoji\\n    currency\\n    languages {\\n        code\\n        name\\n    }\\n  }\\n}\", \"variables\": {}}").
            when().
                post(Constants.BASE_URL).
            then().
                assertThat().
                statusCode(400).
                body(containsString("{\"errors\":[{\"message\":\"Cannot query field \\\"nam\\\" on type \\\"Country\\\". Did you mean \\\"name\\\"?\",\"locations\":[{\"line\":3,\"column\":5}],\"extensions\":{\"code\":\"GRAPHQL_VALIDATION_FAILED\"}}]}\n"));

    }
}


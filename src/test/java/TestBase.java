import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class TestBase {

    public RequestSpecification SPEC = new RequestSpecBuilder()
            .addHeader("Content-Type", "application/json")
            .setBaseUri(Constants.BASE_URL).build();

}
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static requests.LanguageRequest.*;

import static org.hamcrest.Matchers.*;

public class LanguageQueryTest extends TestBase{
    @Test
    public void shouldReturnAllFieldsAndStatus200() {
        getLanguageResponse(SPEC, "pt").
            then().
                assertThat().
                statusCode(Constants.SUCCESS_STATUS_CODE).
                body(formatResponseKey("code"), is("pt")).
                body(formatResponseKey("name"), is("Portuguese")).
                body(formatResponseKey("native"), is("Português")).
                body(formatResponseKey("rtl"), is(false));
    }

    @Test
    public void shouldMatchFieldsType(){
        getLanguageResponse(SPEC, "pt").
        then().
                assertThat().
                statusCode(Constants.SUCCESS_STATUS_CODE).
                contentType(ContentType.JSON).
                body(formatResponseKey("code"), instanceOf(String.class)).
                body(formatResponseKey("name"), instanceOf(String.class)).
                body(formatResponseKey("native"), instanceOf(String.class)).
                body(formatResponseKey("rtl"), instanceOf(Boolean.class));
    }

    @Test
    public void shouldReturnNullAndStatus200ForInvalidLanguage() {
        getLanguageResponse(SPEC, "dummy").
        then().
                assertThat().
                statusCode(Constants.SUCCESS_STATUS_CODE).
                body("data.language", nullValue());
    }


}

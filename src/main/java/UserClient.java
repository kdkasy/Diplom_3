import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class UserClient {
    private static final String USER_PATH = "/api/auth/register";
    private static final String UPDATE_USER_PATH = "/api/auth/user";
    private static final String LOG_IN_PATH = "/api/auth/login";

    @Step("Create user from api")
    public ValidatableResponse createUser(User courier) {
        return given()
                .spec(RestClient.getBaseSpec())
                .body(courier)
                .when()
                .post(USER_PATH)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat().body("accessToken", notNullValue());
    }

    @Step("Log in from api")
    public ValidatableResponse logIn(User user){
        return given()
                .spec(RestClient.getBaseSpec())
                .body(UserCredentials.from(user))
                .when()
                .post(LOG_IN_PATH)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat().body("accessToken", notNullValue())
                .assertThat().body("refreshToken", notNullValue());
    }

    @Step("Delete user from api")
    public ValidatableResponse deleteUser(String token) {
        return given()
                .spec(RestClient.getBaseSpec())
                .header("authorization", token)
                .delete(UPDATE_USER_PATH)
                .then()
                .statusCode(HttpStatus.SC_ACCEPTED);
    }
}


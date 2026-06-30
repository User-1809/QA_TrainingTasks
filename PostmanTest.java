import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostmanTest {

    private final String BASE_URL =
            "https://postman-echo.com";

    @Test
    public void testGet() {
        given()
                .when()
                .get(BASE_URL + "/get?foo=bar")
                .then()
                .statusCode(200)
                .body("args.foo", equalTo("bar"));
    }

    @Test
    public void testPostText() {
        given()
                .contentType("text/plain; charset=utf-8")
                .body("hello")
                .when()
                .post(BASE_URL + "/post")
                .then()
                .statusCode(200)
                .body("data", equalTo("hello"));
    }

    @Test
    public void testPostForm() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("foo", "bar")
                .when()
                .post(BASE_URL + "/post")
                .then()
                .statusCode(200)
                .body("form.foo", equalTo("bar"));
    }

    @Test
    public void testPut() {
        given()
                .contentType("application/json; charset=utf-8")
                .body("{\"key\":\"value\"}")
                .when()
                .put(BASE_URL + "/put")
                .then()
                .statusCode(200)
                .body("json.key", equalTo("value"));
    }

    @Test
    public void testPatch() {
        given()
                .contentType("application/json; charset=utf-8")
                .body("{\"key\":\"value\"}")
                .when()
                .patch(BASE_URL + "/patch")
                .then()
                .statusCode(200)
                .body("json.key", equalTo("value"));
    }

    @Test
    public void testDelete() {
        given()
                .contentType("application/json; charset=utf-8")
                .body("{\"key\":\"value\"}")
                .when()
                .delete(BASE_URL + "/delete")
                .then()
                .statusCode(200)
                .body("json.key", equalTo("value"));
    }
}

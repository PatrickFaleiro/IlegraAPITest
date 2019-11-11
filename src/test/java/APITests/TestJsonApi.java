package APITests;

import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJsonApi {

    private static final String urlBaseGetPost = "https://jsonplaceholder.typicode.com/posts";
    private static final String urlBasePatchDelete = "https://jsonplaceholder.typicode.com/posts/1";

 

    @Test
    public void getValues(){
        given()
                .relaxedHTTPSValidation()
                .param("userId","1")
                .param("id","10")
        .when()
                .get(urlBaseGetPost)
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(CoreMatchers.containsString("accusamus veritatis"))
                .log().ifStatusCodeIsEqualTo(200)
                .log().ifError();;

    }

    @Test
    public void createValues(){
        String payload = "title: 'teste post'," +
                    "     body: 'teste post'," +
                    "     userId: 1";

        given().relaxedHTTPSValidation()
                .body(payload)
        .when()
                .post(urlBaseGetPost)
        .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .log().ifStatusCodeIsEqualTo(201);

    }

    @Test
    public void updateValue(){
       String payload = "title: 'teste post1'," +
                    "     body: 'teste post1',";

        given().relaxedHTTPSValidation()
                .body(payload)
        .when()
                .patch(urlBasePatchDelete)
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().ifStatusCodeIsEqualTo(200);
    }

    @Test
    public void deleteValue(){
        given().relaxedHTTPSValidation()
                .param("userId","1")
                .param("id","1")
        .when()
                .delete(urlBasePatchDelete)
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().ifError();

    }

}

package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class APITest2 {

    @Test
    public void DeletePlaceID() {

        baseURI= "https://rahulshettyacademy.com/";
        String responseData = given().log().all()
                .queryParam("place_id", "4b0e533539ebdef6a748fafd577645d4")
                .header("Content-Type","text/plain")
                .body("{\n" +
                        "\n" +
                        "    \"place_id\":"+"4b0e533539ebdef6a748fafd577645d4"+"\n" +
                        "}\n").
                when().put("maps/api/place/delete/json").
                then()


                .log().all().
                assertThat().statusCode(200)
                .extract().asString()
                ;

        System.out.println("response is "+ responseData);

        JsonPath json = new JsonPath(responseData);
    }

    @Test
    public void getUsers() {
        RestAssured.baseURI = "https://reqres.in/";
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", equalTo(2));
    }

}

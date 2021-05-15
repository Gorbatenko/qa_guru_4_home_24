package team.dataart.tests;

import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import team.dataart.utils.JiraIssue;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.parameter;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static team.dataart.api.LogFilter.filters;

@Tag("api")
@JiraIssue("QC3-39")
@Feature("Vacancy api tests")
public class CareerApiTests {

    @BeforeAll
    static void configureBaseUrl() {
        RestAssured.baseURI = TestData.getApiUrl();
        RestAssured.registerParser("text/plain", Parser.JSON);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/vacancySectionTags.csv", numLinesToSkip = 1)
    @DisplayName("Successful authorization with set cookie, received by API")
    void checkTechnologyTitles(String path, String value) {
        parameter("path", path);
        parameter("value", value);

        Object response = given()
                .filter(filters().withCustomTemplates())
                .log().uri()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("section", "vacancy")
                .when()
                .get("/ajax/getSectionTags")
                .then()
                .statusCode(200)
                .log().body()
                .extract()
                .body()
                .jsonPath()
                .get(path);

        assertThat(response.toString()).contains(value);
    }
}

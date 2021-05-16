package team.dataart.tests;

import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import team.dataart.utils.JiraIssue;

import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.SeverityLevel.MINOR;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static team.dataart.api.LogFilter.filters;

@Tag("api")
@JiraIssue("QC3-39")
@Owner("GorbatenkoVA")
@Feature("Vacancy api tests")
public class CareerApiTests {

    @BeforeAll
    static void configureBaseUrl() {
        RestAssured.baseURI = TestData.getApiUrl();
        RestAssured.registerParser("text/plain", Parser.JSON);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/vacancySectionTags.csv", numLinesToSkip = 1)
    @DisplayName("getSectionTags contains Technology")
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

    @Test
    @AllureId("2804")
    @Severity(MINOR)
    @DisplayName("getSectionTags contains Location")
    void checkLocationTitles() {

    }
}

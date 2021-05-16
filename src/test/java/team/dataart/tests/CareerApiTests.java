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
import team.dataart.annotations.JiraIssue;

import java.util.List;

import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;
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

        step("Check that Technology contains " + value, () -> {
            assertThat(response.toString()).contains(value);
        });
    }

    @Test
    @AllureId("2804")
    @Severity(MINOR)
    @DisplayName("GetCmsNews contains Location")
    void checkCmsNewsTitles() {
        List<String> response = given()
                .filter(filters().withCustomTemplates())
                .log().uri()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("count", "2")
                .when()
                .get("/ajax/GetCmsNews")
                .then()
                .statusCode(200)
                .log().body()
                .extract()
                .body()
                .jsonPath()
                .get("items.title");

        step("Check items size", () -> {
            assertThat(response).hasSize(2);
        });
        step("Check that titles not empty", () -> {
            assertThat(response).isNotEmpty();
        });
    }

    @Test
    @Severity(MINOR)
    @DisplayName("GetCompanyFacts contains 3 facts")
    void checkCompanyFactsTitles() {
        List<String> response = given()
                .filter(filters().withCustomTemplates())
                .log().uri()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .when()
                .get("/ajax/GetCompanyFacts")
                .then()
                .statusCode(200)
                .log().body()
                .extract()
                .body()
                .jsonPath()
                .get("items.factHeader");

        step("Check items size", () -> {
            assertThat(response).hasSize(3);
        });
        step("Check titles", () -> {
            assertThat(response).contains("Общее количество специалистов DataArt");
            assertThat(response).contains("Составил оборот DataArt в 2020 году");
            assertThat(response).contains("Реализованных проектов");
        });
    }
}

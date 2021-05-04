package team.dataart.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static team.dataart.tests.TestData.getWebUrl;

@Feature("Main page tests")
@Owner("GorbatenkoVA")
public class MainPageTests extends TestBase {

    @Test
    @DisplayName("Filter vacancies by technology is work")
    void checkTechnologyFilter() {
        step("Open url " + getWebUrl(), () -> {
            open(getWebUrl());
        });

        step("Select technology", () -> {
            // todo
        });

        step("Check vacancy technology", () -> {
            // todo
        });
    }

    @Test
    @DisplayName("Filter vacancies by industry is work")
    void checkIndustryFilter() {
        step("Open url " + getWebUrl(), () -> {
            open(getWebUrl());
        });

        step("Select industry", () -> {
            // todo
        });

        step("Check vacancy industry", () -> {
            // todo
        });
    }

    @Test
    @DisplayName("Filter vacancies by location is work")
    void checkLocationFilter() {
        step("Open url " + getWebUrl(), () -> {
            open(getWebUrl());
        });

        step("Select location", () -> {
            // todo
        });

        step("Check vacancy location", () -> {
            // todo
        });
    }

    @Test
    @DisplayName("Filter vacancies by location is work")
    void checkThatEmptyVacanciesListHasDefaultText() {
        step("Open url " + getWebUrl(), () -> {
            open(getWebUrl());
        });

        step("Select technology", () -> {
            // todo QA
        });

        step("Select industry", () -> {
            // todo medicine
        });

        step("Select location", () -> {
            // todo Lublin
        });

        step("Check default text", () -> {
            // todo
        });
    }
}
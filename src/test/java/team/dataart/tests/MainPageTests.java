package team.dataart.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static team.dataart.tests.TestData.getWebUrl;
import static team.dataart.utils.Randomizer.randomValueFromVariant;

@Tag("ui")
@Owner("GorbatenkoVA")
@Feature("Main page tests")
public class MainPageTests extends TestBase {

    @BeforeEach
    void openUrl() {
        step("Open url " + getWebUrl(), () -> {
            open(getWebUrl());
        });
    }

    @Test
    @DisplayName("Filter vacancies by technology is work")
    void checkTechnologyFilter() {
        String technology = randomValueFromVariant(".Net", "QA", "JavaScript", "Java", "DevOps", "Ruby");

        step("Select technology", () -> {
            $("#customSelect__technology").click();
            $$(".isOpen .customSelect__option").findBy(text(technology)).click();
        });

        step("Check vacancy technology", () -> {
            ElementsCollection allVacancies = $$(".vacanciesWidget__list-item");
            allVacancies.filter(text(technology)).shouldHaveSize(allVacancies.size());
        });
    }

    @Test
    @DisplayName("Filter vacancies by industry is work")
    void checkIndustryFilter() {
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
        step("Select location", () -> {
            // todo
        });

        step("Check vacancy location", () -> {
            // todo
        });
    }

    @Test
    @DisplayName("Empty vacancies list has default text")
    void checkThatEmptyVacanciesListHasDefaultText() {
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
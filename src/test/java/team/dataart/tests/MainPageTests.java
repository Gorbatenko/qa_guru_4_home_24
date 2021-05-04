package team.dataart.tests;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
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
        String industry = randomValueFromVariant("Интернет вещей", "Медиа и развлечения", "Медицина",
                "Путешествия", "Ритейл", "Финансы");

        step("Select industry", () -> {
            $("#customSelect__industry").click();
            $$(".isOpen .customSelect__option").findBy(text(industry)).click();
        });

        step("Check vacancy industry", () -> {
            ElementsCollection allVacancies = $$(".vacanciesWidget__list-item");
            allVacancies.filter(text(industry)).shouldHaveSize(allVacancies.size());
        });
    }

    @Test
    @DisplayName("Filter vacancies by location is work")
    void checkLocationFilter() {
        String location = randomValueFromVariant("София", "Вроцлав", "Remote.RU", "Remote.UA", "Днепр", "Херсон");

        step("Select location", () -> {
            $("#customSelect__location").click();
            $$(".isOpen .customSelect__option").findBy(text(location)).click();
        });

        step("Check vacancy location", () -> {
            ElementsCollection allVacancies = $$(".vacanciesWidget__list-item");
            allVacancies.filter(text(location)).shouldHaveSize(allVacancies.size());
        });
    }

    @Test
    @DisplayName("Empty vacancies list has default text")
    void checkThatEmptyVacanciesListHasDefaultText() {
        String technology = "QA";
        String industry = "Ставки и игорный бизнес";
        String location = "София";

        step("Select technology", () -> {
            step("Select technology", () -> {
                $("#customSelect__technology").click();
                $$(".isOpen .customSelect__option").findBy(text(technology)).click();
            });
        });

        step("Select industry", () -> {
            $("#customSelect__industry").click();
            $$(".isOpen .customSelect__option").findBy(text(industry)).click();
        });

        step("Select location", () -> {
            $("#customSelect__location").click();
            $$(".isOpen .customSelect__option").findBy(text(location)).click();
        });

        step("Check default text", () -> {
            $(".vacanciesWidget__error").shouldHave(text("По вашему запросу ничего не найдено"));
            $(".vacanciesWidget__error").shouldHave(text("Убедитесь, что все слова написаны без ошибок, или попробуйте использовать другие ключевые слова"));
        });
    }
}
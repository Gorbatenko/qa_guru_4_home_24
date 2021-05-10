package team.dataart.tests;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;
import static team.dataart.tests.TestData.getWebUrl;
import static team.dataart.utils.Randomizer.randomValueFromVariant;

@Tag("ui")
@Owner("GorbatenkoVA")
@Feature("Career page tests")
public class CareerPageTest extends TestBase {

    @BeforeEach
    void openUrl() {
        step("Open url " + getWebUrl() + "/ru/career", () -> {
            open("/ru/career");
        });
    }

    @Test
    @DisplayName("Filter vacancies by relocate is work")
    void checkVacancyRelocateFilter() {
        step("Select relocate checkbox", () -> {
            $("#vacancy_relocate").click();
        });

        step("Check that all vacancies has relocate icons", () -> {
            ElementsCollection allVacancies = $$(".job-card__container");
            ElementsCollection relocateIcons = $$(".vacancy__relocation");
            allVacancies.shouldHaveSize(relocateIcons.size());
        });
    }

    @Test
    @DisplayName("Filter vacancies by remote is work")
    void checkVacancyRemoteFilter() {
        step("Select remote checkbox", () -> {
            $("#vacancy_remote").click();
        });

        step("Check that all vacancies has remote icons", () -> {
            ElementsCollection allVacancies = $$(".job-card__container");
            ElementsCollection remoteIcons = $$(".vacancy__remote");
            allVacancies.shouldHaveSize(remoteIcons.size());
        });
    }
}

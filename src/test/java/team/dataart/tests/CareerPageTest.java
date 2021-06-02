package team.dataart.tests;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static team.dataart.tests.TestData.getWebUrl;

@Tag("ui")
@Issue("QC3-39")
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
    @Severity(CRITICAL)
    @DisplayName("Check that all vacancies has relocate icons")
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
    @Severity(BLOCKER)
    @DisplayName("Check that all vacancies has remote icons")
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

    @Test
    @Severity(BLOCKER)
    @DisplayName("When unselect filter vacancies list autoUpdated")
    void checkWhenFilterUncheckedThenVacanciesAutoupdated() {
        String checkedSpecialisation = "QA";
        String uncheckedSpecialisation = "Java";

        step("Open specialisation menu", () -> {
            $("#vacancy_specialisation").click();
            step("Select specialisation '" + uncheckedSpecialisation + "'", () -> {
                $$("#vacancy_specialisation span").findBy(text(uncheckedSpecialisation)).click();
            });
            step("Select specialisation '" + checkedSpecialisation + "'", () -> {
                $$("#vacancy_specialisation span").findBy(text(checkedSpecialisation)).click();
            });
        });

        step("Unselect specialisation '" + uncheckedSpecialisation + "'", () -> {
            $$(".scrollbar-extract li").filter(text(uncheckedSpecialisation))
                    .first().$("button").click();
        });

        step("Check that vacancies have not '" + uncheckedSpecialisation + "' tag", () -> {
            $$("job-card__speciality-wrapper").filter(text(uncheckedSpecialisation)).shouldHaveSize(0);
        });
    }
}

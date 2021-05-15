package team.dataart.tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import team.dataart.utils.JiraIssue;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static team.dataart.tests.TestData.getWebUrl;
import static team.dataart.utils.Randomizer.randomValuesFromVariant;

@Tag("ui")
@JiraIssue("QC3-39")
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

    @Test
    @DisplayName("When uncheck filter vacancies list autoupdated")
    void checkWhenFilterUncheckedThenVacanciesAutoupdated() {
        List<String> randomSpecialisations = randomValuesFromVariant(".NET", "QA", "JavaScript", "Java",
                "Design", "DevOps", "Ruby");

        step("Open specialisation menu", () -> {
            $("#vacancy_specialisation").click();

            ElementsCollection snapshot = $$("#vacancy_specialisation span").snapshot();
            for (String specialisation : randomSpecialisations) {
                step("Select specialisation " + specialisation, () -> {
                    snapshot.findBy(text(specialisation)).click();
                });
            }
        });

        String firstSpecialisation = $(".job-card__speciality-wrapper").getText();
        ;

        step("Unselect specialisation: '" + firstSpecialisation + "'", () -> {
            $$(".scrollbar-extract li").filter(text(firstSpecialisation))
                    .first().$("button").click();
        });

        step("Check that vacancies have not '" + firstSpecialisation + "' tag", () -> {
            $$("job-card__speciality-wrapper").filter(text(firstSpecialisation)).shouldHaveSize(0);
        });
    }
}

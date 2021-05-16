package team.dataart.tests;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import team.dataart.utils.JiraIssue;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static team.dataart.tests.TestData.getWebUrl;
import static team.dataart.utils.Randomizer.randomValuesFromVariant;

@Tag("ui")
@JiraIssue("QC3-39")
@Owner("GorbatenkoVA")
@Feature("Career page tests")
public class SimplePageTest extends TestBase {

    @BeforeEach
    void openUrl() {
        $("first").shouldHave(text("hello world"));
    }

    @Test
    @Ignore
    @DisplayName("When filter ignored it cant be checked")
    void oneSimpleIgnoredTest() {

    }

    @Test
    @DisplayName("When test is skip it cant be checked")
    void firstSimpleSkipTest() {
    }

    @Test
    @DisplayName("When test is skip it cant be checked too")
    void secondSimpleSkipTest() {
    }
}

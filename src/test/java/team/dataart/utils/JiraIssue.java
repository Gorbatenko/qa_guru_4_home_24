package team.dataart.utils;

import io.qameta.allure.LabelAnnotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Documented
@Inherited
@Retention(RUNTIME)
@Target({METHOD, TYPE})
@LabelAnnotation(name = "jira")
public @interface JiraIssue {
    String value();
}

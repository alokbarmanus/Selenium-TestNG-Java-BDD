package com.seleniumproject.base;

import com.seleniumproject.context.TestContext;
import com.seleniumproject.pages.DashboardPageObjects;
import com.seleniumproject.pages.LoginPageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

public abstract class BaseStepDefinitions {

    protected TestContext testContext;

    protected WebDriver driver() {
        if (testContext == null) {
            throw new IllegalStateException("TestContext is not initialized in step definition.");
        }
        return testContext.getTestBase().getDriver();
    }

    protected WebDriverWait waitDriver() {
        if (testContext == null) {
            throw new IllegalStateException("TestContext is not initialized in step definition.");
        }
        return testContext.getTestBase().getWait();
    }

    protected String property(String key) {
        if (testContext == null) {
            throw new IllegalStateException("TestContext is not initialized in step definition.");
        }
        return testContext.getTestBase().getProperty(key);
    }

    protected String resolveValue(String value) {
        if (testContext == null) {
            throw new IllegalStateException("TestContext is not initialized in step definition.");
        }
        return testContext.resolveValue(value);
    }

    protected Map<String, String> credentials(String username, String password) {
        return Map.of(
            "username", resolveValue(username),
            "password", resolveValue(password)
        );
    }

    protected LoginPageObjects loginPage() {
        return new LoginPageObjects(driver(), waitDriver());
    }

    protected DashboardPageObjects dashboardPage() {
        return new DashboardPageObjects(driver(), waitDriver());
    }
}

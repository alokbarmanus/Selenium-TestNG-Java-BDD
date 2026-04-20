package com.seleniumproject.steps;

import com.seleniumproject.base.TestBase;
import com.seleniumproject.context.TestContext;
import com.seleniumproject.listeners.ExtentReportListener;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.Map;

public class LoginPageStepDefinitions extends TestBase {

    public LoginPageStepDefinitions(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("I launch the application")
    public void i_launch_the_application() {
        String url = property("app.url");
        ExtentReportListener.logInfo("Launching application URL: " + url);
        testContext.getTestBase().navigateToUrlWithRetry(url);
        Assert.assertTrue(loginPage().isPageLoaded(), "Login page did not load successfully.");
        ExtentReportListener.logPass("Application launched successfully");
    }

    @When("^user login with ['\"]([^'\"]*)['\"] and ['\"]([^'\"]*)['\"]$")
    public void user_login_with_and(String username, String password) {
        Map<String, String> data = credentials(username, password);
        String resolvedUsername = data.get("username");
        String resolvedPassword = data.get("password");
        ExtentReportListener.logInfo("Logging in with resolved test data");
        loginPage().enterUsername(resolvedUsername);
        loginPage().enterPassword(resolvedPassword);
        Assert.assertEquals(loginPage().getEnteredUsername(), resolvedUsername,
            "Username value is not entered correctly.");
        Assert.assertEquals(loginPage().getEnteredPassword(), resolvedPassword,
            "Password value is not entered correctly.");
        ExtentReportListener.logPass("Username and password entered and validated");
        loginPage().clickOnLoginButton();
        Assert.assertTrue(dashboardPage().waitForDashboardPage(), "Dashboard page did not load after login.");
    }

    @When("^user login with ['\"]([^'\"]*)['\"] into application$")
    public void user_login_with_into_application(Map<String, String> data) {
	    String resolvedUsername = data.get("username");
        String resolvedPassword = data.get("password");
        System.out.println("Login data: " + resolvedUsername + ", " + resolvedPassword);
        loginPage().enterUsername(resolvedUsername);
        loginPage().enterPassword(resolvedPassword);
        loginPage().clickOnLoginButton();
        Assert.assertTrue(dashboardPage().waitForDashboardPage(), "Dashboard page did not load after login.");
    }
    
    //Extra step, not used yet
    @When("^user enter address info using ['\"]([^'\"]*)['\"] in the form$")
    public void user_enter_address_info_using_in_the_form(Map<String, String> address) {
        ExtentReportListener.logInfo("Entering address data from nested JSON map");
        System.out.println("Address info: " + address.get("street") + ", " + address.get("city") + ", " + address.get("state") + " " + address.get("zip"));
        ExtentReportListener.logInfo("Address used: " + address.get("street") + ", "
            + address.get("city") + ", " + address.get("state") + " " + address.get("zip"));
    }

    
}
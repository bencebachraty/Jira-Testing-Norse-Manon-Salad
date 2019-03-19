package com.codecool.testautomation.NorseManonSalad.features;

import com.codecool.testautomation.NorseManonSalad.TestUtils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login extends Feature {

    @FindBy(id = "login-form-username")
    WebElement userNameField;

    @FindBy(id = "login-form-password")
    WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"header-details-user-fullname\"]//img")
    WebElement userProfilePicture;

    @FindBy(id = "login")
    WebElement loginSubmitButton;

    final String USER_NAME = System.getenv("USER_NAME");
    final String PASSWORD = System.getenv("PASSWORD");

    protected Login(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    void login(String userName, String password) {
        driver.get(Utils.BASE_URL);
        userNameField.sendKeys(userName);
        passwordField.sendKeys(password);
        passwordField.submit();
    }

    void loginSuccessful() {
        login(USER_NAME, PASSWORD);
    }

    String validateSuccessfulLogin() {
        waitUntilElementLoaded(userProfilePicture);
        String profilePictureAltString = userProfilePicture.getAttribute("alt");
        return profilePictureAltString;
    }

    boolean validateErrorMessage(String errorId) {
        waitUntilElementLoaded(loginSubmitButton);
        WebElement errorMessage = driver.findElement(By.id(errorId));
        boolean isPresentErrorMessage = errorMessage.isDisplayed();
        return isPresentErrorMessage;
    }
}

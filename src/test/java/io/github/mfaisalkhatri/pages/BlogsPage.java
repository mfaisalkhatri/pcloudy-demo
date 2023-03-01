package io.github.mfaisalkhatri.pages;

import static io.github.mfaisalkhatri.drivers.DriverManager.getDriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Faisal Khatri
 * @since 11/2/2022
 **/
public class BlogsPage {

    private WebElement pageTitle () {
        WebDriverWait wait = new WebDriverWait (getDriver (), Duration.ofSeconds (10));
        return wait.until (ExpectedConditions.visibilityOfElementLocated (By.cssSelector ("section.browser_Combination .pl-reads > div.pick_features")));
    }

    public String getPageTitle () {
        return pageTitle ().getText ();
    }
}

package io.github.mfaisalkhatri.pages;

import static io.github.mfaisalkhatri.drivers.DriverManager.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * @author Faisal Khatri
 * @since 11/2/2022
 **/
public class DocumentationPage {

    private void openManualAppTestingMenu () {
        WebElement manualAppTestingLink = getDriver ().findElement (By.linkText ("Manual App Testing"));
        WebElement webTesting = getDriver ().findElement (By.linkText ("Web Testing"));

        Actions actions = new Actions (getDriver ());
        actions.moveToElement (manualAppTestingLink)
            .click ()
            .moveToElement (webTesting)
            .click ();

    }

    private WebElement webTestingPageTitle () {
        return getDriver ().findElement (By.cssSelector ("#explore-full-pcloudy-platform > h2"));

    }

    public String getWebTestingPageTitle () {
        openManualAppTestingMenu ();
        return webTestingPageTitle ().getText ();
    }
}

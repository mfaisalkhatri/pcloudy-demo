package io.github.mfaisalkhatri.pages;

import static io.github.mfaisalkhatri.drivers.DriverManager.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Faisal Khatri
 * @since 10/31/2022
 **/
public class HomePage {

    public String getMainTitle () {
        return getDriver ().findElement (By.tagName ("h1"))
            .getText ();
    }
}

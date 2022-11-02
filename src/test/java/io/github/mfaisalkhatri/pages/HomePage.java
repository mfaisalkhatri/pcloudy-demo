package io.github.mfaisalkhatri.pages;

import static io.github.mfaisalkhatri.drivers.DriverManager.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * @author Faisal Khatri
 * @since 10/31/2022
 **/
public class HomePage {

    private WebElement closeBtnOfBottomleftPopUp () {
        return getDriver ().findElement (By.cssSelector ("div.bottom-left-popup > a"));

    }

    public void closeLeftPopup () {
        closeBtnOfBottomleftPopUp ().click ();
    }

    public String getTopText () {
        return getDriver ().findElement (By.cssSelector ("div.info.text-center > h1"))
            .getText ();
    }

    public String getCenterText () {
        return getDriver ().findElement (By.cssSelector ("div.info.text-center > h2"))
            .getText ();
    }

    private WebElement resourcesMenu () {
        return getDriver ().findElement (By.linkText ("Resources"));
    }

    private WebElement blogsLink () {
        return getDriver ().findElement (By.linkText ("Blog"));

    }

    public BlogsPage openBlogsPage () {
        Actions actions = new Actions (getDriver ());
        actions.moveToElement (resourcesMenu ())
            .click ()
            .build ()
            .perform ();
        blogsLink ().click ();
        return new BlogsPage ();
    }
}

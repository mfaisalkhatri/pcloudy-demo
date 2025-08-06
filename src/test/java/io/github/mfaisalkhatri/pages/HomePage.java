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

    public void closeLeftPopup () {
        closeBtnOfBottomleftPopUp ().click ();
    }

    public String getCenterText () {
        return getDriver ().findElement (By.cssSelector (".img-Txt h2"))
            .getText ();
    }

    public String getTopText () {
        return getDriver ().findElement (By.cssSelector ("div.info.text-center > h1"))
            .getText ();
    }

    public BlogsPage openBlogsPage () {
        Actions actions = new Actions (getDriver ());
        actions.moveToElement (resourcesMenu ())
            .pause (1000)
            .click ()
            .build ()
            .perform ();
        blogsLink ().click ();
        return new BlogsPage ();
    }

    private WebElement blogsLink () {
        return getDriver ().findElement (
            By.cssSelector ("#resource_menu2 > div > div.container > div > div:nth-child(1) > div > a:nth-child(1)"));

    }

    private WebElement closeBtnOfBottomleftPopUp () {
        return getDriver ().findElement (By.cssSelector ("div.bottom-left-popup > a"));

    }

    private WebElement resourcesMenu () {
        return getDriver ().findElement (By.linkText ("Resources"));
    }
}
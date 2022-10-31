package io.github.mfaisalkhatri.tests;

import static io.github.mfaisalkhatri.drivers.DriverManager.getDriver;
import static io.github.mfaisalkhatri.utilities.Helper.takeScreenShot;

import io.github.mfaisalkhatri.pages.HomePage;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Faisal Khatri
 * @since 10/27/2022
 **/
public class PcloudyWebsiteTests extends BaseTest {

    @BeforeClass
    public void testSetup () {
        String website = "https://www.pcloudy.com/";
        getDriver ().get (website);
    }

    @Test
    public void testPrintTitle () {
        HomePage homePage = new HomePage ();
        takeScreenShot ();
        System.out.println (homePage.getMainTitle ());
    }
}

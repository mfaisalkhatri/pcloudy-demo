package io.github.mfaisalkhatri.tests;

import static io.github.mfaisalkhatri.drivers.DriverManager.getDriver;

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
    public void testChecktitle () {
        String title = getDriver ().findElement (By.tagName ("h1"))
            .getText ();
        System.out.println (title);
    }
}

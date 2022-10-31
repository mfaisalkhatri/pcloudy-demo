package io.github.mfaisalkhatri.tests;

import static io.github.mfaisalkhatri.drivers.DriverManager.createDriver;
import static io.github.mfaisalkhatri.drivers.DriverManager.quitDriver;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

/**
 * @author Faisal Khatri
 * @since 10/27/2022
 **/
public class BaseTest {

    @Parameters ({"os","osVersion","browserName","browserVersion"})
    @BeforeClass (alwaysRun = true)
    public void setupTest (final String os, final String osVersion, final String browserName, final String browserVersion) {
        createDriver (os, osVersion, browserName, browserVersion);
    }

    @AfterClass (alwaysRun = true)
    public void tearDown () {
        quitDriver ();
    }
}

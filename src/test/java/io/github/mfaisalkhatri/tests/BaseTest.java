package io.github.mfaisalkhatri.tests;

import static io.github.mfaisalkhatri.drivers.DriverManager.createDriver;
import static io.github.mfaisalkhatri.drivers.DriverManager.quitDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

/**
 * @author Faisal Khatri
 * @since 10/27/2022
 **/
public class BaseTest {

    @Parameters ({"OS","browser","browserVersion"})
    @BeforeSuite (alwaysRun = true)
    public void setupTest (final String OS,final String browserName, final String browserVersion) {
        createDriver (OS, browserName, browserVersion);
    }

    @AfterSuite (alwaysRun = true)
    public void tearDown () {
        quitDriver ();
    }
}

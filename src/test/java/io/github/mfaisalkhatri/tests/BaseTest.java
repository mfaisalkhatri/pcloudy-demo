package io.github.mfaisalkhatri.tests;

import static io.github.mfaisalkhatri.drivers.DriverManager.quitDriver;
import static io.github.mfaisalkhatri.drivers.DriverManager.startDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

/**
 * @author Faisal Khatri
 * @since 10/27/2022
 **/
public class BaseTest {

    @Parameters ({ "id" })
    @BeforeClass (alwaysRun = true)
    public void setupTest (final String id) {
        startDriver (id);
    }

    @AfterClass (alwaysRun = true)
    public void tearDown () {
        quitDriver ();
    }
}

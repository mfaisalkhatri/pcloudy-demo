package io.github.mfaisalkhatri.drivers;

import java.net.MalformedURLException;
import java.net.URL;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

/**
 * @author Faisal Khatri
 * @since 10/27/2022
 **/
public class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER           = new ThreadLocal<> ();
    private static final Logger                 LOG              = LogManager.getLogger ("DriverManager.class");
    private static final String                 GRID_URL         = "https://prod-browsercloud-in.pcloudy.com/seleniumcloud/wd/hub";
    private static final String                 PCLOUDY_USERNAME = System.getProperty ("username");
    private static final String                 PCLOUDY_APIKEY   = System.getProperty ("apikey");

    public static void createDriver (final String os, String osVersion, final String browserName,
        final String browserVersion) {

        Capabilities options;

        if (browserName.equalsIgnoreCase ("chrome")) {
            options = getChromeOptions (os, osVersion, browserVersion);
        } else if (browserName.equalsIgnoreCase ("firefox")) {
            options = getFirefoxOptions (os, osVersion, browserVersion);
        } else if (browserName.equalsIgnoreCase ("safari")) {
            options = getSafariOptions (os, osVersion, browserVersion);
        } else {
            throw new Error ("Browser name/version is not specified correctly!!");
        }

        try {
            setDriver (new RemoteWebDriver (new URL (GRID_URL), options));
        } catch (final MalformedURLException e) {
            LOG.error ("Error setting up chrome browser in pCloudy", e);
        }

        setupBrowserTimeouts ();
    }

    @SuppressWarnings ("unchecked")
    public static <D extends WebDriver> D getDriver () {
        return (D) DRIVER.get ();
    }

    public static void quitDriver () {
        if (null != DRIVER.get ()) {
            LOG.info ("Closing the driver...");
            getDriver ().quit ();
            DRIVER.remove ();
        }
    }

    private static void setDriver (final WebDriver driver) {
        DriverManager.DRIVER.set (driver);
    }

    private static void setupBrowserTimeouts () {
        LOG.info ("Setting Browser Timeouts....");
        getDriver ().manage ()
            .timeouts ()
            .implicitlyWait (Duration.ofSeconds (30));
        getDriver ().manage ()
            .timeouts ()
            .pageLoadTimeout (Duration.ofSeconds (30));
        getDriver ().manage ()
            .timeouts ()
            .scriptTimeout (Duration.ofSeconds (30));
    }

    private static Capabilities getFirefoxOptions (final String os, final String osVersion,
        final String browserVersion) {
        final FirefoxOptions firefoxOptions = new FirefoxOptions ();
        firefoxOptions.setCapability ("os", os);
        firefoxOptions.setCapability ("osVersion", osVersion);
        firefoxOptions.setCapability ("browserName", "firefox");
        firefoxOptions.setCapability ("browserVersion", browserVersion);
        firefoxOptions.setCapability ("clientName", PCLOUDY_USERNAME);
        firefoxOptions.setCapability ("apiKey", PCLOUDY_APIKEY);
        firefoxOptions.setCapability ("email", PCLOUDY_USERNAME);
        return firefoxOptions;
    }

    private static Capabilities getSafariOptions (final String os, final String osVersion,
        final String browserVersion) {
        final SafariOptions safariOptions = new SafariOptions ();
        safariOptions.setCapability ("os", os);
        safariOptions.setCapability ("osVersion", osVersion);
        safariOptions.setCapability ("browserName", "safari");
        safariOptions.setCapability ("browserVersion", browserVersion);
        safariOptions.setCapability ("clientName", PCLOUDY_USERNAME);
        safariOptions.setCapability ("apiKey", PCLOUDY_APIKEY);
        safariOptions.setCapability ("email", PCLOUDY_USERNAME);
        return safariOptions;
    }

    private static Capabilities getChromeOptions (String os, String osVersion, String browserVersion) {
        final ChromeOptions chromeOptions = new ChromeOptions ();
        chromeOptions.setCapability ("os", os);
        chromeOptions.setCapability ("osVersion", osVersion);
        chromeOptions.setCapability ("browserName", "chrome");
        chromeOptions.setCapability ("browserVersion", browserVersion);
        chromeOptions.setCapability ("clientName", PCLOUDY_USERNAME);
        chromeOptions.setCapability ("apiKey", PCLOUDY_APIKEY);
        chromeOptions.setCapability ("email", PCLOUDY_USERNAME);
        return chromeOptions;
    }

    private DriverManager () {
    }

}

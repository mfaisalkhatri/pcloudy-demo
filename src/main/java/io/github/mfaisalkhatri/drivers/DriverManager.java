package io.github.mfaisalkhatri.drivers;

import static java.text.MessageFormat.format;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.apache.commons.exec.OS;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

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

    public static void createDriver (final String OS, final String browserName, final String browserVersion) {

        if (browserName.equalsIgnoreCase ("chrome")) {
            setupChromeBrowser (OS, browserVersion);
        } else if (browserName.equalsIgnoreCase ("firefox")) {
            setupFirefoxBrowser (OS, browserVersion);
        } else if (browserName.equalsIgnoreCase ("edge")) {
            setupEdgeBrowser (OS, browserVersion);
        } else {
            LOG.error ("Browser name/version is not specified correctly!!");
        }

        try {
            setDriver (new RemoteWebDriver (new URL (format ("https://{0}", GRID_URL)), capabilities ()));
        } catch (final MalformedURLException e) {
            LOG.error ("Error setting up browser in pCloudy", e);
        }

        setupBrowserTimeouts ();
    }

    public static <D extends WebDriver> D getDriver () {
        return (D) DriverManager.DRIVER.get ();
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

    private static ChromeOptions setupChromeBrowser (String OS, String browserVersion) {
        final ChromeOptions chromeOptions = new ChromeOptions ();
        chromeOptions.setPlatformName (OS);
        chromeOptions.setBrowserVersion (browserVersion);
        return chromeOptions;
    }

    private static FirefoxOptions setupFirefoxBrowser (String OS, String browserVersion) {
        FirefoxOptions firefoxOptions = new FirefoxOptions ();
        firefoxOptions.setPlatformName (OS);
        firefoxOptions.setBrowserVersion (browserVersion);
        return firefoxOptions;
    }

    private static EdgeOptions setupEdgeBrowser (String OS, String browserVersion) {
        EdgeOptions edgeOptions = new EdgeOptions ();
        edgeOptions.setPlatformName (OS);
        edgeOptions.setBrowserVersion (browserVersion);
        return edgeOptions;
    }

    private static DesiredCapabilities capabilities () {
        capabilities ().setCapability ("pCloudy_Username", PCLOUDY_USERNAME);
        capabilities ().setCapability ("pCloudy_ApiKey", PCLOUDY_APIKEY);
        capabilities ().setCapability ("pCloudy_WildNet", "false");
        capabilities ().setCapability ("pCloudy_EnableVideo", "true");
        capabilities ().setCapability ("pCloudy_EnablePerformanceData", "true");
        capabilities ().setCapability ("pCloudy_EnableDeviceLogs", "true");
        return capabilities ();
    }

    private DriverManager () {
    }

}

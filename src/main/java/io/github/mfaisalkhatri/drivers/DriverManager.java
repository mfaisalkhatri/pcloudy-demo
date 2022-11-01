package io.github.mfaisalkhatri.drivers;

import static java.util.Objects.requireNonNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import java.time.Duration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
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

    public static void startDriver (final String id) {

        MutableCapabilities capabilities = new MutableCapabilities ();

        try (
            final var in = new FileInputStream (requireNonNull (DriverManager.class.getClassLoader ()
                .getResource ("parallel.config.json")).getPath ())) {

            final var objectMapper = new ObjectMapper ();
            final JsonNode jsonNode = objectMapper.readValue (in, JsonNode.class);

            final var caps = jsonNode.get ("oSAndBrowserCaps")
                .elements ();
            caps.forEachRemaining (configParam -> {
                if (configParam.get ("id")
                    .asText ()
                    .equals (id)) {
                    configParam.fields ()
                        .forEachRemaining (capability -> {
                            capabilities.setCapability (capability.getKey (), capability.getValue ()
                                .asText ());
                        });
                }
            });
            capabilities.setCapability ("clientName", PCLOUDY_USERNAME);
            capabilities.setCapability ("apiKey", PCLOUDY_APIKEY);
            capabilities.setCapability ("email", PCLOUDY_USERNAME);
            try {
                LOG.info ("setting up capabilities" + capabilities);
                LOG.info ("Starting the Driver..");
                setDriver (new RemoteWebDriver (new URL (GRID_URL), capabilities));
            } catch (final MalformedURLException e) {
                LOG.error ("Error Remote WebDriver...", e);
            }
            setupBrowserTimeouts ();

        } catch (final IOException e) {
            LOG.error ("Error reading the config json file", e);
        }
    }
    private DriverManager () {
    }
}

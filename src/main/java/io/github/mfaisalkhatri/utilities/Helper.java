package io.github.mfaisalkhatri.utilities;

import static io.github.mfaisalkhatri.drivers.DriverManager.getDriver;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 * @author Faisal Khatri
 * @since 10/28/2022
 **/
public class Helper {

    public static void takeScreenShot () {
        File scrFile = ((TakesScreenshot) getDriver ()).getScreenshotAs (OutputType.FILE);
        try {
            DateFormat df = new SimpleDateFormat ("dd_MMM_yyyy_hh_mm_ss");
            String fileName = df.format (new Date ()) + ".png";
            FileUtils.copyFile (scrFile, new File (System.getProperty ("user.dir") + "/screenshots/" + fileName));
        } catch (IOException e) {
            throw new Error ("Error while generating screenshot!", e);
        }
    }
}

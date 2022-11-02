package io.github.mfaisalkhatri.tests;

import static io.github.mfaisalkhatri.drivers.DriverManager.getDriver;
import static io.github.mfaisalkhatri.utilities.Helper.takeScreenShot;
import static org.testng.Assert.assertEquals;

import io.github.mfaisalkhatri.pages.BlogsPage;
import io.github.mfaisalkhatri.pages.HomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Faisal Khatri
 * @since 10/27/2022
 **/
public class PcloudyWebsiteTests extends BaseTest {

    private HomePage homePage;
    @BeforeClass
    public void testSetup () {
        String website = "https://www.pcloudy.com/";
        getDriver ().get (website);
        homePage = new HomePage ();
    }

    @Test
    public void testHomePageText () {
        takeScreenShot ();
        assertEquals (homePage.getTopText (), "Continuous Testing Cloud");
        assertEquals (homePage.getCenterText (), "Deliver Flawless Digital Experience\n" + "through our Device and Browser Cloud");
    }

    @Test
    public void testBlogPageText () {
        BlogsPage blogsPage = homePage.openBlogsPage ();
        takeScreenShot ();
        assertEquals (blogsPage.getPageTitle (), "pCloudy Blogs");
    }
}

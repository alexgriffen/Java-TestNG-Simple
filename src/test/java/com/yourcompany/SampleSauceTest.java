package com.yourcompany;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.UnexpectedException;


public class SampleSauceTest {

    public String buildTag = System.getenv("BUILD_TAG");

    public String username = System.getenv("SAUCE_USERNAME");

    public String accesskey = System.getenv("SAUCE_ACCESS_KEY");

    /**
     * ThreadLocal variable which contains the {@link WebDriver} instance which
     * is used to perform browser interactions with.
     */
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    /**
     * @return the {@link WebDriver} for the current thread
     */
    public WebDriver getWebDriver() {
        return webDriver.get();
    }

    /**
     * DataProvider that explicitly sets the browser combinations to be used.
     *
     * @return Two dimensional array of objects with browser, version, and
     * platform information
     */
    @DataProvider(name = "hardCodedBrowsers", parallel = true)
    public static Object[][] sauceBrowserDataProvider(Method testMethod) {
        return new Object[][]{

                // if (osOption === "edgeIEWindows"){
                // Windows OS
                // new Object[]{"MicrosoftEdge", "latest", "Windows 10"},
                // new Object[]{"MicrosoftEdge", "latest-1", "Windows 10"},
                // new Object[]{"MicrosoftEdge", "latest-1", "Windows 10"},

                // new Object[]{"internet explorer", "latest", "Windows 7"},
                // } else {

                 new Object[]{"firefox", "latest", "Windows 10", "1024x768"},
                 new Object[]{"firefox", "latest-1", "Windows 10", "1024x768"},
                 new Object[]{"firefox", "latest-2", "Windows 10", "1024x768"},

                 new Object[]{"chrome", "latest", "Windows 10", "1024x768"},
                 new Object[]{"chrome", "latest-1", "Windows 10", "1024x768"},
                 new Object[]{"chrome", "latest-2", "Windows 10", "1024x768"},


                // Mac OS
//                new Object[]{"chrome", "latest", "OS X 10.14"},
//                new Object[]{"chrome", "latest-1", "OS X 10.14"},
//                new Object[]{"chrome", "latest-2", "OS X 10.14"},
//
//                new Object[]{"firefox", "latest", "OS X 10.14"},
//                new Object[]{"firefox", "latest-1", "OS X 10.14"},
//                new Object[]{"firefox", "latest-2", "OS X 10.14"},

                // new Object[]{"safari", "12.0", "OS X 10.14"},
                //
                // new Object[]{"safari", "12.1", "OS X 10.13"},
                // new Object[]{"safari", "11.1", "OS X 10.13"},
                //
                // new Object[]{"safari", "11.0", "OS X 10.12"},
                // new Object[]{"safari", "10.1", "OS X 10.12"},
                //
                // new Object[]{"safari", "10.0", "OS X 10.11"},
                // new Object[]{"safari", "9.0", "OS X 10.11"},

                /**
                 *** use these when running headless
                 **/

                // new Object[]{"firefox", "latest", "Linux"},
                // new Object[]{"firefox", "latest-1", "Linux"},
                // new Object[]{"firefox", "latest-2", "Linux"},
                // new Object[]{"chrome", "latest", "Linux"},
                // new Object[]{"chrome", "latest-1", "Linux"},
                // new Object[]{"chrome", "latest-2", "Linux"},
        };
    }

    /**
     * Constructs a new {@link RemoteWebDriver} instance which is configured to
     * use the capabilities defined by the browser, version and os parameters,
     * and which is configured to run against ondemand.saucelabs.com, using the
     * username and access key populated by the {@link #authentication}
     * instance.
     *
     * @param browser    Represents the browser to be used as part of the test run.
     * @param version    Represents the version of the browser to be used as part
     *                   of the test run.
     * @param os         Represents the operating system to be used as part of the test
     *                   run.
     * @param rez        Represents the screen resolution
     * @return
     * @throws MalformedURLException if an error occurs parsing the url
     */
    protected void createDriver(String browser, String version, String os, String rez, String methodName)
            throws MalformedURLException, UnexpectedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // set desired capabilities to launch appropriate browser on Sauce
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        capabilities.setCapability(CapabilityType.VERSION, version);
        capabilities.setCapability(CapabilityType.PLATFORM, os);
        capabilities.setCapability("screenResolution", rez);
        capabilities.setCapability("name", methodName);


        //Getting the build name.
        // Using the Jenkins ENV var. You can use your own. If it is not set test will run without a build id.
        if (buildTag != null) {
            capabilities.setCapability("build", buildTag);
        }


        // Launch remote browser and set it as the current thread
        webDriver.set(new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + "@ondemand.saucelabs.com/wd/hub"), capabilities));

    }

    @Test(dataProvider = "hardCodedBrowsers")
    public void LoadTestPage(String browser, String version, String os, String rez, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException, InterruptedException {

        //create webdriver session
        this.createDriver(browser, version, os, rez, method.getName());
        WebDriver driver = this.getWebDriver();

        driver.get("https://saucelabs.com"); //update this link to whichever site you'd like to take screenshots of
        driver.get("https://yahoo.com");

    }

    /**
     * Method that gets invoked after test. Dumps browser log and Closes the
     * browser
     */
    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
        ((JavascriptExecutor) webDriver.get()).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed")); //sauce:context
        webDriver.get().quit();
    }

    protected void annotate(String text) {
        ((JavascriptExecutor) webDriver.get()).executeScript("sauce:context=" + text);
    }
}



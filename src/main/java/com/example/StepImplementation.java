package com.example;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.DataStoreFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StepImplementation {

    private static final Logger logger = LoggerFactory.getLogger(StepImplementation.class);

    public StepImplementation() {
        logger.info("Creating new StepImplementation...");
    }

    @Step("Go to Gauge Get Started Page")
    public void gotoGetStartedPage() throws InterruptedException {
        logger.info("in gotoGetStartedPage");

        WebElement getStartedButton = getWebDriver().findElement(By.xpath("//a[@class='link-get-started']"));
        getStartedButton.click();

        // outputs extra text added to the report for this step
        Gauge.writeMessage("Page title is %s", getWebDriver().getTitle());
    }

    @Step("Ensure installation instructions are available")
    public void ensureInstallationInstructionsAreAvailable() throws InterruptedException {
        logger.info("in ensureInstallationInstructionsAreAvailable");

        WebElement instructions = getWebDriver().findElement(By.xpath("//p[@class='instruction']"));
        assertTrue(instructions != null);
    }

    @Step("Open the <title> homepage")
    public void implementation1(String title) {
        logger.info("in implementation1");

        String app_url = System.getenv("APP_URL");
        getWebDriver().get(app_url + "/");

        assertTrue(getWebDriver().getTitle().contains(title));
    }

    @Step("Another parameterised step def <count> <flag>")
    public void anotherStepDef(int count, boolean flag) {
        logger.info("In anotherStepDef, count is {}, flag is {}", count, flag);
        assertEquals("wrong value", 42, count);
        assertEquals("wrong value", true, flag);
    }

    private WebDriver getWebDriver() {
        return (WebDriver) DataStoreFactory.getSpecDataStore().get("webdriver");
    }
}

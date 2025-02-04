package scripts;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.Select; /*Unused Package*/
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FlightBookingTest {
	//Creating the constructor of SignInTest class.
	SignInTest signin = new SignInTest();
	//Only initializing the Webdriver.
    WebDriver driver;// = new ChromeDriver(); 

    @Test
    public void testThatResultsAppearForAOneWayJourney() {
    	//Accessing the setDriverPath() method from SignInTest class.
        signin.setDriverPath();
        //Declaring the webdriver after setting the Chrome driver. 
        driver = new ChromeDriver();
        driver.get("https://www.cleartrip.com/");
        waitFor(2000);
        BookingFlight();
        waitFor(5000);
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.className("searchSummary")));
        //close the browser
        driver.quit();
    }
    
    private void BookingFlight() {
    	driver.findElement(By.id("OneWay")).click();
        driver.findElement(By.id("FromTag")).clear();
        driver.findElement(By.id("FromTag")).sendKeys("Bangalore");
        //wait for the auto complete options to appear for the origin
        waitFor(2000);
        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        originOptions.get(0).click();
        waitFor(2000);
        //Changed "toTag" to "ToTag".
        driver.findElement(By.id("ToTag")).clear(); 
        //Changed "toTag" to "ToTag".
        driver.findElement(By.id("ToTag")).sendKeys("Delhi");
        //wait for the auto complete options to appear for the destination
        waitFor(2000);
        //select the first item from the destination auto complete list
        List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
        destinationOptions.get(0).click();
        waitFor(2000);
        //Changing to the value of xpath to valid date.
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[5]/td[7]/a")).click();
        //all fields filled in. Now click on search
        driver.findElement(By.id("SearchBtn")).click();		
	}

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}

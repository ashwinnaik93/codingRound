package scripts;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HotelBookingTest {

	//Only initializing the Webdriver.
    WebDriver driver;
    //Creating the constructor of SignInTest class.
    SignInTest signin = new SignInTest();
    
    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

    @Test
    public void shouldBeAbleToSearchForHotels() {
    	//Accessing the setDriverPath() method from SignInTest class.
        signin.setDriverPath(); 
        //Declaring the webdriver after setting the Chrome driver.
        driver = new ChromeDriver();   
        driver.get("https://www.cleartrip.com/");
        //Using initElements method to initialize web elements.
        PageFactory.initElements(driver, this);
        hotelLink.click();
        localityTextBox.sendKeys("Indiranagar, Bangalore");
        //Select the first item from the destination auto complete list.
        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li")); 
        //Changing the Index number to 1 to take a click on the option.
        originOptions.get(1).click(); 
        //Selecting the Checkin date.
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[5]/td[2]/a")).click();
        //Tapping on checkout date picker.
        driver.findElement(By.id("CheckOutDate")).click();
        //Selecting the Checkout date.
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[5]/td[7]/a")).click();
        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        searchButton.click();
        driver.quit();
    }
}

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.ReadConfig;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertTrue;

public class Steps {
    ReadConfig readconfig=new ReadConfig();
    public String baseURL=readconfig.getApplicationURL();
    public String macBookAmount=readconfig.getMacBookAmount();
    private WebDriver driver;
    private By orderStatusAlert= By.xpath("/html/body/div[10]/p");
    private By orderID= By.xpath("/html/body/div[10]/p/text()[1]");
    private By amount = By.xpath("/html/body/div[10]/p/text()[2]");
    private By name = By.xpath("/html/body/div[10]/p/text()[4]");
    public String product_price= "";

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get(baseURL);
    }
    @Given("I am on the dashboard page of the DemoBlaze application")
    public void iAmOnTheDashboardPageOfTheDemoBlazeApplication() throws InterruptedException {
        driver.manage().window().maximize();
        Thread.sleep(5000);
    }

    @After
    public void quitBrowser(){
        driver.quit();
    }


    @When("I place an order after entering user's {string},{string}, {string}, {string},{string}, and {string}")
    public void i_place_an_order_after_entering_user_s_and(String name, String country,
                                                           String city, String creditCardNumber, String month,
                                                           String year) throws InterruptedException {
        driver.findElement(By.linkText("Laptops")).click();
        Thread.sleep(5000);
        driver.findElement(By.linkText("MacBook Pro")).click();
        Thread.sleep(5000);
        driver.findElement(By.linkText("Add to cart")).click();
        Thread.sleep(5000);
        driver.switchTo().alert().accept();
        Thread.sleep(5000);
        driver.findElement(By.id("cartur")).click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector(".btn-success")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("country")).click();
        driver.findElement(By.id("country")).sendKeys(country);
        driver.findElement(By.id("city")).click();
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("card")).click();
        driver.findElement(By.id("card")).sendKeys(creditCardNumber);
        driver.findElement(By.id("month")).click();
        driver.findElement(By.id("month")).sendKeys(month);
        driver.findElement(By.id("year")).click();
        driver.findElement(By.id("year")).sendKeys(year);
        //get product price
        product_price= driver.findElement(By.xpath("//*[@id=\"totalm\"]")).getText();
        driver.findElement(By.cssSelector("#orderModal .btn-primary")).click();
        Thread.sleep(2000);
    }

    @Then("I should see my order details which should include {string}, order id, and amount")
    public void i_should_see_my_order_details_which_should_include_order_id_and_amount(String actual_name) throws InterruptedException {
        Thread.sleep(5000);

        //ID is not null
        assertTrue(getAlertText(orderStatusAlert)!=null, "ID is empty");
        //Amount is the same
        assertTrue(getAlertText(orderStatusAlert).contains("1100"),"amount is not the same");
        //Name is the same
        assertTrue(getAlertText(orderStatusAlert).contains(actual_name), "Name is not the same");


    }

    private String getAlertText(By alertElement){
        return driver.findElement(alertElement).getText();
    }

    @Then("I should get a prompt message that credit card is required.")
    public void iShouldGetAPromptMessageThatCreditCardIsRequired() {
        String error_message=driver.switchTo().alert().getText();
        assertTrue(error_message.contains("Creditcard"));
    }
}

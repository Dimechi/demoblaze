package utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Iterator;
import java.util.List;


public class DropDown {
    private WebDriver driver;

    public DropDown(WebDriver driver){
        this.driver = driver;
    }

    public void selectFromDropDown(By DropDownXpath, String option){
        Select clickThis = new Select(driver.findElement(DropDownXpath));
        clickThis.selectByVisibleText(option);
    }

    public void selectFromKendoDropdown(By DropdownXpath, By optionsXpath, String option){
        WebElement DropDown = driver.findElement(DropdownXpath);
        DropDown.click();
        List<WebElement> options = driver.findElements(optionsXpath);

        for (Iterator<WebElement> iterator = options.iterator(); iterator.hasNext();) {
            WebElement webElement = (WebElement) iterator.next();
            System.out.println(webElement.getText());
            if (webElement.getText().equals(option))
                webElement.click();
        }
    }

}

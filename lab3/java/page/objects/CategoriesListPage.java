package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CategoriesListPage extends BasePage{

    public CategoriesListPage() {
        PageFactory.initElements(driver, this);
    }

    public SearchResultPage clickCategory(String categoryName) {
        String xpath = "//a[contains(@class, 'iaZJx')][contains(text(), '%s')]".formatted(categoryName);
        driver.findElement(By.xpath(xpath)).click();
        return new SearchResultPage();
    }
}

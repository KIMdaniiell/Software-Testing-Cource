package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class SearchResultPage extends BasePage{
    @FindBy (xpath = "//h1[contains(@class, 'rc3E0')]")
    private WebElement searchCategoryTitle;

    @FindBy (xpath = "//*[contains(@class, 'G9iz9')]//a[contains(@class, 'zWnJM J8Lz2 BnXd1')]")
    private List<WebElement> searchResultList;


    public SearchResultPage() {
        PageFactory.initElements(driver, this);
    }


    public String getCategoryTitle() {
        return searchCategoryTitle.getText();
    }

    public QuestionPage clickQuestionFromList (Integer index) {
        this.searchResultList.get(index).click();
        return new QuestionPage();
    }

    public List<String> getQuestionTitles () {
        return this.searchResultList
                .stream()
                .map(WebElement::getText)
                .toList();
    }


}

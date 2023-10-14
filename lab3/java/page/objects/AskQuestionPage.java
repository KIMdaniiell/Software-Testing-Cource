package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.regex.Matcher;

public class AskQuestionPage extends BasePage{
    @FindBy (xpath = "//div[contains(@class, 'FmZAS')]//div[contains(@class, 'NEPL5')]")
    private WebElement pageTitle;

    // Форма
    @FindBy (xpath = "//div[contains(@class, 'FmZAS')]//div[contains(@data-vv-as, '«Тема вопроса»')]//textarea")
    private WebElement titleField;
    @FindBy (xpath = "//div[contains(@class, 'FmZAS')]//div[contains(@data-vv-as, '«Текст вопроса»')]//div[contains(@contenteditable, 'true')]")
    private WebElement textField;
    @FindBy (xpath = "//div[contains(@class, 'FmZAS')]//div[contains(@class, 'wS5Ls NIvsd')]//div[contains(@title, 'Категория')]")
    private WebElement categorySelect;
    @FindBy (xpath = "//div[contains(@class, 'FmZAS')]//div[contains(@class, 'wS5Ls NIvsd')]//div[contains(@title, 'Категория')]//span[contains(@class, 'u2mHx')]")
    private List<WebElement> categoriesList;
    @FindBy (xpath = "//div[contains(@class, 'FmZAS')]//div[contains(@class, 'wS5Ls NIvsd')]//div[contains(@title, 'Подкатегория')]")
    private WebElement subCategorySelect;
    @FindBy (xpath = "//div[contains(@class, 'FmZAS')]//div[contains(@class, 'wS5Ls NIvsd')]//div[contains(@title, 'Подкатегория')]//span[contains(@class, 'u2mHx')]")
    private List<WebElement> subCategoriesList;
    @FindBy (xpath = "//div[contains(@class, 'FmZAS')]//div[contains(@class, 'x9Gdi')]//a")
    private WebElement submitBtn;

    @FindBy (xpath = "//div[contains(@class, 'FmZAS')]//div[contains(@class, 'QnEUz')]")
    private WebElement titleErrorElement;

    @FindBy (xpath = "//div[contains(@class, 'aJogz')]//span[contains(@class, 'rARq0')]")
    private WebElement userActivityPoints;


    public AskQuestionPage() {
        PageFactory.initElements(driver, this);
    }

    // Форма

    public String getPageTitle () {
        return this.pageTitle.getText();
    }

    public AskQuestionPage inputTitle (String title) {
        this.titleField.sendKeys(title);
        return this;
    }

    public String getTitleError() {
        return this.titleErrorElement.getText();
    }

    public AskQuestionPage inputText (String text) {
        this.textField.sendKeys(text);
        return this;
    }

    public AskQuestionPage clickCategorySelect() {
        this.categorySelect.click();
        return this;
    }
    public List<String> getCategoryList() {
        return this.categoriesList
                .stream()
                .map(WebElement::getText)
                .toList();
    }
    public AskQuestionPage clickCategory(String category) {
        this.categoriesList
                .stream()
                .filter(webElement -> webElement.getText().contains(category))
                .toList().get(0).click();
        return this;
    }
    public AskQuestionPage selectCategory (String category) {
        this.clickCategorySelect();

        this.clickCategory(category);

        return this;
    }

    public AskQuestionPage clickSubCategorySelect() {
        this.subCategorySelect.click();
        return this;
    }
    public List<String> getSubCategoryList() {
        return this.subCategoriesList
                .stream()
                .map(WebElement::getText)
                .toList();
    }
    public AskQuestionPage clickSubCategory(String subCategory) {
        this.subCategoriesList
                .stream()
                .filter(webElement -> webElement.getText().contains(subCategory))
                .toList().get(0).click();
        return this;
    }
    public AskQuestionPage selectSubCategory (String subCategory) {
        this.clickSubCategorySelect();

        this.clickSubCategory(subCategory);

        return this;
    }

    public QuestionPage clickSubmitBtn () {
        this.submitBtn.click();
        return new QuestionPage();
    }



    public boolean isSubmitable() {
        return !this.submitBtn
                .getDomAttribute("class")
                .contains("z1pfO");
    }

    // Баллы активности

    public Integer getActivityPoints() {
        String text = this.userActivityPoints.getText();
        return Integer.parseInt(
                text.substring(0, text.indexOf(" "))
        );
    }
}

package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import readProperties.ConfProvider;

import java.util.List;

public class UserPage extends BasePage{
    // Заголовок профиля

    @FindBy (xpath = "//div[contains(@class, 'KDSit')]//div[contains(@class, 'dKYWz Ms8PB')]//span[contains(@class, 'u6pge')]")
    private WebElement userNameElement;

    @FindBy (xpath = "//div[contains(@class, 'Y7dPA')]//div[contains(@class, 'dKYWz')]//strong[contains(@class, 'Jigwg')]")
    private WebElement userActivityPointsElement;

    // Боковая панель

    // Выбор списка для отображения (список вопросов или список ответов)

    @FindBy (xpath = "//div[contains(@class, '_ZqVV')]//span[contains(text(), 'Вопросы')]/parent::*/parent::*")
    private WebElement questionModeBtn;

    @FindBy (xpath = "//div[contains(@class, '_ZqVV')]//span[contains(text(), 'Ответы')]/parent::*/parent::*")
    private WebElement responseModeBtn;

    @FindBy (xpath = "//div[contains(@class, '_ZqVV')]//span[contains(text(), 'Вопросы')]/following-sibling::*")
    private WebElement questionCounterElement;

    @FindBy (xpath = "//div[contains(@class, '_ZqVV')]//span[contains(text(), 'Ответы')]/following-sibling::*")
    private WebElement responseCounterElement;

    // Управление подпиской

    @FindBy (xpath = "//div[contains(@class, '_ZqVV')]//span[contains(text(), 'Подписаться')]/parent::*/parent::*")
    private WebElement subscribeBtn;

    // Панель управления списком вопросов

    @FindBy (xpath = "//div[contains(@class, 'HJkIC')]//li[contains(@class, 'ZGXIl')]//a[contains(text(), 'Все')]")
    private WebElement allQuestionsBtn;

    @FindBy (xpath = "//div[contains(@class, 'HJkIC')]//li[contains(@class, 'ZGXIl')]//a[contains(text(), 'Открытые')]")
    private WebElement openQuestionsBtn;

    @FindBy (xpath = "//div[contains(@class, 'HJkIC')]//li[contains(@class, 'ZGXIl')]//a[contains(text(), 'Решенные')]")
    private WebElement solvedQuestionsBtn;

    @FindBy (xpath = "//div[contains(@class, 'HJkIC')]//li[contains(@class, 'ZGXIl')]//a[contains(text(), 'На голосовании')]")
    private WebElement votedQuestionsBtn;

    // Локатор карточек с вопросами и краткой информацией о них (заголовок, автор, дата, кол-во ответов ...)
    private final By questionCardLocator = By.xpath("//div[contains(@class, 'G9iz9')]");


    public UserPage() {
        PageFactory.initElements(driver, this);
    }


    public UserPage openUserPageBuId (Integer id) {
        driver.get(ConfProvider.USER_LINK + id);
        return this;
    }

    // Заголовок профиля
    public String getUserName() {
        return this.userNameElement.getText();
    }

    public Integer getUserActivityPoints() {
        return Integer.parseInt(this.userActivityPointsElement.getText());
    }

    // Боковая панель
    public UserPage clickQuestionModeBtn() {
        this.questionModeBtn.click();
        return this;
    }

    public UserPage clickResponseModeBtn() {
        this.responseModeBtn.click();
        return this;
    }

    public String getQuestionCounter() {
        return this.questionCounterElement.getText();
    }

    public String getResponseCounter() {
        return this.responseCounterElement.getText();
    }

    // Управление подпиской

    @Deprecated
    public void clickSubscribeBtn() {
        this.subscribeBtn.click();
    }

    // Панель управления списком вопросов
    public UserPage clickAllQuestionsBtn() {
        this.allQuestionsBtn.click();
        return this;
    }

    public UserPage clickOpenQuestionsBtn() {
        this.openQuestionsBtn.click();
        return this;
    }

    public UserPage clickSolvedQuestionsBtn() {
        this.solvedQuestionsBtn.click();
        return this;
    }

    public UserPage clickVotedQuestionsBtn() {
        this.votedQuestionsBtn.click();
        return this;
    }


    // Список вопросов
    private List<UserPage.QuestionCard> getQuestionCards () {
        return driver.findElements(questionCardLocator)
                .stream()
                .map(UserPage.QuestionCard::new)
                .toList();
    }

    public List<String> getQuestionTitleList () {
        return getQuestionCards()
                .stream()
                .map(UserPage.QuestionCard::getTitle)
                .toList();
    }

    public List<String> getQuestionCategoryList () {
        return getQuestionCards()
                .stream()
                .map(UserPage.QuestionCard::getCategory)
                .toList();
    }

    public List<String> getQuestionDateList () {
        return getQuestionCards()
                .stream()
                .map(UserPage.QuestionCard::getDate)
                .toList();
    }

    public List<String> getQuestionResponseCounterList () {
        return getQuestionCards()
                .stream()
                .map(UserPage.QuestionCard::getResponseCounter)
                .toList();
    }

    public String getQuestionTitle (Integer id) {
        return getQuestionCards().get(id).getTitle();
    }

    public String getQuestionCategory (Integer id) {
        return getQuestionCards().get(id).getCategory();
    }

    public String getQuestionDate (Integer id) {
        return getQuestionCards().get(id).getDate();
    }

    public String getQuestionResponseCounter (Integer id) {
        return getQuestionCards().get(id).getResponseCounter();
    }

    public QuestionPage clickQuestionTitle (Integer id) {
        getQuestionCards().get(id).clickTitle();
        return new QuestionPage();
    }

    private static class QuestionCard {
        // //div[contains(@class, 'G9iz9')]
        private final WebElement we;
        private final By header = By.className("CQrPu");
        private final By footer = By.className("fF5MA");
        private final By title = By.className("zWnJM");
        private final By category = By.tagName("a");
        private final By date = By.className("qo4_N");
        private final By responseCounter = By.className("Z3oKe");


        QuestionCard (WebElement we) {
            this.we = we;
        }

        String getTitle () {
            return we.findElement(header).findElement(title).getText();
        }

        String getCategory () {
            // у поля категории нет css классов, поэтому приходится обращаться по тэгу и индексу
            return we.findElement(footer).findElements(category).get(0).getText();
        }

        String getDate () {
            return we.findElement(footer).findElement(date).getText();
        }

        String getResponseCounter () {
            return we.findElement(footer).findElement(responseCounter).getText();
        }


        void clickTitle () {
            we.findElement(header).findElement(title).click();
        }
    }
}

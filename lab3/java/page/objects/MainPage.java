package page.objects;

import com.sun.tools.javac.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;
import readProperties.ConfProvider;

import java.util.List;


public class MainPage extends BasePage{
    // TODO Вынести функционал панели управления в абстрактный класс
    // TODO Сгруппировать элементы


//    @FindBy(xpath = "//*[contains(@data-testid, 'whiteline-account-exit')]//div[contains(text(), 'Выйти')]")
//    private WebElement exitBtn;
//
//    @FindBy(xpath = "//*[contains(@class, 'ph-project__user-name')]")
//    private WebElement profileBtn;
//
//    @FindBy(xpath = "//div[contains(@class, 'login-panel popup')]//input[contains(@name, 'password')]")
//    private WebElement passwordField;
//
//    @FindBy(xpath = "//div[contains(@class, 'login-panel popup')]//span[contains(text(), 'Ввести пароль')]")
//    private WebElement inputPasswordBtn;
//
//    @FindBy(xpath = "//iframe[contains(@class, 'ag-popup__frame__layout__iframe')]")
//    private WebElement loginFrame;
//
//    @FindBy(xpath = "//div[contains(@class, 'login-panel popup')]//div[contains(@class, 'domain-select')]")
//    private WebElement domainSelect;
//
//    @FindBy(xpath = "//div[contains(@class, 'login-panel popup')]//input[contains(@name, 'username')]")
//    private WebElement loginField;

    @FindBy(xpath = "//*[contains(@class, 'iy9DJ')]/span[contains(@class, 'pmWan')][contains(text(), 'Лучшие')]/parent::div")
    private WebElement bestQuestionBtn;

    @FindBy(xpath = "//*[contains(@class, 'iy9DJ')]/span[contains(@class, 'pmWan')][contains(text(), 'На голосовании')]/parent::div")
    private WebElement votedQuestionBtn;

    @FindBy(xpath = "//*[contains(@class, 'iy9DJ')]/span[contains(@class, 'pmWan')][contains(text(), 'Открытые')]/parent::div")
    private WebElement openedQuestionBtn;

//    @FindBy(xpath = "//*[contains(@class, 'qwSX5')]//input")
//    private WebElement searchField;
//
//    @FindBy(xpath = "//*[contains(@class, 'uqCC6')]//*[contains(text(), 'Лидеры')]")
//    private WebElement leaderBtn;
//
//    @FindBy(xpath = "//*[contains(@class, 'uqCC6')]//*[contains(text(), 'Категории')]")
//    private WebElement categoryBtn;
//
//    @FindBy(xpath = "//button[contains(text(), 'Войти')]")
//    private WebElement loginBtn;

    // Локатор карточек с вопросами и краткой информацией о них (заголовок, автор, дата, кол-во ответов ...)
    private final By questionCardLocator = By.xpath("//div[contains(@class, 'wjjeg')]");


    public MainPage() {
        driver.get(ConfProvider.URL);
        PageFactory.initElements(driver, this);
    }


//     Модальные окна

//    public MainPage clickLoginBtn() {
//        this.loginBtn.click();
//        return this;
//    }
//
//    public MainPage clickCategoryBtn() {
//        this.categoryBtn.click();
//        return this;
//    }
//
//    public MainPage clickProfileBtn() {
//        this.profileBtn.click();
//        return this;
//    }
//
//    public MainPage switchToLoginFrame() {
//        driver.switchTo().frame(loginFrame);
//        return this;
//    }
//
//    public MainPage switchToMainFrame() {
//        driver.switchTo().frame(0);
//        return this;
//    }

    // Панель списка вопросов
    public MainPage clickOpenedQuestionBtn() {
        this.openedQuestionBtn.click();
        return this;
    }

    public MainPage clickVotedQuestionBtn() {
        this.votedQuestionBtn.click();
        return this;
    }

    public MainPage clickBestQuestionBtn() {
        this.bestQuestionBtn.click();
        return this;
    }

//  Авторизация

//    public MainPage inputLogin (String userLogin) {
//        this.loginField.sendKeys(userLogin);
//        return this;
//    }
//
//    public MainPage clickDomainSelect() {
//        this.domainSelect.click();
//        return this;
//    }
//
//    public MainPage clickDomain (String domainName) {
//        String xpath = "//span[contains(text(), '%s')]".formatted(domainName);
//        driver.findElement(By.xpath(xpath))
//                .click();
//        return this;
//    }
//
//    public MainPage clickInputPasswordBtn() {
//        this.inputPasswordBtn.click();
//        return this;
//    }
//
//    public MainPage inputPassword (String userLogin) {
//        this.passwordField.sendKeys(userLogin, Keys.ENTER);
//        return this;
//    }
//
//    public MainPage clickExitBtn() {
//        this.exitBtn.click();
//        return this;
//    }
//
//    public LoginPage inputIncorrectPassword (String userLogin) {
//        this.passwordField.sendKeys(userLogin, Keys.ENTER);
//        return new LoginPage();
//    }


//    public MainPage login(String login, String domain, String password) {
//        this.clickLoginBtn()
//                .switchToLoginFrame()
//                .inputLogin(login)
//                .clickDomainSelect()
//                .clickDomain(domain)
//                .clickInputPasswordBtn()
//                .inputPassword(password);
//        return this;
//    }
//
//    public MainPage logout() {
//        this.clickProfileBtn()
//                .clickExitBtn();
//        return this;
//    }

//    // Список лидеров
//    public LeadersListPage clickLeaderBtn() {
//        this.leaderBtn.click();
//        return new LeadersListPage();
//    }
//
//    // Список (полный) категорий
//    public CategoriesListPage goToCategoryPage() {
//        this.clickCategoryBtn();
//
//        String xpath = "//*[contains(@class, 'NmSn4')]//a[contains(text(), 'Полный список')]";
//        driver.findElement(By.xpath(xpath)).click();
//        return new CategoriesListPage();
//    }
//
//    // Поисковая строка
//    public SearchResultPage search (String request) {
//        this.searchField.sendKeys(request, Keys.ENTER);
//        return new SearchResultPage();
//    }
//
//    public SearchResultPage clickCatElemFromShortList(String categoryName) {
//        String xpath = "//*[contains(@class, 'NmSn4')]//a[contains(text(), '%s')]".formatted(categoryName);
//        driver.findElement(By.xpath(xpath)).click();
//        return new SearchResultPage();
//    }

//    // Данные со страницы
//    public String getUserMailAddress() {
//        return profileBtn.getText();
//    }
//
//    public boolean loginBtnIsPresent () {
//        return driver.findElements(
//                By.xpath("//button[contains(text(), 'Войти')]"))
//                .size() > 0;
//    }

    // Список вопросов
    private List<QuestionCard> getQuestionCards () {
        return driver.findElements(questionCardLocator)
                .stream()
                .map(QuestionCard::new)
                .toList();
    }

    public List<String> getQuestionTitleList () {
        return getQuestionCards()
                .stream()
                .map(QuestionCard::getTitle)
                .toList();
    }

    public List<String> getQuestionAuthorList () {
        return getQuestionCards()
                .stream()
                .map(QuestionCard::getAuthor)
                .toList();
    }

    public String getQuestionTitle (Integer id) {
        return getQuestionCards().get(id).getTitle();
    }

    public String getQuestionAuthor(Integer id) {
        return getQuestionCards().get(id).getAuthor();
    }

    public QuestionPage clickQuestion (Integer id) {
        getQuestionCards().get(id).clickTitle();
        return new QuestionPage();
    }

    public UserPage clickQuestionAuthor (Integer id) {
        getQuestionCards().get(id).clickAuthor();
        return new UserPage();
    }

    private static class QuestionCard {
        // div @class = 'wjjeg'
        private final WebElement we;
        private final By author = By.className("sLn02");
        private final By title = By.className("ZePiW");


        QuestionCard (WebElement we) {
            this.we = we;
        }


        String getAuthor () {
            return we.findElement(author).getText();
        }

        String getTitle () {
            return we.findElement(title).getText();
        }

        void clickAuthor () {
            we.findElement(author).click();
        }

        void clickTitle () {
            we.findElement(title).click();
        }
    }



    public MainPage login(String login, String domain, String password) {
        this._login(login, domain, password);
        return this;
    }

    public MainPage logout() {
        this._logout();
        return this;
    }
}



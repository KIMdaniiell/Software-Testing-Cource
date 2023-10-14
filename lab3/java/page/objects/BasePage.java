package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

abstract public class BasePage {
    protected static WebDriver driver;

    private UpperControlPanel upperControlPanel;

    private LowerControlPanel lowerControlPanel;


    public static void setDriver(WebDriver webDriver){
        driver = webDriver;
    }


    private UpperControlPanel getUpperControlPanel() {
        if (null == this.upperControlPanel) {
            this.upperControlPanel = new UpperControlPanel();
        }
        return this.upperControlPanel;
    }

    private LowerControlPanel getLowerControlPanel() {
        if (null == this.lowerControlPanel) {
            this.lowerControlPanel = new LowerControlPanel();
        }
        return this.lowerControlPanel;
    }


    private class UpperControlPanel {
        @FindBy (xpath = "//div[contains(@class, 'ph-whiteline')]//button[contains(text(), 'Войти')]")
        private WebElement loginBtn;
        @FindBy (xpath = "//div[contains(@class, 'ph-whiteline')]//span[contains(@class, 'ph-project__user-name')]")
        private WebElement profileBtn;


        public UpperControlPanel () {
            PageFactory.initElements(driver, this);
        }

        public boolean isLoggedIn () {
            return driver.findElements(
                    By.xpath("//div[contains(@class, 'ph-whiteline')]//button[contains(text(), 'Войти')]")
            ).isEmpty();
        }

        public LoginModalWindow clickLoginBtn() {
            this.loginBtn.click();
            return new LoginModalWindow();
        }

        public boolean isLoginModalWindowVisible() {
            return !driver.findElements(
                    By.xpath("//iframe[contains(@class, 'ag-popup__frame__layout__iframe')]")
            ).isEmpty();
        }

        public ProfileModalWindow clickProfileBtn() {
            this.profileBtn.click();
            return new ProfileModalWindow();
        }

        public String getUserMailAddress() {
            return profileBtn.getText();
        }

        public class LoginModalWindow {
            @FindBy (xpath = "//iframe[contains(@class, 'ag-popup__frame__layout__iframe')]")
            private WebElement frame;


            public LoginModalWindow () {
                PageFactory.initElements(driver, this);
                driver.switchTo().frame(frame);
            }


            public void leaveFrame() {
                driver.switchTo().frame(0);
            }

            public LoginStage getLoginStage() {
                return new LoginStage();
            }

            public class LoginStage  {
                @FindBy(xpath = "//div[contains(@class, 'login-panel popup')]//input[contains(@name, 'username')]")
                private WebElement loginField;
                @FindBy(xpath = "//div[contains(@class, 'login-panel popup')]//div[contains(@class, 'domain-select')]")
                private WebElement domainSelect;
                @FindBy(xpath = "//div[contains(@class, 'login-panel popup')]//span[contains(text(), 'Ввести пароль')]")
                private WebElement passwordStageBtn;
                @FindBy (xpath = "//div[contains(@class, 'login-panel popup')]//div[contains(@class, 'domain-select')]//span[contains(text(), '')]")
                private List<WebElement> domainList;


                public LoginStage () {
                    PageFactory.initElements(driver, this);
                }


                public LoginStage inputLogin (String login) {
                    this.loginField.sendKeys(login);
                    return this;
                }

                public LoginStage clickDomainSelect() {
                    this.domainSelect.click();
                    return this;
                }

                public PasswordStage clickPasswordStageBtn() {
                    this.passwordStageBtn.click();
                    return new PasswordStage();
                }

                public List<String> getDomainList() {
                    return this.domainList
                            .stream()
                            .map(WebElement::getText)
                            .toList();
                }

                public LoginStage selectDomain (String domain) {
                    this.domainList
                            .stream()
                            .filter(webElement -> webElement.getText().equals(domain))
                            .forEach(WebElement::click);
                    return this;
                }
            }

            public class PasswordStage  {
                @FindBy(xpath = "//div[contains(@class, 'login-panel popup')]//input[contains(@name, 'password')]")
                private WebElement passwordField;
                @FindBy (xpath = "//div[contains(@class, 'login-panel popup')]//button[contains(@class, 'base-0-2-79 primary-0-2-93')]")
                private WebElement submitBtn;

                private PasswordStage () {
                    PageFactory.initElements(driver, this);
                }

                public PasswordStage inputPassword (String password) {
                    this.passwordField.sendKeys(password);
                    return this;
                }

                public void clickSubmitBtn() {
                    this.submitBtn.click();
                }
            }
        }

        public class ProfileModalWindow {
            @FindBy(xpath = "//div[contains(@class, 'ph-whiteline')]" +
                    "//div[contains(@data-testid, 'whiteline-account-exit')]" +
                    "//div[contains(text(), 'Выйти')]")
            private WebElement exitBtn;


            public ProfileModalWindow () {
                PageFactory.initElements(driver, this);
            }


            public void clickExitBtn () {
                this.exitBtn.click();
                return ;
            }
        }
    }

    private class LowerControlPanel {
        @FindBy(xpath = "//div[contains(@class, 'header-menu')]//div[contains(@title, 'Ответы Mail.ru')]")
        private WebElement toMainPageBtn;
        @FindBy(xpath = "//div[contains(@class, 'header-menu')]//span[contains(text(), 'Категории')]")
        private WebElement categoriesBtn;
        @FindBy(xpath = "//div[contains(@class, 'header-menu')]//a[contains(text(), 'Спросить')]")
        private WebElement askQuestionBtn;
        @FindBy(xpath = "//div[contains(@class, 'header-menu')]//a[contains(text(), 'Лидеры')]")
        private WebElement toLeaderPageBtn;
        @FindBy(xpath = "//div[contains(@class, 'header-menu')]//input")
        private WebElement searchField;
        @FindBy (xpath = "//div[contains(@class, 'header-menu')]//a[contains(@title, 'Профиль')]")
        private WebElement profileBtn;

        @FindBy (xpath = "//div[contains(@class, 'NmSn4')]//a[contains(text(), '')]")
        private List<WebElement> categoriesList;


        public LowerControlPanel() {
            PageFactory.initElements(driver, this);
        }


        public MainPage clickToMainPageBtn() {
            this.toMainPageBtn.click();
            return new MainPage();
        }

        public void clickCategoriesBtn() {
            this.categoriesBtn.click();
        }

        public AskQuestionPage clickAskQuestionBtn() {
            this.askQuestionBtn.click();
            return new AskQuestionPage();
        }

        public LeadersListPage clickToLeaderPageBtn() {
            this.toLeaderPageBtn.click();
            return new LeadersListPage();
        }

        public SearchResultPage inputSearch (String request) {
            this.searchField.sendKeys(request, Keys.ENTER);
            return new SearchResultPage();
        }

        public UserPage clickProfileBtn() {
            this.profileBtn.click();
            return new UserPage();
        }


        public List<String> getCategoriesShortList () {
            this.clickCategoriesBtn();

            return this.categoriesList
                    .stream()
                    .map(WebElement::getText)
                    .toList();
        }

        public SearchResultPage clickCategory (String category) {
            this.clickCategoriesBtn();

            this.categoriesList
                    .stream()
                    .filter(webElement -> webElement.getText().equals(category))
                    .forEach(WebElement::click);
            return new SearchResultPage();
        }

        public CategoriesListPage goToCategoryPage() {
            this.clickCategoriesBtn();

            this.categoriesList
                    .stream()
                    .filter(webElement -> webElement.getText().equals("Полный список"))
                    .forEach(WebElement::click);

            return new CategoriesListPage();
        }
    }


    // Функционал верхней панели управления
    public boolean isLoggedIn() {
        return this.getUpperControlPanel().isLoggedIn();
    }

    public boolean isLoginModalWindowVisible() {
        return this.getUpperControlPanel()
                .isLoginModalWindowVisible();
    }

    public String getUserMailAddress() {
        return this.getUpperControlPanel().getUserMailAddress();
    }

    protected void _login(String login, String domain, String password) {
        this.getUpperControlPanel()
                .clickLoginBtn()
                .getLoginStage()
                .inputLogin(login)
                .selectDomain(domain)
                .clickPasswordStageBtn()
                .inputPassword(password)
                .clickSubmitBtn();
    }

    protected void _logout() {
        this.getUpperControlPanel()
                .clickProfileBtn()
                .clickExitBtn();
    }

    public LoginPage loginIncorrect(String login, String domain, String password) {
        this._login(login, domain, password);
        return new LoginPage();
    }

    // Функционал нижней панели управления

    public MainPage goToMainPage () {
        return this.getLowerControlPanel()
                .clickToMainPageBtn();
    }

    public SearchResultPage selectCategoryFromShortList (String category) {
        return this.getLowerControlPanel()
                .clickCategory(category);
    }

    public AskQuestionPage clickAskQuestionBtn() {
        return this.getLowerControlPanel()
                .clickAskQuestionBtn();
    }

    public LeadersListPage clickToLeaderPageBtn(){
        return this.getLowerControlPanel()
                .clickToLeaderPageBtn();
    }

    public SearchResultPage search (String request) {
        return this.getLowerControlPanel()
                .inputSearch(request);
    }

    public List<String> getCategoriesShortList() {
        return this.getLowerControlPanel()
                .getCategoriesShortList();
    }

    public CategoriesListPage goToCategoryPage() {
        return this.getLowerControlPanel()
                .goToCategoryPage();
    }

    public UserPage goToUserPage() {
        return this.getLowerControlPanel()
                .clickProfileBtn();
    }
}

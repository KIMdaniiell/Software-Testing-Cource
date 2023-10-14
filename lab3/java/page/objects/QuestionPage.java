package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import readProperties.ConfProvider;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionPage extends BasePage{
    // Список ответов
    @FindBy (xpath = "//div[contains(@class, 'cxc3c')]")
    private List<WebElement> responseCardElementList;

    @Deprecated
    By responseSectionTitlePath = By.className("FbiUg");
    @Deprecated
    By responseTextRelPath = By.className("Xn2FM");


    public QuestionPage() {
        PageFactory.initElements(driver, this);
    }


    public QuestionPage openQuestionPageById(Integer id) {
        driver.get(ConfProvider.QUESTION_LINK + id);
        return this;
    }

    // Карточка вопроса
    public QuestionCard getQuestionCard () {
        return new QuestionCard();
    }

    // Список ответов
    @Deprecated
    public Integer getResponsesListSize () {
        int count = 0;

        List<WebElement> titleElements = driver.findElements(responseSectionTitlePath);

        for (WebElement titleElement: titleElements) {
            String title = titleElement.getText();
//            System.out.println("-> Title[%s] found!".formatted(title));

            if (title.contains("Лучший ответ"))
                count += 1;
            else if (title.contains("ответ")) {
                Matcher matcher = Pattern.compile("\\d+(?= ответ)").matcher(title);
                while (matcher.find()) {
                    count += Integer.parseInt(
                            title.substring(matcher.start(), matcher.end())
                    );
                }
            }
        }

        List<WebElement> showMoreElements = driver.findElements(By.xpath("//div[contains(@class, 'lEFH5 ew4Zd')]//span"));
        if (!showMoreElements.isEmpty()) {
            String title = showMoreElements.get(0).getText();
//            System.out.println("-> But [%s] are hidden!".formatted(title));
            Pattern pattern = Pattern.compile("\\d+(?= ответ)");
            Matcher matcher = pattern.matcher(title);
            while (matcher.find()) {
                count -= Integer.parseInt(
                        title.substring(matcher.start(), matcher.end())
                );
            }
        }

//        System.out.println("Всего должно быть %d ответов".formatted(count));
        return count;
    }

    @Deprecated
    public List<String> getResponsesTextList () {
        return this.responseCardElementList
                .stream()
                .map(responseCard -> {
                    List<WebElement> paragraphs = responseCard.findElements(responseTextRelPath);
                    if (paragraphs.size() == 1)
                        return paragraphs.get(0).getText();
                    else
                        return paragraphs.get(0).getText() + " ...";
                })
                .toList();
    }

    private List<QuestionPage.ResponseCard> getResponseCards () {
        return this.responseCardElementList
                .stream()
                .map(QuestionPage.ResponseCard::new)
                .toList();
    }

    public List<String> getResponseAuthorList () {
        return getResponseCards()
                .stream()
                .map(ResponseCard::getAuthor)
                .toList();
    }

    public List<String> getResponseTextList () {
        return getResponseCards()
                .stream()
                .map(ResponseCard::getText)
                .map( textList -> {
                    if (textList.size() == 1)
                        return textList.get(0);
                    else
                        return textList.get(0) + "...";
                })
                .toList();
    }

    public String getResponseAuthor (Integer id) {
        return getResponseCards()
                .get(id)
                .getAuthor();
    }

    public List<String> getResponseText (Integer id) {
        return getResponseCards()
                .get(id)
                .getText();
    }

    public UserPage clickResponseAuthor (Integer id) {
        getResponseCards()
                .get(id)
                .clickAuthor();
        return new UserPage();
    }

    // Форма для ответа

    public ResponseForm getResponseForm () {
        return new ResponseForm();
    }


    private static class ResponseCard {
        private final WebElement we;
        private final By author = By.className("QBqbi"); //div[contains(@class, 'cxc3c')]//*[contains(@class, 'QBqbi')]
        private final By text = By.className("Xn2FM"); //div[contains(@class, 'cxc3c')]//*[contains(@class, 'Xn2FM')]


        ResponseCard (WebElement we) {
            this.we = we;
        }


        public String getAuthor () {
            return we.findElement(author).getText();
        }

        public List<String> getText () {
            return we.findElements(text)
                    .stream()
                    .map(WebElement::getText)
                    .toList();
        }

        public void clickAuthor () {
            we.findElement(author).click();
        }
    }

    public static class QuestionCard {
        // Панель дополнительных действий
        @FindBy (xpath = "//div[contains(@class, 'hXNvl')]//*[contains(@class, 'T2v9L')]")
        private WebElement additionalActionsBtn;

        // Информация о вопросе
        @FindBy (xpath = "//div[contains(@class, 'hXNvl')]//a[contains(@class, 'yh3lJ')]")
        private WebElement author;
        @FindBy (xpath = "//div[contains(@class, 'hXNvl')]//h1[contains(@class, 'z7Rgh')]")
        private WebElement title;

        @FindBy (xpath = "//div[contains(@class, 'hXNvl')]//p[contains(@class, 'Xn2FM')]")
        private List<WebElement> textList;
        @FindBy (xpath = "//div[contains(@class, 'hXNvl')]//div[contains(@class, 'aitWd PcSgH')]//p[contains(@class, 'Xn2FM')]")
        private List<WebElement> originalTextList;
        @FindBy (xpath = "//div[contains(@class, 'hXNvl')]//div[contains(@class, 'HRuZ5')]//p[contains(@class, 'Xn2FM')]")
        private List<WebElement> additionTextList;

        // Нижняя панель действий
        @FindBy (xpath = "//div[contains(@class, 'hXNvl')]//a[contains(@title, 'Ответить')]")
        private WebElement responseBtn;
        @FindBy (xpath = "//div[contains(@class, 'hXNvl')]//a[contains(@title, 'Нравится')]")
        private WebElement likeBtn;
        @FindBy (xpath = "//div[contains(@class, 'hXNvl')]//div[contains(@title, 'Нравится')]//div[contains(@class, 'bewUw')]")
        private List<WebElement> likedUsersElementList;
        @FindBy (xpath = "//div[contains(@class, 'hXNvl')]//a[contains(@title, 'исаться')]") // :D
        private WebElement subscribeStatus;
        @FindBy (xpath = "//div[contains(@class, 'hXNvl')]//a[contains(@title, 'Подписаться')]")
        private WebElement subscribeBtn;
        @FindBy (xpath = "//div[contains(@class, 'hXNvl')]//a[contains(@title, 'Отписаться')]")
        private WebElement unsubscribeBtn;


        QuestionCard () {
            PageFactory.initElements(driver, this);
        }

        // Панель дополнительных действий
        public AdditionalActionsPanel clickAdditionalActionsBtn() {
            // TODO кастыль, чтобы не думать о панельки смены темы, которая загараживает кнопку
            if (driver.findElements(By.className("kKd3U")).size() > 0) {
                driver.findElements(By.className("kKd3U")).get(0).click();
            }



            this.additionalActionsBtn.click();
            return new AdditionalActionsPanel();
        }

        // Информация о вопросе
        public String getTitle () {
            return this.title.getText();
        }
        public String getAuthor () {
            return this.author.getText();
        }
        public UserPage clickAuthor () {
            this.author.click();
            return new UserPage();
        }
        public List<String> getText () {
            return this.textList
                    .stream()
                    .map(WebElement::getText)
                    .toList();
        }
        public List<String> getOriginalText () {
            return this.originalTextList
                    .stream()
                    .map(WebElement::getText)
                    .toList();
        }
        public List<String> getAdditionText () {
            return this.additionTextList
                    .stream()
                    .map(WebElement::getText)
                    .toList();
        }

        // Нижняя панель действий
        public QuestionCard clickResponseBtn () {
            this.responseBtn.click();
            return this;
        }
        public QuestionCard clickLikeBtn () {
            this.likeBtn.click();
            return this;
        }
        public List<String> getLikedUsersList () {
            return this.likedUsersElementList
                    .stream()
                    .map(webElement ->
                            webElement.getAttribute("title"))
                    .toList();
        }
        public QuestionCard clickSubscribeBtn () {
            this.subscribeBtn.click();
            return this;
        }
        public QuestionCard clickUnsubscribeBtn () {
            this.unsubscribeBtn.click();
            return this;
        }
        public QuestionCard clickSubscribeBtnNWait () {
            this.subscribeBtn.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
            wait.until(d -> this.unsubscribeBtn.isDisplayed() );

            return this;
        }
        public QuestionCard clickUnsubscribeBtnNWait () {
            this.unsubscribeBtn.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
            wait.until(d -> this.subscribeBtn.isDisplayed() );

            return this;
        }
        public boolean isSubscribed() {
            return this.subscribeStatus
                    .getAttribute("title")
                    .equals("Отписаться");
        }


        public static class AdditionalActionsPanel {
            // Панель дополнительных действий
            @FindBy (xpath = "//div[contains(@class, 'hXNvl')]//div[contains(@class, 'T2v9L')]//a[contains(text(), 'Редактировать')]")
            private WebElement editionBtn;
            @FindBy (xpath = "//div[contains(@class, 'hXNvl')]//div[contains(@class, 'T2v9L')]//a[contains(text(), 'Дополнить')]")
            private WebElement additionBtn;
            @FindBy (xpath = "//div[contains(@class, 'hXNvl')]//div[contains(@class, 'T2v9L')]//a[contains(text(), 'Удалить')]")
            private WebElement deleteBtn;
            @FindBy (xpath = "//div[contains(@class, 'hXNvl')]//div[contains(@class, 'T2v9L')]//a[contains(text(), 'Сделать лидером')]")
            private WebElement makeLeaderBtn;


            private AdditionalActionsPanel() {
                PageFactory.initElements(driver, this);
            }


            // Панель дополнительных действий
            public EditionModalWindow clickEditionBtn () {
                this.editionBtn.click();
                return new EditionModalWindow();
            }

            public AdditionModalWindow clickAdditionBtn () {
                this.additionBtn.click();
                return new AdditionModalWindow();
            }

            public DeleteModalWindow clickDeleteBtn () {
                this.deleteBtn.click();
                return new DeleteModalWindow();
            }


            public static class EditionModalWindow {
                @FindBy (xpath = "//div[contains(@class, 'uVdWz')]//p[contains(@class, 'UVhXs')]")
                private WebElement title;
                @FindBy (xpath = "//div[contains(@class, 'uVdWz')]//textarea[contains(@name, 'question_text')]")
                private WebElement titleField;
                @FindBy (xpath = "//div[contains(@class, 'uVdWz')]//div[contains(@class, 'ProseMirror _Gg7M editor-input-element')]")
                private WebElement textField;
                @FindBy (xpath = "//div[contains(@class, 'uVdWz')]//div[contains(@title, 'Категория')]//div[contains(@class, 'lmES2 sa8gy')]")
                private WebElement categoryBtn;
                @FindBy (xpath = "//div[contains(@class, 'uVdWz')]//div[contains(@class, 'IgotB')]//span[contains(text(), '')]")
                private List<WebElement> categoryList;
                @FindBy (xpath = "//div[contains(@class, 'uVdWz')]//div[contains(@title, 'Подкатегория')]//div[contains(@class, 'lmES2 sa8gy')]")
                private WebElement subCategoryBtn;
                @FindBy (xpath = "//div[contains(@class, 'uVdWz')]//div[contains(@class, 'IgotB')]//span[contains(text(), '')]")
                private List<WebElement> subCategoryList;
                @FindBy (xpath = "//div[contains(@class, 'uVdWz')]//div[contains(text(), 'Сохранить вопрос')]")
                private WebElement submitBtn;

                EditionModalWindow () {
                    PageFactory.initElements(driver, this);
                }

                public String getTitle () {
                    return this.title.getText();
                }

                public EditionModalWindow inputTitle (String title) {

                    this.titleField.sendKeys(title);
                    return this;
                }

                public EditionModalWindow inputText (String text) {
                    this.textField.clear();
                    this.textField.sendKeys(text);
                    return this;
                }

                public void clickCategoryBtn () {
                    this.categoryBtn.click();
                }

                public void selectCategory (String categoryName) {
                    this.categoryList
                            .stream()
                            .filter(webElement -> webElement.getText().contains(categoryName))
                            .forEach(WebElement::click);
                }

                public void clickSubCategoryBtn () {
                    this.subCategoryBtn.click();
                }

                public void selectSubCategory (String subCategoryName) {
                    this.subCategoryList
                            .stream()
                            .filter(webElement -> webElement.getText().contains(subCategoryName))
                            .forEach(WebElement::click);
                }

                public void clickSubmitBtn () {
                    this.submitBtn.click();
                }
            }

            public static class AdditionModalWindow {
                @FindBy (xpath = "//div[contains(@class, 'uVdWz')]//p[contains(@class, 'UVhXs')]")
                private WebElement title;
                @FindBy (xpath = "//div[contains(@class, 'uVdWz')]//div[contains(@class, 'ProseMirror _Gg7M editor-input-element')]")
                private WebElement textField;
                @FindBy (xpath = "//div[contains(@class, 'uVdWz')]//div[contains(text(), 'Дополнить')]")
                private WebElement submitBtn;


                AdditionModalWindow () {
                    PageFactory.initElements(driver, this);
                }


                public String getTitle() {
                    return this.title.getText();
                }

                public AdditionModalWindow inputText (String text) {
                    this.textField.clear();
                    this.textField.sendKeys(text);
                    return this;
                }

                public void clickSubmitBtn () {
                    this.submitBtn.click();
                }
            }

            public static class DeleteModalWindow {
                @FindBy (xpath = "//div[contains(@class, 'uVdWz')]//p[contains(@class, 'UVhXs')]")
                private WebElement title;


                DeleteModalWindow () {
                    PageFactory.initElements(driver, this);
                }


                public String getTitle() {
                    return this.title.getText();
                }
            }
        }
    }

    public static class ResponseForm {
        @FindBy (xpath = "//div[contains(@class, 'aVlKA')]//form//div[contains(@class, 'ProseMirror _Gg7M editor-input-element')]")
        private WebElement inputTextElement;
        @FindBy (xpath = "//div[contains(@class, 'aVlKA')]//form//div[contains(@class, 'Hu3Jp')]//a")
        private WebElement submitBtn;

        ResponseForm () {
            PageFactory.initElements(driver, this);
        }

        public ResponseForm inputText (String text) {
            this.inputTextElement.sendKeys(text);
            return this;
        }

        public void clickSubmitBtn () {
            this.submitBtn.click();
        }
    }


    public QuestionPage login(String login, String domain, String password) {
        this._login(login, domain, password);
        return this;
    }

    public QuestionPage logout() {
        this._logout();
        return this;
    }
}

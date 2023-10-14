import org.apache.commons.io.input.MessageDigestCalculatingInputStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.objects.AskQuestionPage;
import page.objects.MainPage;
import page.objects.QuestionPage;
import page.objects.UserPage;
import readProperties.ConfProvider;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionCRUDTest extends BaseTest {
    final static int QUESTION_CREATION_COST = 5;
    static String title = ConfProvider.QUESTION_TITLE;
    //static MainPage mainPage;

    @BeforeAll
    public static void setUpQuestion() {
        BaseTest bt = new QuestionCRUDTest();
        bt.setUp();
        final long TIME_HASH = System.currentTimeMillis();
        //title = ConfProvider.QUESTION_TITLE + TIME_HASH;


        // <- Проверяю, есть ли вопросы, которые уже созданы, чтобы не тратить баллы ->
        MainPage mainPage = new MainPage()
                .login(ConfProvider.USER_LOGIN,
                        ConfProvider.USER_MAIL_DOMAIN,
                        ConfProvider.USER_PASSWORD);

        UserPage userPage = mainPage.goToUserPage();

        /*if (!userPage.getQuestionDateList().isEmpty()) {
            String lastDate = userPage.getQuestionDate(0);
            Integer time = Integer.parseInt(lastDate.substring(0, lastDate.indexOf(" ")));
            if (time < 30) {
                title = userPage.getQuestionTitle(0);
                return;
            }
        }*/
        // <- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ->



        AskQuestionPage askQuestionPage = mainPage
                .clickAskQuestionBtn();

        assertTrue(askQuestionPage.getActivityPoints() >= QUESTION_CREATION_COST,
                "User should have enough activity points to create a question");

        askQuestionPage
                .inputTitle(title)
                .inputText(ConfProvider.QUESTION_TEXT)
                .selectCategory(ConfProvider.QUESTION_OWN_CATEGORY)
                .selectSubCategory(ConfProvider.QUESTION_OWN_SUBCATEGORY)
                .clickSubmitBtn()
                .goToMainPage();
    }

    @Test
    @DisplayName("[TEST 21]-[QuestionCRUD]-(Pos) Observing new questions on current users page")
    public void questionCRUD_NewQuestionsShouldAppearOnAuthorPage() {
        UserPage userPage = new MainPage()
                .login(ConfProvider.USER_LOGIN,
                        ConfProvider.USER_MAIL_DOMAIN,
                        ConfProvider.USER_PASSWORD)
                .goToUserPage();

        List<String> titles = userPage.getQuestionTitleList();

        assertTrue(titles.contains(title),
                "The list of questions of the current users page should contain created question");
    }

    @Test
    @DisplayName("[TEST 22]-[QuestionCRUD]-(Pos) Editing created question")
    public void questionCRUD_NewQuestionsShouldBeEdited() {
        UserPage userPage = new MainPage()
                .login(ConfProvider.USER_LOGIN,
                        ConfProvider.USER_MAIL_DOMAIN,
                        ConfProvider.USER_PASSWORD)
                .goToUserPage();

        QuestionPage questionPage = userPage.clickQuestionTitle(userPage.getQuestionTitleList().indexOf(title));

        questionPage.getQuestionCard()
                .clickAdditionalActionsBtn()
                .clickEditionBtn()
                .inputText(ConfProvider.QUESTION_EDIT_TEXT)
                .clickSubmitBtn();

        assertEquals(questionPage.getQuestionCard().getOriginalText().get(0),
                ConfProvider.QUESTION_EDIT_TEXT);
    }

    @Test
    @DisplayName("[TEST 23]-[QuestionCRUD]-(Pos) Complete created question")
    public void questionCRUD_NewQuestionsShouldBeCompleted() {
        UserPage userPage = new MainPage()
                .login(ConfProvider.USER_LOGIN,
                        ConfProvider.USER_MAIL_DOMAIN,
                        ConfProvider.USER_PASSWORD)
                .goToUserPage();

        QuestionPage questionPage = userPage.clickQuestionTitle(userPage.getQuestionTitleList().indexOf(title));

        questionPage.getQuestionCard()
                .clickAdditionalActionsBtn()
                .clickAdditionBtn()
                .inputText(ConfProvider.QUESTION_ADD_TEXT)
                .clickSubmitBtn();

        assertEquals(questionPage.getQuestionCard().getAdditionText().get(0),
                ConfProvider.QUESTION_ADD_TEXT);
    }

    @Disabled("Deleting questions is a paid option")
    @Test
    @DisplayName("[TEST 24]-[QuestionCRUD]-(Pos) Delete created question")
    public void questionCRUD_NewQuestionsShouldBeDeleted() {

    }


}

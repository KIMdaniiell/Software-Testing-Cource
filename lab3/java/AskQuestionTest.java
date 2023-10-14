import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.objects.*;
import readProperties.ConfProvider;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class AskQuestionTest extends BaseTest{
    final int QUESTION_CREATION_COST = 5;
    @Test
    @DisplayName("[TEST 18]-[AskQuestion]-(Pos) Creating a question using correct data by authorised user")
    public void askQuestion_QuestionPageShouldContainTitleAndText() {
        // <- Prerequisites ->
        final long TIME_HASH = System.currentTimeMillis();

        MainPage mainPage = new MainPage()
                .login(ConfProvider.USER_LOGIN,
                        ConfProvider.USER_MAIL_DOMAIN,
                        ConfProvider.USER_PASSWORD);
        AskQuestionPage askQuestionPage = mainPage.clickAskQuestionBtn();

        assertTrue(askQuestionPage.getActivityPoints() >= QUESTION_CREATION_COST,
                "User should have enough activity points to create a question");
        // <- - - - - - - - ->

        assertFalse(askQuestionPage.isSubmitable(),
                "Form should not be submittable until user fills title field");

        askQuestionPage.inputTitle(ConfProvider.QUESTION_TITLE + TIME_HASH)
                .inputText(ConfProvider.QUESTION_TEXT)
                .selectCategory(ConfProvider.QUESTION_OWN_CATEGORY)
                .selectSubCategory(ConfProvider.QUESTION_OWN_SUBCATEGORY);

        assertTrue(askQuestionPage.isSubmitable(),
                "Form should become submittable after user fills form fields");

        QuestionPage questionPage = askQuestionPage.clickSubmitBtn();

        assertEquals(questionPage.getQuestionCard().getTitle(), ConfProvider.QUESTION_TITLE + TIME_HASH,
                "User should be redirected to created question page.");
    }

    @Test
    @DisplayName("[TEST 19]-[AskQuestion]-(Neg) Creating a question using incorrect data by authorised user")
    public void askQuestion_SubmitShouldNotBeAvailableUntilFieldDataIsIncorrect() {
        // <- Prerequisites ->
        MainPage mainPage = new MainPage()
                .login(ConfProvider.USER_LOGIN,
                        ConfProvider.USER_MAIL_DOMAIN,
                        ConfProvider.USER_PASSWORD);
        AskQuestionPage askQuestionPage = mainPage.clickAskQuestionBtn();

        assertTrue(askQuestionPage.getActivityPoints() >= QUESTION_CREATION_COST,
                "User should have enough activity points to create a question.");
        // <- - - - - - - - ->

        assertFalse(askQuestionPage.isSubmitable(),
                "Form should not be submittable until user fills title field.");

        askQuestionPage.inputTitle(ConfProvider.QUESTION_TITLE_SPACES);

        assertFalse(askQuestionPage.isSubmitable(),
                "Form should not be submittable until user fills title field correctly.");

        askQuestionPage.inputTitle(ConfProvider.QUESTION_TITLE_TABS);

        assertFalse(askQuestionPage.isSubmitable(),
                "Form should not be submittable until user fills title field correctly.");

        askQuestionPage.inputTitle(ConfProvider.QUESTION_TITLE_NEWLINE);

        assertFalse(askQuestionPage.isSubmitable(),
                "Form should not be submittable until user fills title field correctly.");

        askQuestionPage.inputTitle(ConfProvider.QUESTION_TITLE);

        assertFalse(askQuestionPage.isSubmitable(),
                "Form should be submittable after user fills title field correctly.");
    }

    @Test
    @DisplayName("[TEST 20]-[AskQuestion]-(Neg) Creating a question by guest user")
    public void askQuestion_QuestShouldNotBeAbleToAskQuestionsUnlessLogIn() {
        // <- Prerequisites ->
        MainPage mainPage = new MainPage();
        // <- - - - - - - - ->

        AskQuestionPage askQuestionPage = mainPage.clickAskQuestionBtn();

        askQuestionPage.inputTitle(ConfProvider.QUESTION_TITLE)
                .inputText(ConfProvider.QUESTION_TEXT)
                .selectCategory(ConfProvider.QUESTION_OWN_CATEGORY)
                .selectSubCategory(ConfProvider.QUESTION_OWN_SUBCATEGORY);

        QuestionPage questionPage = askQuestionPage.clickSubmitBtn();

        assertTrue(askQuestionPage.isLoginModalWindowVisible(),
                "LoginModalWindowVisible should pop up after guest try to submit the form.");
    }

    @Test
    @DisplayName("[TEST 21]-[AskQuestion]-(Pos) Observing new questions on current users page")
    public void askQuestion_NewQuestionsShouldAppearOnAuthorPage() {
        // <- Prerequisites ->
        final long TIME_HASH = System.currentTimeMillis();

        AskQuestionPage askQuestionPage = new MainPage()
                .login(ConfProvider.USER_LOGIN,
                        ConfProvider.USER_MAIL_DOMAIN,
                        ConfProvider.USER_PASSWORD)
                .clickAskQuestionBtn();

        assertTrue(askQuestionPage.getActivityPoints() >= QUESTION_CREATION_COST,
                "User should have enough activity points to create a question");

        MainPage mainPage = askQuestionPage
                .inputTitle(ConfProvider.QUESTION_TITLE + TIME_HASH)
                .inputText(ConfProvider.QUESTION_TEXT)
                .selectCategory(ConfProvider.QUESTION_OWN_CATEGORY)
                .selectSubCategory(ConfProvider.QUESTION_OWN_SUBCATEGORY)
                .clickSubmitBtn()
                .goToMainPage();
        // <- - - - - - - - ->

        UserPage userPage = mainPage.goToUserPage();

        List<String> titles = userPage.getQuestionTitleList();

        assertTrue(titles.contains(ConfProvider.QUESTION_TITLE + TIME_HASH),
                "The list of questions of the current users page should contain created question");
    }
}

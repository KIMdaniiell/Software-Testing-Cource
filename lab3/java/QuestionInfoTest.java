import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import page.objects.MainPage;
import page.objects.QuestionPage;
import page.objects.SearchResultPage;
import page.objects.UserPage;
import readProperties.ConfProvider;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionInfoTest extends BaseTest{

    @Test
    @DisplayName("[TEST 09]-[QuestionInfo]-(Pos) Observing question page containing its title and full text")
    public void QuestionInfo_QuestionPageShouldContainTitleAndText() {
        // <- Prerequisites ->
        MainPage mainPage = new MainPage();
        SearchResultPage searchResultPage = mainPage
                .search(ConfProvider.QUESTION_OWN_ID.toString());
        // <- - - - - - - - ->

        QuestionPage questionPage = searchResultPage
                .clickQuestionFromList(0);

        String title = questionPage.getQuestionCard().getTitle();

        assertEquals(title, ConfProvider.QUESTION_TITLE,
                "Question page should contain its title");

        String text = questionPage.getQuestionCard().getText().get(0);

        assertEquals(text, ConfProvider.QUESTION_TEXT,
                "Question page should contain its full text");
    }

    //TODO Сделать источником данных ConfProvider
    @ParameterizedTest
    @ValueSource(ints = {234676937, 234218684, 234676414})
    @DisplayName("[TEST 10]-[QuestionInfo]-(Pos) Observing question page containing its responses")
//    @RepeatedTest(value = 3)
    public void QuestionInfo_QuestionPageShouldContainResponses(int questionId) {
        // <- Prerequisites ->
        MainPage mainPage = new MainPage();
        SearchResultPage searchResultPage = mainPage
                .search(questionId + "");
        // <- - - - - - - - ->

        QuestionPage questionPage = searchResultPage
                .clickQuestionFromList(0);

        Integer size = questionPage.getResponsesListSize();
        List<String> responses = questionPage.getResponsesTextList();

        assertEquals(size, responses.size(),
                """
                        Comment section title should contain number equal to response list size.
                        \tTitle: %d
                        \tList size: %d""".formatted(size, responses.size()));
    }

    @Test
    @DisplayName("[TEST 11]-[QuestionInfo]-(Pos) Observing questions author and his user page")
    public void QuestionInfo_QuestionPageShouldContainItsAuthor() {
        // <- Prerequisites ->
        MainPage mainPage = new MainPage();
        // <- - - - - - - - ->

        String cardAuthor = mainPage.getQuestionAuthor(0);
        QuestionPage questionPage = mainPage.clickQuestion(0);
        String pageAuthor = questionPage.getQuestionCard().getAuthor();

        assertEquals(cardAuthor, pageAuthor,
                """
                    Question page should contain the same author name as question card.
                    \tCard author: %s
                    \tPage author: %s""".formatted(cardAuthor, pageAuthor));

        UserPage userPage = questionPage.getQuestionCard().clickAuthor();
        String userName = userPage.getUserName();

        assertEquals(cardAuthor, userName,
            """
                User page should contain the same author name as question page.
                \tPage author: %s
                \tUser page name: %s""".formatted(pageAuthor, userName));
    }

    @Disabled("1. The limit of reactions is reached." +
            "2. The list of liked users and the indication does not update correctly.")
    @Test
    @DisplayName("[TEST 12]-[QuestionInfo]-(Pos) <Like>'ing question by authorised user")
    public void QuestionInfo_UserShouldBeDisplayedInLikedList() {
        // <- Prerequisites ->
        QuestionPage questionPage = new QuestionPage()
                .openQuestionPageById(ConfProvider.QUESTION_OUTER_ID)
                .login(ConfProvider.USER_LOGIN,
                        ConfProvider.USER_MAIL_DOMAIN,
                        ConfProvider.USER_PASSWORD);
        // <- - - - - - - - ->

        questionPage.getQuestionCard()
                .clickLikeBtn();

        List<String> users = questionPage
                .getQuestionCard()
                .getLikedUsersList();

        assertTrue(users
                .stream()
                .anyMatch(u -> u.contains(
                        ConfProvider.USER_FULL_NAME)
                ), "Users full name should be in liked users list."
        );

        // <- Postconditions ->
        questionPage.getQuestionCard().clickLikeBtn();
        // <- - - - - - - - ->
    }

    @Test
    @DisplayName("[TEST 13]-[QuestionInfo]-(Neg) Leaving <Like> reaction by guest user")
    public void QuestionInfo_GuestShouldNotBeAbleToLike() {
        // <- Prerequisites ->
        QuestionPage questionPage = new QuestionPage()
                .openQuestionPageById(ConfProvider.QUESTION_OUTER_ID);
        // <- - - - - - - - ->

        assertFalse(questionPage.isLoginModalWindowVisible(),
                "LoginModalWindowVisible should not be visible until guest leaves the <Like> reaction.");

        questionPage.getQuestionCard()
                .clickLikeBtn();

        assertTrue(questionPage.isLoginModalWindowVisible(),
                "LoginModalWindowVisible should pop up after guest leaves the <Like> reaction.");
    }

    @Disabled ("Под вопросом нельзя оставлять больше одного комментария. " +
            "А учитывая, что баллы активности ограничены, " +
            "придется каждый раз создавать новые аккаунты " +
            "(для авторизации во время тестирования, и тот на чьи вопросы нужно отвечать). ")
    @Test
    @DisplayName("[TEST 14]-[QuestionInfo]-(Pos) Leaving response by authorised user")
    public void QuestionInfo_UsersResponseShouldBeDisplayed() {
        // <- Prerequisites ->
        QuestionPage questionPage = new QuestionPage()
                .openQuestionPageById(ConfProvider.QUESTION_OUTER_ID)
                .login(ConfProvider.USER_LOGIN,
                        ConfProvider.USER_MAIL_DOMAIN,
                        ConfProvider.USER_PASSWORD);
        // <- - - - - - - - ->

        questionPage.getResponseForm()
                .inputText(ConfProvider.RESPONSE_TEXT)
                .clickSubmitBtn();

        assertTrue(questionPage.getResponseTextList()
                        .contains(ConfProvider.RESPONSE_TEXT),
                "Response should be visible in the list.");

        int index = questionPage.getResponseTextList().indexOf(ConfProvider.RESPONSE_TEXT);

        assertEquals(questionPage.getResponseAuthor(index),
                        ConfProvider.USER_FULL_NAME,
                "Response should belong to current user.");

        // <- Postconditions ->
        // Deleting responses is a paid option
        // <- - - - - - - - ->
    }

    @Test
    @DisplayName("[TEST 15]-[QuestionInfo]-(Neg) Leaving response by guest user")
    public void QuestionInfo_GuestShouldNotBeAbleToResponse() {
        // <- Prerequisites ->
        QuestionPage questionPage = new QuestionPage()
                .openQuestionPageById(ConfProvider.QUESTION_OUTER_ID);
        // <- - - - - - - - ->

        assertFalse(questionPage.isLoginModalWindowVisible(),
                "LoginModalWindowVisible should not be visible until guest clicks submit button.");

        questionPage.getResponseForm()
                .inputText(ConfProvider.RESPONSE_TEXT)
                .clickSubmitBtn();

        assertTrue(questionPage.isLoginModalWindowVisible(),
                "LoginModalWindowVisible should be visible after guest clicks submit button.");
    }

    @Test
    @DisplayName("[TEST 16]-[QuestionInfo]-(Pos) Subscribing question by authorised user")
    public void QuestionInfo_UnsubscribeBtnShouldBeDisplayed() {
        // <- Prerequisites ->
        QuestionPage questionPage = new QuestionPage()
                .openQuestionPageById(ConfProvider.QUESTION_OUTER_ID)
                .login(ConfProvider.USER_LOGIN,
                        ConfProvider.USER_MAIL_DOMAIN,
                        ConfProvider.USER_PASSWORD);
        // <- - - - - - - - ->

        assertFalse(questionPage.getQuestionCard().isSubscribed(),
                "User should be unsubscribed before test.");

        questionPage.getQuestionCard().clickSubscribeBtnNWait();

        assertTrue(questionPage.getQuestionCard().isSubscribed(),
                "User became subscribed");

        questionPage.getQuestionCard().clickUnsubscribeBtnNWait();

        assertFalse(questionPage.getQuestionCard().isSubscribed(),
                "User unsubscribed");
    }

    @Test
    @DisplayName("[TEST 17]-[QuestionInfo]-(Neg) Subscribing question by guest user")
    public void QuestionInfo_GuestShouldNotBeAbleToSubscribeQuestion() {
        // <- Prerequisites ->
        QuestionPage questionPage = new QuestionPage()
                .openQuestionPageById(ConfProvider.QUESTION_OUTER_ID);
        // <- - - - - - - - ->

        assertFalse(questionPage.getQuestionCard().isSubscribed(),
                "User should NOT be subscribed before clicking subscribe button.");
        assertFalse(questionPage.isLoginModalWindowVisible(),
                "LoginModalWindowVisible should not be visible until guest clicks submit button.");

        questionPage.getQuestionCard().clickSubscribeBtn();

        assertFalse(questionPage.getQuestionCard().isSubscribed(),
                "Guest user should be still unsubscribed after clicking subscribe button.");
        assertTrue(questionPage.isLoginModalWindowVisible(),
                "LoginModalWindowVisible should be visible after guest clicks submit button.");
    }
}

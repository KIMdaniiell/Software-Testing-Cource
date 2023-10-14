import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.objects.LoginPage;
import page.objects.MainPage;
import readProperties.ConfProvider;

import static org.junit.jupiter.api.Assertions.*;


public class AuthorizationTest extends BaseTest {

    @Test
    @DisplayName("[TEST 01]-[Authorization]-(Pos) Logging in using correct data")
    public void login_UserMailAddressShouldBeDisplayed() {
        // <- Prerequisites ->
        MainPage mainPage = new MainPage();
        // <- - - - - - - - ->

        mainPage.login(ConfProvider.USER_LOGIN,
                ConfProvider.USER_MAIL_DOMAIN,
                ConfProvider.USER_PASSWORD);

        String address = mainPage.getUserMailAddress();

        assertEquals(address, ConfProvider.USER_LOGIN + ConfProvider.USER_MAIL_DOMAIN,
                "The mail address should be sequence of users login and main domain.");

        assertTrue(mainPage.isLoggedIn(),
                "After authorization, the login button should not be displayed.");
    }

    @Test
    @DisplayName("[TEST 02]-[Authorization]-(Neg) Logging in using incorrect data")
    public void login_UserShouldSeeErrorMessage() {
        // <- Prerequisites ->
        MainPage mainPage = new MainPage();
        // <- - - - - - - - ->

        LoginPage loginPage = mainPage
                .loginIncorrect(ConfProvider.USER_LOGIN,
                        ConfProvider.USER_MAIL_DOMAIN,
                        ConfProvider.USER_INCORRECT_PASSWORD);

        // Optional, not necessary
        String address = loginPage.getUserMailAddress();
        assertEquals(address, ConfProvider.USER_LOGIN + ConfProvider.USER_MAIL_DOMAIN,
                "The mail address should be sequence of users login and main domain.");

        String errorMessage = loginPage.getErrorMessage();
        assertEquals(errorMessage, "Неверный пароль, попробуйте ещё раз",
                "Since the password is incorrect, an error message should appear.");
    }

    @Test
    @DisplayName("[TEST 03]-[Deauthorization]-(Pos) Logging out from the system of an authorized user")
    public void logout_LogInButtonShouldBeAvailable() {
        // <- Prerequisites ->
        MainPage mainPage = new MainPage()
                .login(ConfProvider.USER_LOGIN,
                        ConfProvider.USER_MAIL_DOMAIN,
                        ConfProvider.USER_PASSWORD);
        // <- - - - - - - - ->

        mainPage.logout();

        assertFalse(mainPage.isLoggedIn(),
                "After deauthorization, the login button should be displayed.");
    }
}

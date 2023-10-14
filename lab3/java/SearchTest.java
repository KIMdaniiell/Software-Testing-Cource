import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.objects.MainPage;
import page.objects.SearchResultPage;
import readProperties.ConfProvider;


import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SearchTest extends BaseTest{

    @Test
    @DisplayName("[TEST 04]-[SearchTest]-(Pos) Observing list of questions")
    public void search_QuestionListShouldBeNonEmpty() {
        // <- Prerequisites ->
        MainPage mainPage = new MainPage();
        // <- - - - - - - - ->

        assertFalse(mainPage
                .clickOpenedQuestionBtn()
                .getQuestionTitleList()
                .isEmpty(),
                "List of OPEN questions should be displayed. It must contain at least 1 question.");

        assertFalse(mainPage
                .clickVotedQuestionBtn()
                .getQuestionTitleList()
                .isEmpty(),
                "List of VOTABLE questions should be displayed. It must contain at least 1 question.");

        assertFalse(mainPage
                .clickBestQuestionBtn()
                .getQuestionTitleList()
                .isEmpty(),
                "List of the BEST questions should be displayed. It must contain at least 1 question.");
    }

    @Test
    @DisplayName("[TEST 05]-[Search]-(Pos) Filtering list of question via search request")
    public void search_QuestionsShouldContainRequest() {
        // <- Prerequisites ->
        MainPage mainPage = new MainPage();
        // <- - - - - - - - ->

        List<String> list = mainPage
                .search(ConfProvider.QUERY_TEXT)
                .getQuestionTitles();

        list.forEach(
            title -> assertTrue(
                    title
                            .toLowerCase()
                            .contains(ConfProvider.QUERY_TEXT_TRIMMED),
                    "Title: [%s] should contain request: [%s]".formatted(title, ConfProvider.QUERY_TEXT))
        );
    }

    @Test
    @DisplayName("[TEST 06]-[Search]-(Pos) Filtering list of question via categories from SHORT list")
    public void search_QuestionsShouldContainCategoryFromShortList() {
        // <- Prerequisites ->
        MainPage mainPage = new MainPage();
        // <- - - - - - - - ->

        SearchResultPage searchPage = mainPage
                .selectCategoryFromShortList(ConfProvider.QUERY_CATEGORY);

        assertEquals(searchPage.getCategoryTitle(), ConfProvider.QUERY_CATEGORY,
                "Searching category should be displayed on the result page");
    }

    @Test
    @DisplayName("[TEST 07]-[Search]-(Pos) Filtering list of question via categories from FULL list")
    public void search_QuestionsShouldContainCategoryFromFullList() {
        // <- Prerequisites ->
        MainPage mainPage = new MainPage();
        // <- - - - - - - - ->

        SearchResultPage searchPage = mainPage
                .goToCategoryPage()
                .clickCategory(ConfProvider.QUERY_CATEGORY_FROM_FULL_LIST);

        assertEquals(searchPage.getCategoryTitle(), ConfProvider.QUERY_CATEGORY_FROM_FULL_LIST,
                "Searching category should be displayed on the result page");
    }
}

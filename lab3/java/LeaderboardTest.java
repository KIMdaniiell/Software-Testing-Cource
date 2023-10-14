import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import page.objects.MainPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class LeaderboardTest extends BaseTest{

    @Test
    @DisplayName("[TEST 08]-[Leaderboard]-(Pos) Observing list of leaders sorted by activity points")
    public void leaderboard_LeadersShouldBeSortedByActivityPoints() {
        // <- Prerequisites ->
        MainPage mainPage = new MainPage();
        // <- - - - - - - - ->

        List<Integer> activityPointsList = mainPage
                .clickToLeaderPageBtn()
                .getActivityPointsList();

        for (int i = 0; i < activityPointsList.size() - 1; i++) {
            assertTrue(activityPointsList.get(i) >= activityPointsList.get(i+1),
                    "Leaders should be sorted. ( %d should be greater or equal %d )"
                            .formatted(activityPointsList.get(i), activityPointsList.get(i+1)));
        }
    }
}

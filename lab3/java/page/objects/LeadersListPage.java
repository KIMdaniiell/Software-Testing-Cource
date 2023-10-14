package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

public class LeadersListPage extends BasePage{
    @FindBy (xpath = "//div[contains(@class, 'KnXul')]//div[contains(@class, 'W5MT3')]/parent::div")
    private WebElement firstLeaderCard;

    @FindBy (xpath = "//div[contains(@class, 'KnXul')]//div[contains(@class, 'DaDRo')]/parent::div")
    private WebElement secondLeaderCard;

    @FindBy (xpath = "//div[contains(@class, 'KnXul')]//div[contains(@class, 'K2mLw')]/parent::div")
    private WebElement thirdLeaderCard;

    @FindBy (xpath = "//div[contains(@class, 'grgJe')]")
    private WebElement bottomList;

    private final By activityPointsRelPath = By.className("VTj4s");

    private final By lowerLeaderCardsRelPath = By.className("obbFb"); //obbFb zGntY

    private final By lowerLeaderActivityPointsRelPath = By.className("Ty_uM");


    public LeadersListPage() {
        PageFactory.initElements(driver, this);
    }


    public Integer getFirstLeaderActivityPoints() {
        return Integer.parseInt(
                this.firstLeaderCard
                        .findElement(activityPointsRelPath)
                        .getText()
                        .replaceAll(" ", ""));
    }

    public Integer getSecondLeaderActivityPoints() {
        return Integer.parseInt(this.secondLeaderCard.findElement(activityPointsRelPath).getText().replaceAll(" ", ""));
    }

    public Integer getThirdLeaderActivityPoints() {
        return Integer.parseInt(this.thirdLeaderCard.findElement(activityPointsRelPath).getText().replaceAll(" ", ""));
    }


    public List<Integer> getActivityPointsTopList() {
        return List.of( this.getFirstLeaderActivityPoints(),
                this.getSecondLeaderActivityPoints(),
                this.getThirdLeaderActivityPoints());
    }

    public List<Integer> getActivityPointsBottomList () {
        return bottomList.findElements(lowerLeaderCardsRelPath)
                .stream()
                .map(webElement -> {
                    return Integer.parseInt(webElement.findElement(lowerLeaderActivityPointsRelPath).getText().replaceAll(" ", ""));
                })
                .toList();
    }

    public List<Integer> getActivityPointsList() {
        LinkedList<Integer> list = new LinkedList<>();
        list.addAll(this.getActivityPointsTopList());
        list.addAll(this.getActivityPointsBottomList());
        return list;
    }
}

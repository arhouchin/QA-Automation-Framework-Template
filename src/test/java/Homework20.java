import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework20 extends BaseTest{

    @Test
    public void deletePlaylist() throws InterruptedException{
        WebElement deletePlaylist = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(".btn-delete-playlist")));
        String expectedPlayListDeletedMessage = "Deleted playlist \"TestPro Playlist.\"";

        provideEmail("aaron.houchin@testpro.io");
        providePassword("Arho!030");
        clickSubmit();
        openPlayList();
        clickDeletePlaylistBtn();
        //Assertion
        Assert.assertEquals(getDeletedPlaylistMsg(), expectedPlayListDeletedMessage);
        
    }

    public void clickDeletePlaylistBtn() {
        WebElement deletePlaylist = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(".btn-delete-playlist")));
        deletePlaylist.click();
    }

    public void openPlayList() {
        WebElement emptyPlaylist = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//section[@id='playlists']//li[3]")));
        emptyPlaylist.click();
    }

    public String getDeletedPlaylistMsg(){
        WebElement notificationmsg = wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.cssSelector("div.success.show")));
        return notificationmsg.getText();
    }
}

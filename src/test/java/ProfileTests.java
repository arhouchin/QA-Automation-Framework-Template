import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class ProfileTests extends BaseTest {

    @Test
    public void changeProfileName() throws InterruptedException {
        //Login
        provideEmail("aaron.houchin@testpro.io");
        providePassword("Arho!030");
        clickSubmit();
        Thread.sleep(2000);

        //Navigate To Profile Page
        clickOnAvatar();
        Thread.sleep(2000);

        //Random New Name
        String randomNewName = generateRandomName();
        Thread.sleep(2000);

        //Provide Current Password
        provideCurrentPassword("Arho!030");
        Thread.sleep(2000);

        //Provide new random name
        provideNewName(randomNewName);
        Thread.sleep(2000);

        //Click On The Save Button
        clickSave();
        Thread.sleep(2000);

        //Assertion
        WebElement actualProfileName = driver.findElement(By.cssSelector("a.view-profile>span"));
        Assert.assertEquals(actualProfileName.getText(), randomNewName);


    }

    //Helper Methods
    //Navigate To Profile Page
    public void clickOnAvatar(){
        WebElement avatarIcon = driver.findElement(By.cssSelector("img.avatar"));
        avatarIcon.click();
    }

    public String generateRandomName(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public void provideCurrentPassword(String password){
        WebElement currentPasswordField = driver.findElement(By.cssSelector("[name='current_password']"));
        currentPasswordField.clear();
        currentPasswordField.sendKeys(password);
    }

    public void provideNewName(String newName){
        WebElement profileNameField = driver.findElement(By.cssSelector("[name='name']"));
        profileNameField.clear();
        profileNameField.sendKeys(newName);
    }

    public void clickSave(){
        WebElement saveButton = driver.findElement(By.cssSelector("button.btn-submit"));
        saveButton.click();
    }

}

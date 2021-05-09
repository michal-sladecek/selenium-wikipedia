import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class WikipediTests {
    @Test
    public void testSearchFunctionality() {
        SeleniumConfig config = new SeleniumConfig();
        WebDriver driver = config.getDriver();
        driver.get("https://www.wikipedia.com");

        driver.findElement(By.id("searchInput")).sendKeys("Wikipedia");

        driver.findElement(By.xpath("//i[text()=\"Search\"]")).click();

        assertEquals("https://en.wikipedia.org/wiki/Wikipedia", driver.getCurrentUrl());
    }

    @Test
    public void testLoginWithInvalidEmailPasswordDoesNotWork(){
        //Negative test case - entering wrong username and password does not login user.
        SeleniumConfig config = new SeleniumConfig();
        WebDriver driver = config.getDriver();
        driver.get("https://en.wikipedia.org/w/index.php?title=Special:UserLogin&returnto=Help:Introduction+to+Wikipedia");

        driver.findElement(By.id("wpName1")).sendKeys("8g23uhf023fn2b9ubfn");

        driver.findElement(By.id("wpPassword1")).sendKeys("8g23uhf0asdf23fn2b9ubfn");

        driver.findElement(By.id("wpLoginAttempt")).click();

        assertEquals("https://en.wikipedia.org/w/index.php?title=Special:UserLogin&returnto=Help:Introduction+to+Wikipedia", driver.getCurrentUrl());
    }

    @Test
    public void testUserInfoLookup(){
        //Negative test case - entering wrong username and password does not login user.
        SeleniumConfig config = new SeleniumConfig();
        WebDriver driver = config.getDriver();
        driver.get("https://www.wikidata.org/wiki/Special:CentralAuth");

        driver.findElement(By.id("ooui-php-1")).sendKeys("wikipedia");

        driver.findElement(By.xpath("//span[text()=\"View user information\"]")).click();

        String dateRegistered = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div[4]/fieldset[1]/ul/li[2]")).getText();

        assertEquals("Registered: 15:56, 17 March 2015 (6 years ago)",dateRegistered);
    }


}

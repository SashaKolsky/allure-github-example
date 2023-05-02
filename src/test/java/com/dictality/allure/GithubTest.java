package com.dictality.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selectors.withText;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GithubTest {

    @Test
    public void searchForIssue(){
        Configuration.headless = true;
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com");
        $(".header-search-input").click();
        $(".header-search-input").sendKeys("allure-example");
        $(".header-search-input").submit();

        $(By.linkText("eroshenkoam/allure-example")).click();
        $("#issues-tab").click();

        $(withText("#81")).should(Condition.exist);
    }

    @Test
    public void searchForIssueSelenium(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--window-size=1920,1200");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().fullscreen();

        Allure.step("Navigating to https://github.com");
        driver.navigate().to("https://github.com");

        driver.findElement(By.cssSelector(".header-search-input")).click();
        driver.findElement(By.cssSelector(".header-search-input")).sendKeys("allure-example");
        driver.findElement(By.cssSelector(".header-search-input")).submit();

        driver.findElement(By.linkText("eroshenkoam/allure-example")).click();
        driver.findElement(By.cssSelector("#issues-tab")).click();

        assertTrue(driver.findElements(withText("#81")).size() > 0);

        driver.close();
    }
}

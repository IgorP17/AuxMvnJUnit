package org.selenide;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.getUserAgent;
import static com.codeborne.selenide.Selenide.open;

public class SimpleSelenoidTest {


    @ParameterizedTest(name = "{index}. Browser {1}, search string {0}")
    @MethodSource("provideData")
    public void userCanSearch(String searchString, String browser) {
        Configuration.browser = browser;
        Configuration.remote = "http://igor-virtualbox:4444/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", false
        ));
        Configuration.browserCapabilities = capabilities;

        open("https://duckduckgo.com");
        System.out.println("==Using " + Configuration.browser + "; UserAgent = " + getUserAgent());
        new GooglePage().searchFor(searchString);

        SearchResultPage results = new SearchResultPage();
        results.getResults().shouldHave(sizeGreaterThan(0));
        results.getResult(0).shouldHave(text(searchString));
    }



    private static Stream<Arguments> provideData() {
        return Stream.of(
                Arguments.of("футбол", BrowserEnum.FIREFOX.getName()),
                Arguments.of("бильярд", BrowserEnum.CHROME.getName())
        );
    }
}

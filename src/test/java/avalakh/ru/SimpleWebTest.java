package avalakh.ru;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SimpleWebTest {

    @BeforeEach
    void setup(){
        Configuration.holdBrowserOpen = true;
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser="edge";
        Configuration.browserSize = "1920x1080";
        open("https://www.google.com/");
    }

//    @ValueSource(strings = {"Dima","Vasya","Petya"}) //дает только один аргумент в тест!
    @CsvSource(value = {
            "https://ru.selenide.org, Selenide",
            "https://junit.org, JUnit 5"}) //количество разделенных запятыми (или др символом) массива = количеству параметров
//    @CsvSource(value = {
//            "https://ru.selenide.org+ Selenide",
//            "https://junit.org+ JUnit 5"},delimiterString = "+")
//    @CsvFileSource(resources = "/TestData.csv")
    @DisplayName("В поисковой выдаче Google присутствует ссылка https://ru.selenide.org для запроса 'selenide'")
    @ParameterizedTest
    @Tag("BLOCKER")
    void successfulSearchSelenideTest(String expectedLink, String searchQuery){

        $("[name=q]").setValue(searchQuery)
                .pressEnter();
        $("[id=search]").shouldHave(text(expectedLink));
    }






    @DisplayName("В поисковой выдаче картинок Google присутствует картинка")
    @Test
    @Tag("BLOCKER")
    void successfulSearchPhotoTest(){
        $("[name=q]").setValue("selenide").pressEnter();
        $$("[role='navigation'] .hdtb-mitem a").first().click();
        $("[id=search]").shouldHave(text("https://ru.selenide.org"));
    }

}

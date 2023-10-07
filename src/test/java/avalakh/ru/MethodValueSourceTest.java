package avalakh.ru;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MethodValueSourceTest {

    @BeforeEach
    void setup(){
        Configuration.holdBrowserOpen = false;
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser="edge";
        Configuration.browserSize = "1920x1080";
        open("https://sinonim.org/");
    }
    static Stream<Arguments> wordAndSynonymsTest() {
        return Stream.of(
                Arguments.of("блокада",
                        List.of("война","окружение","изоляция","отключение","осада","блокирование"
                                ,"эмбарго","информблокада","самоблокада")),
                Arguments.of("коряк",
                        List.of("коряга","упрямец","лесина","палеоазиат","рассоха","развилина"))
        );
    }

    @MethodSource
    @ParameterizedTest(name="Поиск {0} среди {1}")
    @Tag("synonyms")
    void wordAndSynonymsTest(String word, List<String> expectedSynonyms){

        $("#text").setValue(word).pressEnter();
        $$(".nach").filter(visible)
                .shouldHave(CollectionCondition.texts(expectedSynonyms));
    }
}

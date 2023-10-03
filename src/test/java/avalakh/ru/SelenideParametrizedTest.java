package avalakh.ru;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideParametrizedTest {
    static Stream<Arguments> selenideButtonsTest (){
        return Stream.of(
                Arguments.of(Language.EN, List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes")),
                Arguments.of(Language.RU,List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы"))
        );
    }

    @MethodSource

    @ParameterizedTest(name="Поиск {0} среди {1}")
    void selenideButtonsTest(Language language, List<String> expectedButtons){
        open("https://ru.selenide.org");
        $$("#languages a").find(Condition.text(language.name())).click();
        $$(".main-menu-pages a").filter(Condition.visible)
                .shouldHave(CollectionCondition.texts(expectedButtons));
    }
}

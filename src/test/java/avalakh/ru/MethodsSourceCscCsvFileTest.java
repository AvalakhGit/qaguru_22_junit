package avalakh.ru;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MethodsSourceCscCsvFileTest {
    @BeforeEach
    void setup(){
        Configuration.holdBrowserOpen = false;
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser="edge";
        Configuration.browserSize = "1920x1080";
        open("https://creditcalcul.ru/");
    }

    @CsvFileSource(resources = "/LoanData.csv",delimiterString = "+")
    @DisplayName("Рассчет кредита")
    @ParameterizedTest (name = "Проверка расчета кредита {0} под процент {1} на срок {2}")
    @Tags({@Tag("CSV"),@Tag("FILE")})

    void loanCsvFileTest(String loanValue, String loanPercent, String loanTime,String loanMonthPay, String loanBankPercent){

        $("[data-test=credit]").setValue(loanValue);
        $("[data-test=percent]").setValue(loanPercent);
        $("[data-test=time]").setValue(loanTime)
                .pressEnter();
        $$("[data-test=info-block-number]").shouldHave(exactTexts(loanMonthPay,loanBankPercent));

    }
    @CsvSource(value = {
            "10000+15+2+484,87+1 636,81",
            "20000+5+1+1 712,15+545,80"},delimiterString = "+")
            @DisplayName("Рассчет кредита")
            @ParameterizedTest (name = "Проверка расчета кредита {0} под процент {1} на срок {2}")
            @Tags({@Tag("CSV"),@Tag("Source")})
    void successfulSearchSelenideTest(String loanValue, String loanPercent, String loanTime,String loanMonthPay, String loanBankPercent){

        $("[data-test=credit]").setValue(loanValue);
        $("[data-test=percent]").setValue(loanPercent);
        $("[data-test=time]").setValue(loanTime)
                .pressEnter();
        $$("[data-test=info-block-number]").shouldHave(exactTexts(loanMonthPay,loanBankPercent));

    }



}

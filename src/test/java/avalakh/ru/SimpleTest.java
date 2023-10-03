package avalakh.ru;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;

public class SimpleTest {
private String name="Max";
    @Tags({
            @Tag("API"),
            @Tag("SMOKE")
    })
    @Test
    void firstTest(){

    }

    @RepeatedTest(2)
    @Tag("WEB")
    void secondTest(){
        Assertions.assertTrue(name.equals("Max1"),"Не равны строки!");

        System.out.println(name);
    }

    @Test
    void firstTests(){
        Assertions.assertAll(
                () -> {
                    Assertions.assertTrue(name.equals("Max1"),"Strings not equals!");
                    Assertions.assertTrue(name.equals("Max12"),"Strings not equals!");
                    Assertions.assertTrue(4<2,"4 > 2!");
                }
        );
    }
    @Test
    void exeptionTest(){
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            someMethod();
        });
    }

    private void someMethod(){
        throw new IllegalArgumentException();
    }

}

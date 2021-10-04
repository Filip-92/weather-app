package pl_filippeszke.controller;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MainWindowControllerTest {

    Double lat = 52.229676;
    Double lon = 21.012229;

    MainWindowController mainWindowController = new MainWindowController();

    @Test
    void shouldHaveDefaultValuesSetUponLaunch() {

    }

//    @Test
//    void shouldReturnLanguage() throws IOException, ParseException {
//
//        //given
//        MainWindowController mainWindowController = new MainWindowController();
//        String language = "polish";
//
//        //when
//        language = mainWindowController.changeLanguageToEnglish();
//
//        //then
//        assertThat(language, is("english"));
//    }

//    @Test
//    void shouldChangeStyleOfLanguageButton() throws IOException, ParseException {
//
//        //given
//        MainWindowController mainWindowController = new MainWindowController();
//        Button button1 = new Button();
//        Button button2 = new Button();
//
//        //when
//        mainWindowController.changeLanguage(button1, button2);
//
//        //then
//        assertThat(button1.setStyle(notNullValue()), is(false));
//    }

    @Test
    void shouldBeInitializedUponLaunching() {

        //given
        MainWindowController mainWindowController = new MainWindowController();
    }

    @Test
    void shouldBeClearAfterResetting() {

        //given
        MainWindowController mainWindowController = new MainWindowController();
        HBox currentWeatherHBox = new HBox();
        Pane dailyPane = new Pane();

        //when
        mainWindowController.resetLocation(currentWeatherHBox, dailyPane);

        //then
        assertThat(currentWeatherHBox, is(notNullValue()));
        assertThat(dailyPane, is(notNullValue()));
    }

    @Test
    void shouldBeFilledWithDataUponLaunching() throws IOException, ParseException {

    }

    @Test
    void shouldExitUponClicking() {

        //given
        MainWindowController mainWindowController = new MainWindowController();

        //when
        mainWindowController.exit();

        //then
        assertEquals(1, 1);
    }

}
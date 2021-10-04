package pl_filippeszke.model.auxiliaryMethod;

import javafx.scene.control.Label;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringMethodsTest {


    StringMethods stringMethods = new StringMethods();

    @Test
    void shouldReturnDescriptionWithCapitalizedFirstLetter() {

        //given
        String description = "deszczowo";

        //when
        description = stringMethods.capitalizeFirstLetter(description);

        //then
        assertThat(description, is("Deszczowo"));
    }

    @Test
    void shouldReturnTrueIfTheValueIsNumeric() {

        //given
        String string = "56";

        //when
        stringMethods.isNumeric(string);

        //then
        assertThat(stringMethods.isNumeric(string), is(true));
    }

    @Test
    void shouldReturnFalseIfTheValueIsString() {

        //given
        String string = "Warszawa";

        //when
        stringMethods.isNumeric(string);

        //then
        assertThat(stringMethods.isNumeric(string), is(false));
    }

    @Test
    void shouldReturnReplacedCharacters() {

        //given
        String string = "łamigłówka";

        //when
        string = stringMethods.replaceSpecialCharacters(string);

        //then
        assertThat(string, is("lamiglowka"));
    }

    @Test
    void shouldReturnTrueIfTheInputValueIsValid() {

        //given
        String city = "Warszawa";
        String country = "Polska";
        Label errorLabel = null;
        String language = null;

        //when
        boolean valid = stringMethods.validateInput(city, country, errorLabel, language);

        //then
        assertTrue(valid);
    }
}
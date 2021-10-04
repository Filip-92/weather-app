package pl_filippeszke.model.auxiliaryMethod;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class LabelDescriptionTest {

    LabelDescription labelDescription = new LabelDescription();

    @Test
    void shouldReturnDayLabelDescriptionTranslatedToPolish() {

        //given
        String language = "polish";
        String dayLabel;

        //when
        dayLabel = labelDescription.dayLabelDescription(language);

        //then
        assertThat(dayLabel, is("dzisiaj"));
    }

    @Test
    void shouldReturnDayLabelDescriptionTranslatedToEnglish() {

        //given
        String language = "english";
        String dayLabel;

        //when
        dayLabel = labelDescription.dayLabelDescription(language);

        //then
        assertThat(dayLabel, is("today"));
    }

    @Test
    void shouldReturnWindSpeedLabelDescriptionTranslatedToPolish() {

        //given
        String language = "polish";
        String windSpeedLabel;

        //when
        windSpeedLabel = labelDescription.windSpeedLabelDescription(language);

        //then
        assertThat(windSpeedLabel, is("wiatr: "));
    }

    @Test
    void shouldReturnWindSpeedLabelDescriptionTranslatedToEnglish() {

        //given
        String language = "english";
        String windSpeedLabel;

        //when
        windSpeedLabel = labelDescription.windSpeedLabelDescription(language);

        //then
        assertThat(windSpeedLabel, is("wind: "));
    }

    @Test
    void shouldReturnCloudinessLabelDescriptionTranslatedToPolish() {

        //given
        String language = "polish";
        String windSpeedLabel;

        //when
        windSpeedLabel = labelDescription.cloudinessLabelDescription(language);

        //then
        assertThat(windSpeedLabel, is("zachmurzenie: "));
    }

    @Test
    void shouldReturnCloudinessLabelDescriptionTranslatedToEnglish() {

        //given
        String language = "english";
        String windSpeedLabel;

        //when
        windSpeedLabel = labelDescription.cloudinessLabelDescription(language);

        //then
        assertThat(windSpeedLabel, is("cloudiness: "));
    }

    @Test
    void shouldReturnPressureLabelDescriptionTranslatedToPolish() {

        //given
        String language = "polish";
        String windSpeedLabel;

        //when
        windSpeedLabel = labelDescription.pressureLabelDescription(language);

        //then
        assertThat(windSpeedLabel, is("ciśnienie: "));
    }

    @Test
    void shouldReturnPressureLabelDescriptionTranslatedToEnglish() {

        //given
        String language = "english";
        String windSpeedLabel;

        //when
        windSpeedLabel = labelDescription.pressureLabelDescription(language);

        //then
        assertThat(windSpeedLabel, is("pressure: "));
    }

    @Test
    void shouldReturnHumidityLabelDescriptionTranslatedToPolish() {

        //given
        String language = "polish";
        String windSpeedLabel;

        //when
        windSpeedLabel = labelDescription.humidityLabelDescription(language);

        //then
        assertThat(windSpeedLabel, is("wilgotność: "));
    }

    @Test
    void shouldReturnHumidityLabelDescriptionTranslatedToEnglish() {

        //given
        String language = "english";
        String windSpeedLabel;

        //when
        windSpeedLabel = labelDescription.humidityLabelDescription(language);

        //then
        assertThat(windSpeedLabel, is("humidity: "));
    }

    @Test
    void shouldReturnTranslatedWeekDayName1() {

        //given
        String weekDay = "poniedziałek";
        String result;

        //when
        result = labelDescription.translateWeekDay(weekDay);

        //then
        assertThat(result, is("Monday"));
    }

    @Test
    void shouldReturnTranslatedWeekDayName2() {

        //given
        String weekDay = "wtorek";
        String result;

        //when
        result = labelDescription.translateWeekDay(weekDay);

        //then
        assertThat(result, is("Tuesday"));
    }

    @Test
    void shouldReturnTranslatedWeekDayName3() {

        //given
        String weekDay = "środa";
        String result;

        //when
        result = labelDescription.translateWeekDay(weekDay);

        //then
        assertThat(result, is("Wednesday"));
    }

    @Test
    void shouldReturnTranslatedWeekDayName4() {

        //given
        String weekDay = "czwartek";
        String result;

        //when
        result = labelDescription.translateWeekDay(weekDay);

        //then
        assertThat(result, is("Thursday"));
    }

    @Test
    void shouldReturnTranslatedWeekDayName5() {

        //given
        String weekDay = "piątek";
        String result;

        //when
        result = labelDescription.translateWeekDay(weekDay);

        //then
        assertThat(result, is("Friday"));
    }

    @Test
    void shouldReturnTranslatedWeekDayName6() {

        //given
        String weekDay = "sobota";
        String result;

        //when
        result = labelDescription.translateWeekDay(weekDay);

        //then
        assertThat(result, is("Saturday"));
    }

    @Test
    void shouldReturnTranslatedWeekDayName7() {

        //given
        String weekDay = "niedziela";
        String result;

        //when
        result = labelDescription.translateWeekDay(weekDay);

        //then
        assertThat(result, is("Sunday"));
    }
}
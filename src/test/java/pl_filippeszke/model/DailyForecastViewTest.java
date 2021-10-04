package pl_filippeszke.model;

import org.junit.jupiter.api.Test;
import pl_filippeszke.model.auxiliaryMethod.CurrentTimeAndDate;

import java.text.ParseException;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class DailyForecastViewTest {

    @Test
    void shouldReturnNameOfNextDay() {

        //given
        DailyForecastView dailyForecastView = new DailyForecastView();
        CurrentTimeAndDate currentTimeAndDate = new CurrentTimeAndDate(
                Clock.fixed(LocalDateTime.of(2021, 9, 1, 12, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.of("UTC")));
        String nextDay = null;

        //when
        LocalDateTime day = currentTimeAndDate.currentDay();
        nextDay = dailyForecastView.nextDay(1);

        //then
        assertThat(nextDay, is("Tuesday"));
    }
}
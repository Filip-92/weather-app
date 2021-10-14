package pl_filippeszke.model.auxiliaryMethod;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class CurrentTimeAndDateTest {

    @Test
    void shouldReturnCurrentDayAndDate() {

        //given
        CurrentTimeAndDate currentTimeAndDate = new CurrentTimeAndDate(
                Clock.fixed(LocalDateTime.of(2021, 9, 1, 12, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.of("UTC")));

        //when
        String date = currentTimeAndDate.currentDate("english");

        //then
        //assertThat(date, is("środa, 01 września, 2021")); // current day and date
        assertThat(date, is("Wednesday, 01 September, 2021")); // current day and date
    }

    @Test
    void shouldReturnCorrectFormatOfCurrentTime() {

        //given
        CurrentTimeAndDate currentTimeAndDate = new CurrentTimeAndDate(
                Clock.fixed(LocalDateTime.of(2021, 9, 20, 12, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.of("UTC")));

        //when
        String time = currentTimeAndDate.currentTime();

        //then
        assertThat(time, is("12:00"));
    }
}
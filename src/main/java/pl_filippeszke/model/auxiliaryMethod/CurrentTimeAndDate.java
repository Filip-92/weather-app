package pl_filippeszke.model.auxiliaryMethod;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CurrentTimeAndDate {

    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEEE" + ", " + "dd MMMM, yyyy");
    private static final DateTimeFormatter dateTimeFormatterEng = DateTimeFormatter.ofPattern("EEEE" + ", " + "dd MMMM, yyyy", Locale.ENGLISH);

    private Clock clock;

    public CurrentTimeAndDate(Clock clock) {
        this.clock = clock;
    }

    public String currentTime() {
        return LocalDateTime.now(clock).format(timeFormatter);
    }

    public String currentDate(String language) {
        if(language.equals("polish"))
        {
            return LocalDateTime.now(clock).format(dateTimeFormatter);
        }
        return LocalDateTime.now(clock).format(dateTimeFormatterEng);
    }

    public LocalDateTime currentDay() {
        return LocalDateTime.now(clock);
    }
}


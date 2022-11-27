package ic.doc;

import java.time.DayOfWeek;

public interface TemperatureService {
    int temperatureFor(String place, DayOfWeek dayOfWeek);
}

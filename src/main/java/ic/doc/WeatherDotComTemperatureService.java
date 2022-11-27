package ic.doc;

import com.weather.Day;
import com.weather.Forecaster;
import com.weather.Region;
import java.time.DayOfWeek;

// Adaptor, implements interface
public class WeatherDotComTemperatureService implements TemperatureService {
    @Override
    public int temperatureFor(String place, DayOfWeek dayOfWeek) {
        return new Forecaster().forecastFor(Region.valueOf(place.toUpperCase()),
                                        Day.valueOf(dayOfWeek.name().toUpperCase())).temperature();
    }
}

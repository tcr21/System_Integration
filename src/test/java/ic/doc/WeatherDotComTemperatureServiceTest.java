package ic.doc;

import java.time.DayOfWeek;
import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WeatherDotComTemperatureServiceTest {

    @Test
    public void canRetrieveTemperatureData(){

        TemperatureService weatherDotCom = new WeatherDotComTemperatureService();

        int temperature = weatherDotCom.temperatureFor("London", DayOfWeek.FRIDAY);

        assertThat(temperature, is(greaterThan(-20)));
    }
}

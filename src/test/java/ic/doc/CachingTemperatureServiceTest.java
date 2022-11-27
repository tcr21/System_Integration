package ic.doc;

import java.time.DayOfWeek;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CachingTemperatureServiceTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    TemperatureService downstream = context.mock((TemperatureService.class));

    @Test
    public void preventsMultipleRequestsDownstreamForSameParameters() {
        TemperatureService tempService = new CachingTemperatureService(downstream, 1);
        context.checking(new Expectations(){{
            exactly(1).of(downstream).temperatureFor("London", DayOfWeek.MONDAY); will(returnValue(11));
        }});

        assertThat(tempService.temperatureFor("London", DayOfWeek.MONDAY), is(11));
        assertThat(tempService.temperatureFor("London", DayOfWeek.MONDAY), is(11));
    }

    @Test
    public void evictsEldestMembersWhenCacheReachesCapacity() {
        TemperatureService tempService = new CachingTemperatureService(downstream, 1);

        context.checking(new Expectations(){{
            exactly(2).of(downstream).temperatureFor("London", DayOfWeek.MONDAY); will(returnValue(11));
            exactly(1).of(downstream).temperatureFor("London", DayOfWeek.TUESDAY); will(returnValue(11));
        }});

        tempService.temperatureFor("London", DayOfWeek.MONDAY);
        tempService.temperatureFor("London", DayOfWeek.TUESDAY);
        tempService.temperatureFor("London", DayOfWeek.MONDAY);
    }



}

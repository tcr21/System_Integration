package ic.doc;

import java.time.DayOfWeek;
import java.util.LinkedHashMap;
import java.util.Map;

// Added service, implements interface
public class CachingTemperatureService implements TemperatureService {

    private final TemperatureService downstream; // pass adaptor of type interface into constructor
    private final Map<Pair<String, DayOfWeek>, Integer> cache;

   /* public CachingTemperatureService(TemperatureService downstream) {
        this(downstream, 1000);
    }*/

    public CachingTemperatureService(TemperatureService downstream, final int capacity) {
        this.downstream = downstream;
        cache = new LinkedHashMap<Pair<String, DayOfWeek>, Integer>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }

    @Override
    public int temperatureFor(String place, DayOfWeek dayOfWeek) {
        if (!cache.containsKey(new Pair<>(place, dayOfWeek))){
            cache.put(new Pair<>(place, dayOfWeek), downstream.temperatureFor(place, dayOfWeek));
        }
        return cache.get(new Pair<>(place, dayOfWeek));
    }
}


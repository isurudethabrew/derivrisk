package pricing;

import java.time.*;
import java.util.*;


public class UTCOffset {

    //This will be used to get the Exchange's offset (using Market)
    //This will be used for the valuation date ( region config's default region)    - not market related
    //And so it is being used outside of Market - and therefore should not be an inner class


    private final LocalDateTime localDateTime;
    private final ZonedDateTime toZonedDateTime;
    private final Map<ZoneId, Double> zoneToOffset = new HashMap<>();

    public UTCOffset() {
        this(LocalDateTime.now());
    }

    UTCOffset(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        toZonedDateTime = localDateTime.atZone(ZoneId.of("UTC"));
    }

    double utcOffsetInHours(ZoneId zoneId) {
        return utcOffset(zoneId) * 24;
    }

    double utcOffset(ZoneId zoneId) {
        return zoneToOffset.computeIfAbsent(zoneId, k -> utcOffsetFunction(zoneId));
    }

    //return uffset in day units
    private double utcOffsetFunction(ZoneId zoneId) {
        ZonedDateTime fromZonedDateTime = localDateTime.atZone(zoneId);
        long minutes =  Duration.between(fromZonedDateTime, toZonedDateTime).toMinutes();
        return (double) minutes / 60 / 24;
    }

}

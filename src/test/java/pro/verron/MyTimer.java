package pro.verron;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

public class MyTimer implements AutoCloseable{
    private final Instant start;
    private final Clock clock;
    private Instant end;

    MyTimer(){
        clock = Clock.systemUTC();
        start = clock.instant();
    }

    @Override
    public void close() {
        end = clock.instant();
        System.out.println(getDuration());
    }

    public Duration getDuration() {
        return Duration.between(start, end);
    }
}

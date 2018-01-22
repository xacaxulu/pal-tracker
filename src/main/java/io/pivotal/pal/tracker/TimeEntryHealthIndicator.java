package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class TimeEntryHealthIndicator implements HealthIndicator {

    public static final int MAX_TIME_ENTRIES = 5;
    private JdbcTimeEntryRepository repository;

    public TimeEntryHealthIndicator(JdbcTimeEntryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Health health() {
        int numberOfTimeEntries = repository.list().size();

        if (numberOfTimeEntries < MAX_TIME_ENTRIES) {
            return Health.up().build();
        } else {
            return Health.down().build();
        }
    }
}

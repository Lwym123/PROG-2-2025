import java.time.LocalDateTime;

// Log class to record system events (bike rented, trip started, trip ended)
public class ERyderLog {
    private final String logId;
    private final String eventDescription;
    private final LocalDateTime timestamp;

    public ERyderLog(String logId, String eventDescription) {
        this.logId = logId;
        this.eventDescription = eventDescription;
        this.timestamp = LocalDateTime.now();
    }

    // Getter methods
    public String getLogId() {
        return logId;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return logId + " - " + eventDescription + " - " + timestamp;
    }
}
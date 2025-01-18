package Logging_Framework_design;

public class LogMessage {
     private final String message;
     private final long timestamp;
     private final LogLevel level;
    public LogMessage(LogLevel level,String message) {
        this.level=level;
        this.message=message;
        this.timestamp=System.currentTimeMillis();
    }

    public String getMessage() {
        return message;
    }

    public LogLevel getLevel() {
        return level;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString(){
        return "[ "+level+" ] "+timestamp+"--"+message;
    }
}

package Logging_Framework_design.logappender;

import Logging_Framework_design.LogMessage;

public interface LogAppender {
     void append(LogMessage message);
}

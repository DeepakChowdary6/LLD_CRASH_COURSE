package Logging_Framework_design.logappender;

import Logging_Framework_design.LogMessage;

public class ConsoleAppender implements LogAppender{
    @Override
    public void append(LogMessage logMessage){
        System.out.println(logMessage);
    }
}

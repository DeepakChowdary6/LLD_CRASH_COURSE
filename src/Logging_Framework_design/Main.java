package Logging_Framework_design;

import Logging_Framework_design.logappender.ConsoleAppender;
import Logging_Framework_design.logappender.FileAppender;

public class Main {
    public static void main(String[] args) {
        Logger logger=Logger.getInstance();

        LoggerConfig config1=new LoggerConfig(LogLevel.WARNING, new ConsoleAppender());
        logger.setConfig(config1);
        System.out.println("for the console appender");
        //if we set loglevel to warning in config , only messages above= the warning will log
        logger.debug("This is a debug message");// this message will not log
        logger.info("this is a error message");// this meesage will not log
        logger.warning("this is a warning message");
        logger.error("this is a error message");
        logger.fatal("this is a fatal message");
        System.out.println("for the File appender");
        LoggerConfig config2=new LoggerConfig(LogLevel.ERROR,new FileAppender("sample_file.txt"));
        logger.setConfig(config2);
        logger.debug("This is a debug message");// this message will not log
        logger.info("this is a error message");// this meesage will not log
        logger.warning("this is a warning message");// this meesage will not log
        logger.error("this is a error message");
        logger.fatal("this is a fatal message");
    }


}

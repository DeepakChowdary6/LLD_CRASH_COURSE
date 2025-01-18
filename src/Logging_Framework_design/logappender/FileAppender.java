package Logging_Framework_design.logappender;

import Logging_Framework_design.LogMessage;

import java.io.FileWriter;

public class FileAppender implements LogAppender{
    private final String filePath;

    public FileAppender(String filePath) {
        this.filePath = "src/Logging_Framework_design/logappender/" + filePath;;
    }

    @Override
    public  void append(LogMessage logmessage){

        try (FileWriter writer=new FileWriter(filePath,true)){
            writer.write(logmessage.toString()+"\n");
          //  System.out.println(logmessage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

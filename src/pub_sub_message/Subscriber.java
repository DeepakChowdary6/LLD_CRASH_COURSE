package pub_sub_message;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class  Subscriber implements Isubscriber{

    private final UUID userid;
    private final String userName;

    private List<String> messages;
    Subscriber(String userName){
        this.userName=userName;
        userid=UUID.randomUUID();
        messages=new ArrayList<>();
    }

    @Override
    public void onMessage(String topic,String message){
        messages.add(message);
        System.out.println("Message '"+ message+"' recieved from the "+topic+" to the user "+userName);
    }



}

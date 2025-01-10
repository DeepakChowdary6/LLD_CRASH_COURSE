package pub_sub_message;

public class Publisher {

    private final PubSub pubSub;

    public Publisher(PubSub pubSub) {
        this.pubSub = pubSub;
    }


    public void publish(String topicName,String payload){
        Message message=new Message(topicName,payload);
        pubSub.publish(message);
    }

}

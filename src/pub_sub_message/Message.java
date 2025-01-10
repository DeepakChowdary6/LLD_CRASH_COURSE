package pub_sub_message;

public class Message {
    private final String topicName;
    private final String message;

    public Message(String topic, String message) {
        this.topicName = topic;
        this.message = message;
    }

    public String getTopicName() {
        return topicName;
    }


    public String getMessage() {
        return message;
    }
}

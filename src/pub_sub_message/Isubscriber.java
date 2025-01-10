package pub_sub_message;

public interface Isubscriber {
    public void onMessage(String topic,String message);
}

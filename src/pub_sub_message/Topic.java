package pub_sub_message;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Topic {

    private final String topicName;
    private final UUID id;
    private List<Isubscriber>subscribers;
    private final Lock lock=new ReentrantLock();
    //thread can acquire the lock it already holds without blocking itself.
    //This is useful in recursive methods or nested synchronized blocks.
//    ensures only one thread can hold the lock at a time. Other threads trying to acquire the lock must wait until it is released

    Topic(String topicName){
        this.topicName=topicName;
        this.id=UUID.randomUUID();
        this.subscribers=new ArrayList<>();
    }

    public List<Isubscriber> getSubscribers() {
        lock.lock();
        try{
        return new ArrayList<>(subscribers);}
        finally {
            lock.unlock();
        }

    }

    public String getTopicName() {
        return topicName;
    }

    public Lock getLock() {
        return lock;
    }

    public void addSubscriber(Isubscriber isubscriber){
        lock.lock();
        try{
        this.subscribers.add(isubscriber);}
        finally {

            lock.unlock();
        }
    }
}

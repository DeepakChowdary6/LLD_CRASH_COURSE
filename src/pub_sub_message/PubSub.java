package pub_sub_message;

import java.util.Map;
import java.util.concurrent.*;

public class PubSub {

    // if we need to have order within the topic use this blocking queue in the topic itself
    //we need a another map to store the lock for each topic so that
    //private final Map<String, Semaphore> topicLocks = new ConcurrentHashMap<>();
    public BlockingQueue<Message> globalQueue;
    public ExecutorService executorService;
    public Map<String,Topic>topics;

   public PubSub(Integer workerNodes){
        this.executorService= Executors.newFixedThreadPool(workerNodes);
        globalQueue=new LinkedBlockingQueue<>();
        topics=new ConcurrentHashMap<>();
        startProcessing(workerNodes);
    }

    public void createTopic(String topicName){
// topicLocks.putIfAbsent(topicName, new Semaphore(1));
//            Unlike ReentrantLock, which allows only one thread to access the critical section,
//           a semaphore allows up to a fixed number of threads (based on the number of permits).
        topics.putIfAbsent(topicName,new Topic(topicName));
    }
    public void publish(Message message){
       String topic=message.getTopicName();
       if(!topics.containsKey(topic)){
           throw new IllegalArgumentException("Topic Does not exist with this name : "+message.getTopicName());
       }

       globalQueue.offer(message);
    }

    public void subscribe(String topicName,Isubscriber Isubscriber){

       if(topics.containsKey(topicName)){
           Topic topic=topics.get(topicName);
           topic.addSubscriber(Isubscriber);
       }else  throw new IllegalArgumentException("Topic Does not exist with this name : "+topicName);


    }

   public void startProcessing(int workerNodes){


           for (int i = 0; i < workerNodes; i++) {

               executorService.submit(()->{
                   while (true) {
                       try {
                           Message message = globalQueue.take();
                           processMessage(message);
                       }catch (Exception e){
                           System.out.println(e.getMessage());
                           break;
                       }

                   }
               });

       }
   }

   public void processMessage(Message message){
       String topicName= message.getTopicName();;
       String payload=message.getMessage();


//if there is order withing the topic
//       Semaphore semaphore = topicLocks.get(topicName);
//       if (semaphore == null) {
//           return;
//       }
//
//       // Acquire lock for the topic to ensure sequential processing
//       try {
//           semaphore.acquire();
           Topic topic = topics.get(topicName);
           if (topic != null) {
               for (Isubscriber isubscriber : topic.getSubscribers()) {
                  CompletableFuture.runAsync(() -> isubscriber.onMessage(topicName, payload));
               }
           }
//       } catch (InterruptedException e) {
//           Thread.currentThread().interrupt();
//       } finally {
//           semaphore.release();
//       }

   }


}

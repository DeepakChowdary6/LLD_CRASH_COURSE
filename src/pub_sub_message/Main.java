package pub_sub_message;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {




        PubSub pubSub=new PubSub(4);

        pubSub.createTopic("topic1");
        pubSub.createTopic("topic2");

        pubSub.subscribe("topic1",new Subscriber("Ramesh"));
        pubSub.subscribe("topic1",new Subscriber("Vardhan"));
        pubSub.subscribe("topic2",new Subscriber("Sai"));
        pubSub.subscribe("topic2",new Subscriber("Venkat"));
        pubSub.subscribe("topic2",new Subscriber("Kishore"));
        Publisher publisher1=new Publisher(pubSub);
        Publisher publisher2=new Publisher(pubSub);

//        Thread t1=new Thread(()->{
//            publisher1.publish("topic1","Hi group 1 how are you");
//
//        });
//
//        Thread t2=new Thread(()->{
//            publisher2.publish("topic2","It seems group 2 is busy");
//            publisher2.publish("topic1","Hi you should work hard man");
//
//        });
//
//        t1.start();
//        t2.start();

        Scanner sc=new Scanner(System.in);
        String topic;
        String message;
        while (true){

            System.out.println("Enter topic name and message");
            topic= sc.next();
            sc.nextLine();
            message=sc.nextLine();
            publisher1.publish(topic,message);

        }




    }
}

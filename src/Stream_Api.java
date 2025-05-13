import java.util.*;
import java.util.stream.*;

public class Stream_Api {

    public static void main(String[] args) {
        List<Integer>list=new ArrayList<>(Arrays.asList(1,3,4,5,2));
        List<List<Integer>> nestedNumbers = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4, 5));
        List<String> names = Arrays.asList("Alice dude", "Venkat Bob", "Ramesh Charlie");
      //  IntSummaryStatistics statistics=list.stream().mapToInt(Integer::intValue).summaryStatistics();
        //List<Integer> ans=list.stream().skip(2).limit(2).collect(Collectors.toList()); // limit and skip
      //  List<String>ans=names.stream().peek(System.out::println).collect(Collectors.toList());// performs action without changing the streaam

       List<String>ans=names.stream().flatMap(s->Arrays.stream(s.split(" "))).collect(Collectors.toList());// flatmap  streams to single stream
      //  List<Integer>ans=nestedNumbers.stream().flatMap(List::stream).collect(Collectors.toList());// flat map the stream ,
        //  flatMap takes input and return type should be stream as output , further it combines all the streams to single stream
      //  String ans=names.stream().findFirst().orElse(""); // find the first element in the stream
     // boolean ans=list.stream().noneMatch(n->n<0); // find none of element matches the predicate
      //  boolean ans=list.stream().allMatch(n->n>0); // find all match the predicate i.e positive
      //   List<String>ans=names.stream().map(s->s.split(" ")[0]).collect(Collectors.toList());//list first names

//Optional<Integer> ans=list.stream().findAny(); //returns any element from the stream

       // long ans=list.stream().mapToInt(Integer::intValue).count(); //.count() returns long always

        //List<Integer>ans=list.stream().sorted().collect(Collectors.toList());  // sort the list
      //  List<String>ans=names.stream().map(n->n.toUpperCase()).collect(Collectors.toList()); // to upper case
        // int ans=list.stream().mapToInt(Integer::intValue).max().orElse(-1);//max value with primitive data type
        //mapToInt() is required when you need to perform operations that specifically work on primitive int values, such as sum, average, max, min, etc.
       // Optional<Integer> ans=list.stream().max(Integer::compare);      //find maximum , optional is used as list can be null
        // List<Integer>ans=list.stream().filter(n->n%2==0).collect(Collectors.toList());  //filter even numbers
        //List<Integer>ans=list.stream().map(n->n*n).collect(Collectors.toList()); //find square
        // int ans=list.stream().filter(n->n%2==0).reduce(0,Integer::sum); // sum the even
        //double ans=list.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0); //find average

        System.out.println(ans);

    }

}

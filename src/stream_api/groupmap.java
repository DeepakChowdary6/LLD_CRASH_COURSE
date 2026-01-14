package stream_api;
import java.util.*;
import java.util.stream.Collectors;

public class groupmap {

    public static void main(String[] args) {
        List<String>list=Arrays.asList("Alice","Bob","Carie","Denver","Erin","Bob");
        //using group----------------------------------------------------------------------------------
        //groupingBy   by default it gives (key,List<Values>)                      //function identity for grouping criteria

        Map<Character,List<String>>mp=list.stream().collect(Collectors.groupingBy(name->name.charAt(0)));
     System.out.println(mp); // without set
    String str="aathhtbttnchwicill";
   
String reversed = str.chars()
        .mapToObj(c -> String.valueOf((char) c))
        .reduce("", (acc, ch) -> ch + acc);
      //Collectors.joining() combines stream<String> elements into a single String
        //
    System.out.println("reverse of string str " + reversed);
        //frequency of characters in a string using toMap and groupingBy 
  //.toMap(keyMapper,valueMapper,mergeFunction,mapSupplier)
        //  Map<Character,Long>mp=str.chars().mapToObj(c->(char)c).collect(Collectors.toMap(c->c,c->1L,(a,b)->a+b));
    //.groupingBy(classifier,mapFactory,downstreamCollector)
        //str.chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(c->c,Collectors.counting()));
        //.counting() , .joining() , .toList() ,  .mapping() . toSet() etc.. are called downstramCollector
        
    
        Map<Character,Set<String>>st=list.stream().collect(Collectors.groupingBy(name->name.charAt(0),Collectors.toSet()));
        System.out.println(st);// with set
        List<Person> people = List.of(
                new Person("Alice", 30, "New York"),
                new Person("Bob", 25, "London"),
                new Person("Charlie", 22, "New York"),
                new Person("David", 28, "London"),
                new Person("Eve", 35, "Paris")
        );
        //Instead of collecting full Person objects, mapping() transforms each to a name before collecting.
        Map<String, List<String>> namesByCityusingGroup = people.stream()
                .collect(Collectors.groupingBy(
                        p -> p.city,
                        Collectors.mapping(p -> p.name, Collectors.toList())//Collectors.mapping() = transform each item during a grouped (or other collected) stream.
                ));
          System.out.println(namesByCityusingGroup);

//using map -----------------------------------------------------------
        Map<String, Integer> nameToAge = people.stream()
                .collect(Collectors.toMap(p -> p.name, p -> p.age));
        System.out.println(nameToAge);

        Map<String, List<String>> namesByCityusingMap = people.stream()
                .collect(Collectors.toMap(
                        person -> person.city,                                 // key = city
                        person -> new ArrayList<>(List.of(person.name)),       // value = list with one name
                        (list1, list2) -> {                                     // merge function takes values and combine
                            list1.addAll(list2);
                            return list1;
                        }
                ));
        System.out.println(namesByCityusingMap);

        // first non repeating character in string ----------------------------------------------

        String input = "swiss";  // Input string

        // Step 1: Convert string to a stream of characters
        // - input.chars() returns an IntStream of Unicode values
        // - mapToObj(c -> (char) c) converts int to Character object
        //   so we can use object-based collectors like groupingBy
        Map<Character, Long> characterCountMap = input.chars()
                .mapToObj(c -> (char) c)  // Convert IntStream to Stream<Character>
                .collect(Collectors.groupingBy(
                        c -> c,                        // Group by the character itself
                        () -> new LinkedHashMap<>(),  // Use LinkedHashMap to maintain input order
                        Collectors.counting()         // Count how many times each character appears
                ));

        // Step 2: Find the first character with count == 1
        // - entrySet() gives us key-value pairs from the map
        // - We filter to find those with value = 1
        // - Then we map to the key (the character) and take the first one
        Optional<Character> firstNonRepeatedChar = characterCountMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(1L)) // Check for non-repeated
                .map(entry -> entry.getKey())                 // Extract the character
                .findFirst();                                 // Get the first match

        // Step 3: Print the result
        if (firstNonRepeatedChar.isPresent()) {
            System.out.println("First non-repeated character: " + firstNonRepeatedChar.get());
        } else {
            System.out.println("No non-repeated character found.");
        }

    }
}

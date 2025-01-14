package LRU_Cache;

public class Main {
    public static void main(String[] args) {
           LRUCache<String,Integer> cache=new LRUCache<>(5);
           cache.put("Saif",2);
           cache.put("Rehman",5);
        System.out.println(cache.get("Saif"));
           System.out.println(cache.get("Rehman"));
        cache.put("Saif",3);
        cache.put("Rehman",6);
        System.out.println(cache.get("Saif"));
        System.out.println(cache.get("Rehman"));
    }
}

package LRU_Cache;

public class Main {
    public static void main(String[] args) {
           LRUCache<String,Integer> cache=new LRUCache<>(5);
           cache.put("Saif",2);
           cache.put("Rao",5);
        System.out.println(cache.get("Saif"));
           System.out.println(cache.get("Rao"));
        cache.put("Saif",3);
        cache.put("Rao",7);
        System.out.println(cache.get("Saif"));
        System.out.println(cache.get("Rao"));
    }
}

package custom_hash_Map;

public class CustomHashMap<K, V> {

    // Entry class representing a single key-value pair
    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next; // Pointer to the next Entry for collision handling

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private static final int INITIAL_CAPACITY = 2; // Default bucket size
    private static final float LOAD_FACTOR=0.75f;
    private Entry<K, V>[] buckets; // Array of buckets
    private int size; // Number of key-value pairs
    private float threshold;
    // Constructor
    @SuppressWarnings("unchecked")
    public CustomHashMap() {
        buckets = new Entry[INITIAL_CAPACITY];
        size = 0;
        threshold=(int)INITIAL_CAPACITY*LOAD_FACTOR;
    }

    // Hash function to compute the index
    private int hash(K key) {
        return key == null ? 0 : Math.abs(key.hashCode() % buckets.length);
    }

    public void resize(){
       int newSize=buckets.length*2;
       Entry<K,V>[] newBuckets=new Entry[newSize];
        for(Entry<K,V> bucket:buckets){
            while (bucket!=null){
                Entry<K,V>next=bucket.next;
                int newIndex=hash(bucket.key);
                bucket.next=newBuckets[newIndex];
                newBuckets[newIndex]=bucket;
                bucket=next;
            }
        }
       buckets=newBuckets;
        threshold=(int)newSize*LOAD_FACTOR;
    }

    // Add or update a key-value pair
    public void put(K key, V value) {
        if(size>=threshold){
            resize();
        }

        int bucketIndex = hash(key);
        Entry<K, V> current = buckets[bucketIndex];

        // Traverse the chain to find the key
        while (current != null) {
            if (key == current.key || (key != null && key.equals(current.key))) {
                current.value = value; // Update value if key exists
                return;
            }
            current = current.next;
        }

        // Add new entry at the head of the chain
        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = buckets[bucketIndex];
        buckets[bucketIndex] = newEntry;
        size++;
    }

    // Retrieve the value for a given key
    public V get(K key) {
        int bucketIndex = hash(key);
        Entry<K, V> current = buckets[bucketIndex];

        while (current != null) {
            if (key == current.key || (key != null && key.equals(current.key))) {
                return current.value;
            }
            current = current.next;
        }

        return null; // Key not found
    }

    // Remove a key-value pair
    public V remove(K key) {
        int bucketIndex = hash(key);
        Entry<K, V> current = buckets[bucketIndex];
        Entry<K, V> prev = null;

        while (current != null) {
            if (key == current.key || (key != null && key.equals(current.key))) {
                if (prev == null) {
                    buckets[bucketIndex] = current.next; // Remove head of chain
                } else {
                    prev.next = current.next; // Remove current node
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }

        return null; // Key not found
    }

    // Get the size of the map
    public int size() {
        return size;
    }

    // Test the custom_hash_Map.CustomHashMap
    public static void main(String[] args) {
        CustomHashMap<String, String> map = new CustomHashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        System.out.println("Size: " + map.size());
        System.out.println("Get key1: " + map.get("key1")); // Output: value1
        System.out.println("Get key2: " + map.get("key2")); // Output: value2

        map.put("key2", "newValue2");
        System.out.println("Get updated key2: " + map.get("key2")); // Output: newValue2

        map.remove("key1");
        System.out.println("Get key1 after removal: " + map.get("key1")); // Output: null
        System.out.println("Size: " + map.size()); // Output: 2
    }
}


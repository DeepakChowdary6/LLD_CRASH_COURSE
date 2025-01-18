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

    private static final int INITIAL_CAPACITY = 16; // Default bucket size
    private Entry<K, V>[] buckets; // Array of buckets
    private int size; // Number of key-value pairs

    // Constructor
    @SuppressWarnings("unchecked")
    public CustomHashMap() {
        buckets = new Entry[INITIAL_CAPACITY];
        size = 0;
    }

    // Hash function to compute the index
    private int hash(K key) {
        return key == null ? 0 : Math.abs(key.hashCode() % buckets.length);
    }

    // Add or update a key-value pair
    public void put(K key, V value) {
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

        System.out.println("Get key1: " + map.get("key1")); // Output: value1
        System.out.println("Get key2: " + map.get("key2")); // Output: value2

        map.put("key2", "newValue2");
        System.out.println("Get updated key2: " + map.get("key2")); // Output: newValue2

        map.remove("key1");
        System.out.println("Get key1 after removal: " + map.get("key1")); // Output: null
        System.out.println("Size: " + map.size()); // Output: 2
    }
}


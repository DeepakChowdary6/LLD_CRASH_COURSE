package movie_ticket_booking_system;


import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

// custom hashmap to emulate the behaviour of serializable property in database
public class KeyLockMap<K, V> {

        private final HashMap<K, V> map = new HashMap<>();
        private final ConcurrentHashMap<K, ReentrantLock> keyLocks = new ConcurrentHashMap<>();

        // Ensure each key has a unique lock
        private ReentrantLock getLock(K key) {
            return keyLocks.computeIfAbsent(key, k -> new ReentrantLock());
        }

        // Read operation with fine-grained locking
        public V get(K key) {
            ReentrantLock lock = getLock(key);
            lock.lock(); // Lock the specific key
            try {
                return map.get(key); // Perform read operation
            } finally {
                lock.unlock(); // Release lock
            }
        }

        // Write operation with fine-grained locking
        public void put(K key, V value) {
            ReentrantLock lock = getLock(key);
            lock.lock(); // Lock the specific key
            try {
                map.put(key, value); // Perform write operation
            } finally {
                lock.unlock(); // Release lock
            }
        }
}

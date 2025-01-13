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
        public V get(K key) throws Exception{
            ReentrantLock lock = getLock(key);
            //if the lock is acquired by some other thread , the current thread doesn't wait
            if (lock.tryLock()) { // Try to acquire the lock
                try {
                    return map.get(key); // Perform read operation
                } finally {
                    lock.unlock(); // Release lock
                }
            } else {


               throw new Exception("Wait for some time"); // Return null or handle as needed
            }
        }

        // Write operation with fine-grained locking
        public boolean put(K key, V value) throws Exception{
            ReentrantLock lock = getLock(key);
            //if the lock is acquired by some other thread , the current thread doesn't wait
            if (lock.tryLock()) { // Try to acquire the lock
                try {
                    map.put(key, value); // Perform write operation
                    return true; // Indicate success
                } finally {
                    lock.unlock(); // Release lock
                }
            } else {
                throw new Exception("Wait for some time"); // Return null or handle as needed

            }
        }
}

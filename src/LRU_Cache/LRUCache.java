package LRU_Cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K,V> {
    private final Integer capacity;
    private final Node<K,V> head;
    private final Node<K,V> tail;
    private final Map<K,Node<K,V>> cache;
    public LRUCache(Integer capacity){
        this.head=new Node<>();
        this.tail=new Node<>();
        this.capacity=capacity;
        this.cache=new HashMap<>();
        head.next=tail;
        tail.prev=head;
    }

    public synchronized void put(K key,V value){
        Node<K,V> node=cache.get(key);
        if(node!=null){
            node.value=value;
            moveToHead(node);

        }else{
            node=new Node<K,V>(key,value);
            cache.put(key,node);
            addToHead(node);
            if(capacity<cache.size()){
                Node<K,V>removedNode=removeTail();
                cache.remove(removedNode.key);
            }
        }
    }

    public synchronized V get(K key){
        Node<K,V>node=cache.get(key);
        if(node==null) {
            return null;
        }
            moveToHead(node);
            return node.value;
    }
    public  void addToHead(Node<K,V>node){
        //first attach the node
      node.next= head.next;
      node.prev=head;
      //remove the old attachments
      head.next.prev=node;
      head.next=node;
    }

    public void delete(Node<K,V> node){
          node.prev.next=node.next;
          node.next.prev=node.prev;
          node.next=null;
          node.prev=null;
    }

    public void moveToHead(Node<K,V> node){
          delete(node);
          addToHead(node);
    }
    public Node<K,V> removeTail(){
       Node<K,V> node=tail.prev;
        delete(tail.prev);
        return node;
    }






}

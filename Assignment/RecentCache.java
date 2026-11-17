import java.util.HashMap;
import java.util.Map;

class Node {
    int key;
    String value;
    Node prev, next;

    public Node(int key, String value) {
        this.key = key;
        this.value = value;
    }
}

public class RecentCache {
    private int capacity;
    private Map<Integer, Node> map;
    private Node head;
    private Node tail;

    public RecentCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(0, "");
        this.tail = new Node(0, "");
        head.next = tail;
        tail.prev = head;
    }

    public String get(int key) {
        if (!map.containsKey(key)) {
            return "-1";
        }
        Node node = map.get(key);
        remove(node);
        addToFront(node);
        return node.value;
    }

    public void put(int key, String page) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = page;
            remove(node);
            addToFront(node);
            System.out.println("put(" + key + "," + page + ") updated");
        } else {
            if (map.size() >= capacity) {
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);
                System.out.println("put(" + key + "," + page + ") evicted " + lru.key);
            } else {
                System.out.println("put(" + key + "," + page + ")");
            }
            Node newNode = new Node(key, page);
            map.put(key, newNode);
            addToFront(newNode);
        }
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public void printCache() {
        StringBuilder sb = new StringBuilder("Cache (most recent first): ");
        Node curr = head.next;
        boolean first = true;
        while (curr != tail) {
            if (!first) {
                sb.append(", ");
            }
            sb.append(curr.key).append("=").append(curr.value);
            first = false;
            curr = curr.next;
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        RecentCache cache = new RecentCache(2);
        cache.put(1, "Home");
        cache.put(2, "Notes");
        System.out.println("get(1) -> " + cache.get(1));
        cache.put(3, "Quiz");
        System.out.println("get(2) -> " + cache.get(2));
        cache.put(1, "Home2");
        cache.put(4, "Mail");
        System.out.println("get(3) -> " + cache.get(3));
        System.out.println("get(1) -> " + cache.get(1));
        cache.printCache();
    }
}
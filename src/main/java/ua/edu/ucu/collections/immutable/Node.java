package ua.edu.ucu.collections.immutable;

class Node {
    Object value;
    Node next;
    Node prev;

    public Node() {
        value = null;
    }

    public Node(Node node) {
        value = node.value;
        next = node.next;
        prev = node.prev;
    }

    public Node(Object value) {
        this.value = value;
    }
}
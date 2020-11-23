package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {
    private ImmutableLinkedList stack;

    public Stack() {
        stack = new ImmutableLinkedList();
    }

    void push(Object e) {
        stack = stack.addFirst(e);
    }

    Object peek() {
        return stack.getFirst();
    }

    Object pop() {
        Object item = stack.getFirst();
        stack = stack.removeFirst();
        return item;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}

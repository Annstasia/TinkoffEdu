package edu.hw9.task2.solution2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// хочется за O(1) добавлять найденные рекурсией значения
// гугл не дал библиотечных классов, которые это умеют
public class SpeedLinkedListMVP<T> {
    private final Node head = new Node();
    private Node end = head;

    public void add(T object) {
        end = end.add(object);
    }

    public void move(SpeedLinkedListMVP<T> tailList) {
        end.setNext(tailList.head.next);
        if (tailList.end != tailList.head) {
            end = tailList.end;
        }
    }

    public Iterator<T> iterator() {
        return new NodeIterator(head.next);
    }

    public List<T> toList() {
        Iterator<T> iterator = iterator();
        List<T> answer = new ArrayList<>();
        while (iterator.hasNext()) {
            answer.add(iterator.next());
        }
        return answer;
    }

    private class Node {
        T object;
        Node next = null;

        private Node() {
        }

        private Node(T object) {
            this.object = object;
        }

        public Node add(T object) {
            this.next = new Node(object);
            return this.next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private class NodeIterator implements Iterator<T> {
        Node currentNode;

        private NodeIterator(Node start) {
            this.currentNode = start;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            T answer = null;
            if (hasNext()) {
                answer = currentNode.object;
                currentNode = currentNode.next;
            }
            return answer;
        }
    }

}

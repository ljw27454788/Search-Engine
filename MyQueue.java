/**
 * CS 180 - Project 4
 * This program model queue
 *
 * @author Lu, JiaWei, lu425@purdue.edu, L14.  Zhang, JunYi, zhan2539@purdue.edu, L14
 * @version 3/25/2017
 */
public class MyQueue extends Object{
    Node head;
    Object monitor;
    int count;

    public MyQueue () {
        count = 0;
        head = null;
    }

    public void add(Object o) {
        boolean n = true;
        if (count == 0) {
            head = new Node(o);
            count++;
        } else {
            Node node = head;
            while (n) {
                if (node.getNext() == null) {
                    node.setNext(new Node(o));
                    count++;
                    n = false;
                } else {
                    node = node.getNext();
                }
            }
        }
    }

    public Node remove() {
        if (head == null) {
            return null;
        }
        if (head.getNext() == null) {
            count--;
            Node r = head;
            head = null;
            return r;
        } else {
            Node remo = head;
            head = head.getNext();
            count--;
            return remo;
        }
    }

    public Node peek() {
        return head;
    }

    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        return false;
    }

    public int size() {
        return count;
    }
}

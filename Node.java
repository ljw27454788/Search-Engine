/**
 * CS 180 - Project 4
 * This program model Node
 *
 * @author Lu, JiaWei, lu425@purdue.edu, L14.  Zhang, JunYi, zhan2539@purdue.edu, L14
 * @version 3/25/2017
 */
public class Node extends Object{
    private Object data;
    private Node next;
    private Node prev;
    public Node(Object obj) {
        this.data = obj;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrev() {
        return prev;
    }

    public Object getData() {
        return data;
    }
}

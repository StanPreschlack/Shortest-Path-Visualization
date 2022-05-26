//stack data structure used by the Converter class
public class linkedStack<AnyType> {
    //node inner class
    public class node {
        AnyType data;
        node next = null;
        node(AnyType someData) {
            this.data = someData;
        }
        //default constructor
        node() {}
    }
    //set head null on construction
    node head = null;
    //push
    public void push(AnyType data) {
        node newNode = new node(data);
        node temp = head;
        head = newNode;
        head.next = temp;
    }
    //pop
    public AnyType pop() {
        if (head != null) {
            node temp = head;
            head = head.next;
            return temp.data;
        } else {
            head = new node();
            return head.data;
        }
    }
}

package model;
public class OurLinkedList<Type> implements OurList<Type> {

  private class Node {
    private Type data; // Reference to one element
    private Node next; // null, or reference to next node

     public Node(Type element, Node nodeRef) {
      data = element;
      next = nodeRef;
    }
  }

  // front is an external reference to the first element
  private Node front;
  // n is the number of elements in the list
  int n;

  public OurLinkedList() {
    n = 0;
    front = null;
  }

  // Return the number of elements currently in the list.
  public int size() {
    return n;
  }

  // Return the value of the element stored at the given index.
  // Precondition: 0 <= getIndex < size()
  public Type get(int getIndex) {
    Node ref = front;
    for (int c = 0; c < getIndex; c++) {
      ref = ref.next;
    }
    return ref.data;
  }

  // Add element at the front of the list
  public void addFront(Type element) {
     front = new Node(element, front);
     n++;
  }
  
  public String toString() {
	  return "[]";
  }
}
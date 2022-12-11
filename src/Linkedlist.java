
public class Linkedlist<T> {

    /*Implement an algorithm to remove a loop from a Singly Linked List.*/
    static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public int size = 0;
    private Coordinate head;

    // Method to append a node to the list
    public void appendNode(Coordinate aNode) {
        if(this.head == null) {
            this.head = aNode;
            this.size++;
        } else {
            Coordinate currentNode = this.head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = aNode;
            this.size++;
        }
    }

    public boolean isContainNode(Coordinate aNode) {
        Coordinate currentNode = this.head;
        if(this.head == null) {
            return false;
        }
        while (currentNode.next != null) {
            if(currentNode.getI() == aNode.getI() && currentNode.getJ() == aNode.getJ()) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return currentNode.getI() == aNode.getI() && currentNode.getJ() == aNode.getJ();
    }

    public void deleteNode(Coordinate aNode) {
        Coordinate currentNode = this.head;

        if(currentNode.getI() == aNode.getI() && currentNode.getJ() == aNode.getJ()) {
            this.head = currentNode.next;
        } else {
            currentNode = this.head.next;
            Coordinate previousNode = head;
            while (currentNode != null) {
                if(currentNode.getI() == aNode.getI() && currentNode.getJ() == aNode.getJ()) {
                    previousNode.next = currentNode.next;
                }
                previousNode = previousNode.next;
                currentNode = currentNode.next;
            }
        }



    }

    public void traverseNode() {
        Coordinate currentNode = this.head;
        while (currentNode.next != null) {
            System.out.println(currentNode.getI() + " " + currentNode.getJ());
            currentNode = currentNode.next;
        }
        System.out.println(currentNode.getI() + " " + currentNode.getJ());

    }

   /* public static void main(String[] args) {
        Linkedlist<Integer> linkedList = new Linkedlist<>();
        linkedList.appendNode(new Coordinate(0, 0));
        linkedList.appendNode(new Coordinate(1, 0));
        linkedList.appendNode(new Coordinate(2, 0));

        *//*System.out.println(linkedList.isContainNode(new Coordinate(2, 0)));*//*

        linkedList.traverseNode();

        linkedList.deleteNode(new Coordinate(2, 0));

        linkedList.traverseNode();


    }*/
}



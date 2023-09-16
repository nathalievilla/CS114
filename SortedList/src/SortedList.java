import java.util.Iterator;
public class SortedList<E extends Comparable<? super E>> extends List<E> {

    public SortedList(){
        head = null;
    }
    public void insert (E data){
        if(head == null){
            head = new Node<E>(data);
        }else if(data.compareTo(head.data) < 0){
            Node<E> newNode = new Node<E>(data);
            newNode.next = head;
            head = newNode;
        }else{
            insertERecursive(head, data);
        }
    }

    private void insertERecursive(Node<E> curr, E data){
        if(curr.next == null){
            curr.next = new Node<E>(data);
        }else if(curr.data.compareTo(data) <= 0 && curr.next.data.compareTo(data) >= 0){
            Node<E> newNode = new Node<E>(data);
            newNode.next = curr.next;
            curr.next = newNode;
        }else{
            insertERecursive(curr.next, data);
        }
    }

    public void remove(E data){
        if(head == null){
            return;
        }
        if(head.data.equals(data)){
            head = head.next;
        }else{
            removeERecursive(head, data);
        }
        
    }

    private void removeERecursive(Node<E> curr, E data){
     if(curr.next != null){
        if(curr.next.data.equals(data)){
            curr.next = curr.next.next;
        }else{
            removeERecursive(curr.next, data);
        }
     }
    }

    public E retrieve(int index){
        return retrieveERecursive(head, index);
    }

    private E retrieveERecursive(Node <E> curr, int index){
        if(curr == null){
            return null;
        }
        if(index == 0){
            return curr.data;
        }
        else{
            return retrieveERecursive(curr.next, index - 1);
        }
    }

    public boolean search(E data){
        return searchERecursive(head, data);
    }

    private boolean searchERecursive(Node<E> curr, E data){
        if(curr != null){
            if(curr.data.equals(data)){
                return true;
            }
            return searchERecursive(curr.next, data);
        }
        return false;

    }

    public Iterator<E> iterator(){
        Iterator<E> iter = new Iterator<E>() {
            Node<E> temp = head;
            public boolean hasNext(){
                return temp != null;
            }
            public E next(){
                E data = temp.data;
                temp = temp.next;
                return data;
            }
            public void remove(){

            }
        };
        return iter;
    }
}
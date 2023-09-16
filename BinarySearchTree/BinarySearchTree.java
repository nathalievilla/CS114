import java.util.Iterator;
import java.util.Vector;

public class BinarySearchTree<E extends Comparable<? super E>> extends BinaryTree<E> {
    private Vector<E> vector;
    private Node<E> findIOP(Node<E> curr) {
        curr = curr.left;
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr;
    }
    
//non recursive
    public void insert(E data){
        root = insertRecursive(root, data);
    }
//recursive
    private Node<E> insertRecursive(Node<E> root, E data){
        if(root == null){
            return new Node<E>(data);
        }
        if(root.data.compareTo(data) > 0){
            root.left = insertRecursive(root.left, data);
        }else{
            root.right = insertRecursive(root.right, data);
        }
        return root;
    }

//non recursive
    public void remove(E data){
        removeRecursive(root, data);
    }

//recursive
private Node<E> removeRecursive(Node<E> root, E data) {
    if (root == null) {
        return root;
    }
    if (data.compareTo(root.data) < 0) {
        root.left = removeRecursive(root.left, data);
    } else if (data.compareTo(root.data) > 0) {
        root.right = removeRecursive(root.right, data);
    } else {
        if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        }
        Node<E> iopNode = findIOP(root);
        root.data = iopNode.data;
        root.left = removeRecursive(root.left, iopNode.data);
    }
    return root;
}

//non recursive
    public boolean search(E data){
        return searchRecursive(root, data);
    }

//recursive
    private boolean searchRecursive(Node<E> root, E data){
        if(root == null){
            return false;
        }
        if(root.data.compareTo(data) == 0){
            return true;
        }else if(data.compareTo(root.data) < 0){
            return searchRecursive(root.left, data);
        }else{
            return searchRecursive(root.right, data);
        }
        
    }

    public Iterator<E> iterator() {
        vector = new Vector<E>();
        traverse(root);
        return vector.iterator();
    }
    private void traverse(Node<E> curr) {
        if (curr != null) {
            traverse(curr.left);
            vector.add(curr.data);
            traverse(curr.right);
        }
    }

}
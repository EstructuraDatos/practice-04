package prac5;

/**
 *
 * @author dorian
 */
public class DoubleNode<T> implements Comparable<T>{
    /**
     * Next node of this node
     */
    private DoubleNode<T> next;
    /**
     * Previous node if this node
     */
    private DoubleNode<T> previous;
    /**
     * The element of this node
     */
    private T element;

    /**
     * Default builder
     */
    public DoubleNode() {
        next=null;
        element=null;
        previous=null;
    }

    /**
     * Builder with the element
     * @param element element of the node
     */
    public DoubleNode(T element) {
        this.next = null;
        this.element = element;
    }
    
    /**
     * Set the next node
     * @param node the new next node
     */
    public void setNext(DoubleNode<T> node){
        next=node;
    }
    
    /**
     * Get the next node
     * @return the next node
     */
    public DoubleNode<T> getNext(){
        return next;
    }

    /**
     * Get the previous node
     * @return the previous node
     */
    public DoubleNode<T> getPrevious() {
        return previous;
    }

    /**
     * Set the previous node
     * @param previous the previous node
     */
    public void setPrevious(DoubleNode<T> previous) {
        this.previous = previous;
    }
    
    /**
     * Get the element of the node
     * @return the element of the node
     */
    public T getElement(){
        return element;
    }
    
    /**
     * Set the element of the node
     * @param element the new element of the node
     */
    public void setElement(T element){
        this.element=element;
    }

    /**
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)  
     */
    @Override
    public int compareTo(T o) {
        Comparable thisElement=(Comparable)element;
        return thisElement.compareTo(o);
    }
}
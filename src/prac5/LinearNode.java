package prac5;

/**
 *
 * @author dorian
 */
public class LinearNode<T> implements Comparable<T>{
    /**
     * Next node of this node
     */
    private LinearNode<T> next;
    /**
     * The element of this node
     */
    private T element;

    /**
     * Default builder
     */
    public LinearNode() {
        next=null;
        element=null;
    }

    /**
     * Builder with the element
     * @param element element of the node
     */
    public LinearNode(T element) {
        this.next = null;
        this.element = element;
    }
    
    /**
     * Set the next node
     * @param node the new next node
     */
    public void setNext(LinearNode<T> node){
        next=node;
    }
    
    /**
     * Get the next node
     * @return the next node
     */
    public LinearNode<T> getNext(){
        return next;
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
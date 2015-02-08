package prac5;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author dorian
 */
public class CircularDoubleLinkedIterator<T> implements Iterator<T> {
    /**
     * The current node in the list
     */
    private DoubleNode<T> current;
    /**
     * The last node of the list
     */
    private DoubleNode<T> last;

    /**
     * Builder
     * @param list the last node of the list 
     */
    public CircularDoubleLinkedIterator(DoubleNode<T> list) {
        //if list is not null
        if(list!=null){
            //get the first node of the list
            DoubleNode<T> first=list.getNext();
            //create a new node, copy of the first node of the list
            DoubleNode<T> node=new DoubleNode<>(first.getElement());
            node.setNext(first.getNext());
            //set this node as the current node of the list
            this.current = node;
            //set the first node (original node) as the final node of the
            //list, further explanation in method #hasNext()
            last=first;
            
        //if no list, set everything as null
        }else{
            current=null;
            last=null;
        }
    }
    
    /**
     * 
     * @see java.util.Iterator#hasNext() 
     */
    @Override
    public boolean hasNext() {
        /**
         * The current node is the node to return when call next, so I used the
         * first node as the condition to stop after return the first node
         * one time.
         * To do that, I began the list with a copy of the first node to
         * cheat this condition. The condition will became true when after
         * iterating the list, it returns to the first (and original, stored in 
         * this.last) node.
         */
        return (current!=last);
    }

    /**
     * 
     * @see java.util.Iterator#next() 
     */
    @Override
    public T next() {
        //if has not more elements throw an exception
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        //get the element
        T target=current.getElement();
        //update the current element
        current=current.getNext();
        //return the element
        return target;
    }

    /**
     * 
     * @see java.util.Iterator#remove() 
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

package prac5;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author dorian
 */
public class CircularLinkedList<T> implements UnorderedListADT<T> {
    /**
     * Last node of the list
     */
    private LinearNode<T> rear;
    /**
     * Number of elements of the list
     */
    private int count;
    
    /**
     * 
     * @see UnorderedListADT#addToFront(java.lang.Object)
     */
    @Override
    public void addToFront(T element) {
        //create the node
        LinearNode<T> node=new LinearNode<>(element);
        
        //if is the first node of the list...
        if(count==0){
            //the new node is the las node of the list too..
            rear=node;
            rear.setNext(rear);
            
        //instead, we insert the new node
        }else{
            //conect with the last and first node of the list
            node.setNext(rear.getNext());
            rear.setNext(node);
        }
        
        //increase the count
        count++;
    }

    /**
     * 
     * @see UnorderedListADT#addToRear(java.lang.Object)
     */
    @Override
    public void addToRear(T element) {
        //I insert the new node with the same procedure of addToFront
        addToFront(element);
        //update the rear element of the node
        rear=rear.getNext();
    }

    /**
     * 
     * @see UnorderedListADT#addAfter(java.lang.Object, java.lang.Object)
     */
    @Override
    public void addAfter(T element, T target) {
        //if empty, throw an exception
        if(count==0){
            throw new EmptyCollectionException("No elements");
        }
        
        //boolean to control if the new node is added
        boolean added=false;
        
        //create the new node
        LinearNode<T> node=new LinearNode<>(element);
        //get the first node of the list
        LinearNode<T> actualNode=rear.getNext();
        //iterate over all the list
        for(int i=0; i<count && !added; i++){
            //if found the element to add after...
            if(actualNode.compareTo(target)==0){
                //set the conections
                node.setNext(actualNode.getNext());
                actualNode.setNext(node);
                //update the condition
                added=true;
                count++;
                
                //if it was the rear element, update the rear element
                if(actualNode==rear){
                    rear=node;
                }
                
            //if not, get the next element
            }else{
                actualNode=actualNode.getNext();
            }
        }
        
        //if iterator ends and not found the element, throw an exception
        if(!added){
            throw new NoSuchElementException();
        }
    }
    
    /**
     * Add to the list all elements from the list l1
     * @param l1 the list with elements to add
     */
    public void concat(CircularLinkedList<T> l1){
        //iterate over all the list l1
        for (Iterator<T> it = l1.iterator(); it.hasNext();) {
            //get the element
            T t = it.next();
            //add the element to this list
            this.addToRear(t);
        }
    }

    /**
     * 
     * @see UnorderedListADT#removeFirst() 
     */
    @Override
    public T removeFirst() {
        //if empty, throw an exception
        if(count==0){
            throw new EmptyCollectionException("No elements");
        }
        //set the first node as the rear node to cheat the method removeLast
        rear=rear.getNext();
        //call and return removeLast() (the procedure is the same and with
        //the last sentence, rear will be the same as before this method)
        return removeLast();
    }

    /**
     * 
     * @see UnorderedListADT#removeLast() 
     */
    @Override
    public T removeLast() {
        //if empty, throw an exception
        if(count==0){
            throw new EmptyCollectionException("No elements");
        }
        
        //get the rear node
        LinearNode<T> target=rear;
        //if it's the only node of the list, set rear as null
        if(count==1){
            rear=null;
            
        }else{
            LinearNode<T> front=rear.getNext();
            LinearNode<T> newRear=front;
            //iterate to get the previous node
            for(int i=0; i<count-2; i++){
                newRear=newRear.getNext();
            }
            //set the next node to front, removing the rear node
            newRear.setNext(front);
            //the new rear node
            rear=newRear;
        }
        count--;
        return target.getElement();
    }

    /**
     * 
     * @see UnorderedListADT#remove(java.lang.Object) 
     */
    @Override
    public T remove(T element) {
        //if empty, throw an exception
        if(count==0){
            throw new EmptyCollectionException("No elements");
        }
        
        //boolean to control if found a node to remove
        boolean found=false;
        //get the number of elements of the list (count will be modified)
        int numberElements=count;
        //get the first node of the list and the previous
        LinearNode<T> previousNode=rear;
        LinearNode<T> node=rear.getNext();
        
        //iterate the list
        for(int i=0; i<numberElements; i++){
            //if found the node with the element to remove...
            if(node.compareTo(element)==0){
                //remove the node
                if(node==rear){
                    //if it's the last node, call removeLast()
                    removeLast();
                }else{
                    previousNode.setNext(node.getNext());
                    count--;
                }
                //found an element
                found=true;
            }
            //get the next node updating the previous node
            previousNode=node;
            node=node.getNext();
        }
        
        //if not found after iterate the list, throw an exception
        if(!found){
            throw new NoSuchElementException();
        }else{
            return element;
        }
    }
    
    /**
     * Move the beginning of the list n positions back of the list
     * @param n the number of positions to move
     */
    public void moveBack(int n){
        //if empty, throw an exception
        if(count==0){
            throw new EmptyCollectionException("No elements");
        }
        
        //just call moveForward with inverted n
        //n%count get the remainder for subtract to count
        this.moveForward(count-(n%count));
    }
    
    /**
     * Move the beginning of the list n positions forward of the list
     * @param n the number of positions to move
     */
    public void moveForward(int n){
        //if empty, throw an exception
        if(count==0){
            throw new EmptyCollectionException("No elements");
        }
        
        //get the remainder of n/count to not iterate more than necessary
        n=n%count;
        //if has only one element or the remainder is zero, is not necessary
        //to move rear
        if(count!=1 || n!=0){
            //get the actual rear
            LinearNode<T> newRear=rear;
            //iterate n positions
            for(int i=0; i<n; i++){
                newRear=newRear.getNext();
            }
            //newRear has now the new rear of the list
            rear=newRear;
        }
    }

    /**
     * 
     * @see UnorderedListADT#first() 
     */
    @Override
    public T first() {
        //if empty, throw an exception
        if(count==0){
            throw new EmptyCollectionException("No elements");
            
        //return the element
        }else{
            return rear.getNext().getElement();
        }
    }

    /**
     * 
     * @see UnorderedListADT#last() 
     */
    @Override
    public T last() {
        //if empty, throw an exception
        if(count==0){
            throw new EmptyCollectionException("No elements");
            
        //return the element
        }else{
            return rear.getElement();
        }
    }

    /**
     * 
     * @see UnorderedListADT#contains(java.lang.Object) 
     */
    @Override
    public boolean contains(T target) {
        boolean found=false;
        //iterate over the list until the end of the list or found the element
        for (Iterator<T> it = this.iterator(); it.hasNext() && !found;) {
            //if found the element, set the condition to stop the iterator
            if(it.next().equals(target)){
                found=true;
            }
        }
        
        //return the result
        return found;
    }

    /**
     * 
     * @see UnorderedListADT#isEmpty()  
     */
    @Override
    public boolean isEmpty() {
        return count==0;
    }

    /**
     * 
     * @see UnorderedListADT#size()  
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * 
     * @see UnorderedListADT#iterator() 
     */
    @Override
    public Iterator<T> iterator() {
        return new CircularLinkedIterator<>(rear);
    }
    
    /**
     * String representation of the list, separated with spaces
     * @return a string representation of the list
     */
    @Override
    public String toString(){
        StringBuilder buffer=new StringBuilder();
        //iterate over the list
        for (Iterator<T> it = this.iterator(); it.hasNext();) {
            //get the element
            T element = it.next();
            //add the element to the string
            buffer.append(element.toString());
            //add a space to separate elements
            buffer.append(" ");
        }
        
        return buffer.toString();
    }
}

package prac5;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import prac5.CircularDoubleLinkedList;

/**
 *
 * @author dorian
 */
public class CircularLinkedListTest {
    private CircularDoubleLinkedList<String> list;
    private CircularDoubleLinkedList<String> another;
    
    @Before
    public void setUp(){
        list=new CircularDoubleLinkedList<>();
        another=new CircularDoubleLinkedList<>();
        list.addToRear("A");
        list.addToRear("B");
        list.addToRear("C");
    }
    
    @Test
    public void testAddToRear(){ 
        //añado uno a una lista rellena
        list.addToRear("D");
        assertEquals(4, list.size());
        assertTrue(list.contains("D"));
        assertEquals("D", list.last());
        //añadido correctamente
        assertEquals("A B C D ", list.toString());
        
        //pruebo con una lista vacía un solo elemento
        another.addToRear("A");
        assertEquals(1, another.size());
        assertTrue(another.contains("A"));
        //last y first deben coincidir
        assertEquals("A", another.last());
        assertEquals("A", another.first());
    }
    
    @Test
    public void testAddToFront(){ 
        //mismo procedimiento que testAddToRear pero con el método addToFront
        list.addToFront("D");
        assertEquals(4, list.size());
        list.contains("D");
        assertTrue(list.contains("D"));
        assertEquals("D", list.first());
        assertEquals("D A B C ", list.toString());
        
        another.addToFront("A");
        assertEquals(1, another.size());
        assertTrue(another.contains("A"));
        assertEquals("A", another.last());
        assertEquals("A", another.first());
    }
    
    @Test
    public void testAddAfter(){
        //pruebo a incluirlo después del primero
        list.addAfter("G", "A");
        assertEquals("A G B C ", list.toString());
        
        //entre medias...
        list.addAfter("H", "G");
        assertEquals("A G H B C ", list.toString());
        
        //el último
        list.addAfter("O", "C");
        assertEquals("A G H B C O ", list.toString());
        assertEquals("O", list.last());
    }
    
    @Test(expected=EmptyCollectionException.class)
    public void testAddAfterEmpty(){
        //intentando con una lista vacía
        another.addAfter("A", "B");
    }
    
    @Test(expected=NoSuchElementException.class)
    public void testAddAfterNoExist(){
        //con un elemento no existente
        list.addAfter("G", "J");
    }
    
    @Test
    public void testConcat(){
        //creo una lista
        another.addToRear("G");
        another.addToRear("H");
        another.addToRear("J");
        
        //concateno
        list.concat(another);
        //pruebo que está bien concatenado
        assertEquals("A B C G H J ", list.toString());
        //pruebo que no se haya modificado la lista pasada
        assertEquals("G H J ", another.toString());
    }
    
    @Test
    public void testRemoveFirst(){
        assertEquals("A", list.removeFirst());
        assertEquals(2, list.size());
        assertEquals("B", list.first());
    }
    
    @Test(expected=EmptyCollectionException.class)
    public void testRemoveFirstEmpty(){
        //intento con una lista vacía
        another.removeFirst();
    }
    
    @Test
    public void testRemoveLast(){
        list.addToRear("D");
        assertEquals("D", list.removeLast());
        assertEquals(3, list.size());
        assertEquals("C", list.last());
        
        another.addToRear("F");
        another.removeLast();
        assertEquals("", another.toString());
    }
    
    @Test(expected=EmptyCollectionException.class)
    public void testRemoveLastEmpty(){
        //intento con una lista vacía
        another.removeLast();
    }
    
    @Test
    public void testRemove(){
        assertEquals("A", list.remove("A"));
        assertEquals("B C ", list.toString());
        list.addToRear("A");
        list.addToRear("C");
        list.addToRear("C");
        list.remove("C");
        assertEquals("B A ", list.toString());
        list.addToRear("E");
        list.addToRear("R");
        list.addToRear("O");
        list.remove("E");
        assertEquals("B A R O ", list.toString());
        
        another.addToRear("A");
        another.addToFront("B");
        another.remove("A");
        assertEquals("B ", another.toString());
        another.remove("B");
        assertEquals("", another.toString());
        
    }
    
    @Test(expected=NoSuchElementException.class)
    public void testRemoveNoExist(){
        another.addToRear("A");
        another.remove("C");
    }
    
    @Test
    public void testMoveBack(){
        list.moveBack(1);
        assertEquals("C A B ", list.toString());
        list.moveBack(2);
        assertEquals("A B C ", list.toString());
        list.moveBack(6);
        assertEquals("A B C ", list.toString());
        list.moveBack(4);
        assertEquals("C A B ", list.toString());
        
        list.moveBack(0);
        assertEquals("C A B ", list.toString());
    }
    
    @Test(expected=EmptyCollectionException.class)
    public void testMoveBackEmpty(){
        another.moveBack(4);
    }
    
    @Test
    public void testMoveForward(){
        list.moveForward(1);
        assertEquals("B C A ", list.toString());
        list.moveForward(2);
        assertEquals("A B C ", list.toString());
        list.moveForward(6);
        assertEquals("A B C ", list.toString());
        list.moveForward(4);
        assertEquals("B C A ", list.toString());
        
        list.moveForward(0);
        assertEquals("B C A ", list.toString());
    }
    
    @Test(expected=EmptyCollectionException.class)
    public void testMoveForwardEmpty(){
        another.moveForward(4);
    }
    
    @Test
    public void testFirst(){
        assertEquals("A", list.first());
        another.addToRear("B");
        assertEquals("B", another.first());
    }
    
    @Test(expected=EmptyCollectionException.class)
    public void testFirstEmpty(){
        another.first();
    }
    
    @Test
    public void testLast(){
        assertEquals("C", list.last());
        another.addToRear("B");
        assertEquals("B", another.last());
    }
    
    @Test(expected=EmptyCollectionException.class)
    public void testLastEmpty(){
        another.last();
    }
    
    @Test
    public void testContains(){
        assertTrue(list.contains("A"));
        assertFalse(list.contains("J"));
        assertFalse(another.contains("E"));
    }
    
    @Test
    public void testIsEmpty(){
        assertFalse(list.isEmpty());
        assertTrue(another.isEmpty());
    }
    
    @Test
    public void testSize(){
        assertEquals(3, list.size());
        list.addToRear("A");
        list.addToRear("B");
        assertEquals(5, list.size());
        assertEquals(0, another.size());
    }
    
    @Test
    public void testToString(){
        assertEquals("A B C ", list.toString());
        assertEquals("", another.toString());
        another.addToRear("E");
        assertEquals("E ", another.toString());
        
        list.addToRear("R");
        assertEquals("A B C R ", list.toString());
    }
    
    @Test
    public void testIterator(){
        StringBuilder buffer=new StringBuilder();
        for (Iterator<String> it = list.iterator(); it.hasNext();) {
            String element = it.next();
            buffer.append(element.toString());
            buffer.append(" ");
        }
        assertEquals("A B C ", buffer.toString());
        
        buffer=new StringBuilder();
        for (Iterator<String> it = another.iterator(); it.hasNext();) {
            String element = it.next();
            buffer.append(element.toString());
            buffer.append(" ");
        }
        assertEquals("", buffer.toString());
    }
}
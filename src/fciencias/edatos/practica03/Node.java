/**
 * Clase que modela las referencias auxiliares para 
 * Stack, Queue y probablemente una lista también.
 * @author Reyes Ramos Luz María.
 * @author -----
 * @version 1.0 Octubre 2021
 * @since EDD 2022-1 Lab
 */
public class Node<T> {
    /**Node element */
    private T element;

    /**Next reference */
    private Node<T> next;

    /** Prev reference */
    private Node<T> prev;

    /**
     * Create node
     * @param element Node´s element
     */
    public Node(T element){this.element = element;}
    
    /**
     * Modifies next reference
     * @param newNext New next reference
     */
    public void setNext(Node<T> newNext){this.next = newNext; }

    /**
     * Modifies prev reference
     * @param newPrev  New prev reference
     */
    public void setPrev(Node<T> newPrev){this.prev = newPrev;}

    /**
     * Get Node´s element
     * @return element
     */
    public T getElement(){return element;}

    /**
     *  Next reference acces
     *  @return next reference
     */
    public Node<T> getNext(){return next;}

    /**
     * Get  prev node reference
     * @return prev reference
     */
    public Node<T> getPrev(){return prev;}



    
}

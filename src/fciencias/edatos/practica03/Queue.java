
/**
 * Implementación de una cola basada en referencias.
 * @author Reyes Ramos Luz María, 318211073
 * @author ----
 * @version 1.0 Octubre 2021
 * @since EDD 2022-1 Lab
 */
public class Queue<T> implements TDAQueue<T> {

    /** Primer elemento de la cola */ // cabeza de la lista
    private Node<T> first;

    /**Último elemento de la cola */ //tail de una lista
    private Node<T> last;

    /** Cantidad de elementos que almacena la cola */
    private int size;

    @Override
    public void clear(){
        first = null;
        last =null;
        size = 0;
    }

    @Override
    public T dequeue(){
        //Si la cola es vacía
        if(isEmpty())
            return null;
        Node<T> removed = first;
        first = first.getNext();
        //first.setPrev(null);
        size--;
        return removed.getElement();
    }

    @Override
    public void enqueue(T e){
        Node<T> newNode = new Node<>(e);
        //Si es el primer elemento
        if(size ==0){
            first = last = newNode;
            size++;
            return;
        }
            
        last.setNext(newNode);
        newNode.setPrev(last);
        last = newNode;
        size++;

    }

    @Override
    public boolean isEmpty(){return first == null;}

    /**
     * Cantidad de elementos encolados
     * @return la cantidad de elementos que hay
     * en la cola
     */
    public int size(){ return size;}

    @Override
    public T first(){
        if(isEmpty())
            return null;
        return first.getElement();
    }

    @Override 
    public String toString(){
        Node<T> aux = first;
        //System.out.println(aux.getElement());
        String queueData = "{ ";
        while (aux != null) {
            queueData+= aux.getElement()+", ";
            aux= aux.getNext();
        } 
        return queueData+" }";
    }



    

}

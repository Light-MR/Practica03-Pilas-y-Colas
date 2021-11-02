import java.util.EmptyStackException;

/**
 * Implementación de una pila basada en referencias.
 * Implementa interfaz TDAStack
 * @author Reyes Ramos Luz María, 318211073
 * @author --------
 * @version 1.0 Octubre 2021
 * @since EDD Lab 2022-2
 */
public class Stack<T> implements TDAStack<T> {

    /** Referencia al tope de la pila ( head ) */
    private Node<T> tope;

    /** Cantidad de lementos que hay en la pila */
    private int size; //No sé si sea util más adelante xD

    @Override
    public void clear(){
         tope = null;
         size =0;
        }

    @Override
    public boolean isEmpty(){return tope == null;}

    @Override
    public T pop() throws EmptyStackException{
        //Si la pila esta vacia
        if(isEmpty())
            throw new EmptyStackException();
        Node<T> eliminado = tope;
        tope = tope.getNext();
        size--;
        return eliminado.getElement();
    }

    @Override
    public void push(T e){
        Node<T> newNode = new Node<>(e);
        newNode.setNext(tope);
        tope = newNode;
        size++;

    
    }

    @Override
    public T top() throws EmptyStackException{
        if(isEmpty())
            throw new EmptyStackException();
        
        return tope.getElement();
    }

    /**
     * Elementos que hay en la pila
     * @return devuelve la cantidad de elementos que hay en la pila.
     */
    public int size(){return size;}

    //print stack
    @Override 
    public String toString(){
        if(isEmpty())
            return "\n\tNo se hallaron elementos.";
        
        Node<T> iterator = tope;
        String stackData =  "{ ";
        while(iterator != null){
            stackData+= iterator.getElement()+", ";
            iterator = iterator.getNext();
        }
        return stackData+ " }";
    }
    

}

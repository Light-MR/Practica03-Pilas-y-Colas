/**
 * Clase que representa una lista sumamente simple.
 * Su uso será destinado a auxiliar a Queue.
 * @author Reyes Ramos Luz María, 318211073.
 * @author ---
 * @version 2.0  Octubre 2021
 * @since EDD  2022-1 LAB
 */
public class List<T> {

    /**Cabeza de la lista */
    Node<T> head;

    /**Representa la longitud de la lista */
    int size;

    /**
     * Agrega un elemento en la primera posición de la lista. / Al inicio.
     * @param element Elemento a agregar.
     */
    public void addBeg(T element){
        Node<T> newElement = new Node<>(element);
        //Si es vacia
        if(isEmpty()){
            head = newElement;
            size++;
            return;

        }
        newElement.setNext(head);
        head = newElement;
        size++;
    }

    /**
     * Determina si la lista es vacía.
     * @return True si es vacía, false en otro caso.
     */
    public boolean isEmpty(){
        return head == null;
    }

    /**
     * Vacía la lista.
     */
    public void clear(){
        head = null;
        size =0;
    }

    /**
     * Obtiene la longitud de la lista.
     * @return tamaño de lista.
     */
    public int size(){return size;}

    
    /**
     * Determina si un elemento esta contenido en la lista.
     * @param element Elemento a buscar.
     * @return true si esta contenido, false en otro caso.
     */
    public boolean contains(T element){
        //Si es vacia
        if(isEmpty())
            return false;

        Node<T> iterador = head;
        for(int n= 0 ; n<size(); n++){
            if(element.equals(iterador.getElement()))
                return true;
            
            iterador = iterador.getNext();
           
        }
        return false;
    }



    /**
     * Obtiene el elemento de la lista dado un índice.
     * @param index Indice a buscar.
     * @return Elemento en el indice dado.
     * @throws IndexOutOfBoundsException
     */
    public T get(int index)throws IndexOutOfBoundsException{
        //Si es vacía
        if(isEmpty())
            return null;
        //Algun error con el índice.
        if(index>size()-1 || index<0)
            throw new IndexOutOfBoundsException();
          
        Node<T> iterator = head;
        for(int n=0; n<size();n++){
            if(n==index)
                return iterator.getElement();
            iterator = iterator.getNext();    
        }
        return null;
    }

    
    
    @Override
    public String toString(){
        String formato = "";

        Node<T> iterador = head;
        while(iterador != null){
            formato += iterador.getElement() + "\n";
            iterador = iterador.getNext();
        }

        return formato;
    }


    
}

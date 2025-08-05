
/******************************************************************************
 *
 * MAC0323 ALGORITMOS E ESTRUTURAS DE DADOS II
 * Aluno: Renan Ryu Kajihara
 * Numero USP:14605762
 * Tarefa: Exercício E01
 * Data: 10/03/2024
 * 
 * Os métodos da classe BagVariant foram copiadas do programa Bag.java produzido por Sedgewick e Wayne, exceto o método sort e os demais que são utilizados em sua implementação. 
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/



import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;

public class BagVariant<Item extends Comparable<Item>> implements Iterable<Item>{
    private Node<Item> first;    
    private int n;               

    
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public BagVariant() {
        first = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public boolean less(Item a, Item b) {
        if (a == null) {
            return b != null;}
        else if (b == null) {
            return false;}
        else {
            return a.compareTo(b) < 0;
        }
    }

    public void add(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public Iterator<Item> iterator()  {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }


    public Node<Item> acessa (int indice, Node b){
        if (indice < 0 || indice >= n) {
            throw new IllegalArgumentException();
        }
        Node<Item> primeiro = new Node<> (); 
        primeiro.next= b.next;
        for (int i=0; i<indice; i++){
            if (primeiro == null) {
                throw new IllegalStateException();
            }
            primeiro= primeiro.next;
        }
    
        return primeiro;
    }

    public void merge (BagVariant a, int lo, int mid, int hi, Node b){
        int i =lo;
        int j=mid+1;
        a.first= a.acessa(lo,b);
        Node<Item> low = a.acessa (i,b);
        Node<Item> med = a.acessa (j,b);
        if (less(low.item, med.item) && (low!=null)) {
            a.first = low;
            i++;}
        else {
            a.first = med;
            j++;}
        for (int k=lo+1; k<=hi; k++){
            if (i>mid && med != null) {
                a.first.next = med; 
                if (med!=null) med=med.next;
                a.first=a.first.next;
                j++;}
            else if (j>hi && low != null)
            {
                a.first.next=low.next; 
                if(low!=null) low=low.next; 
                a.first=a.first.next;
                i++;}
            else if (less(low.item, med.item)&& low != null)
            {
                a.first.next=low.next; 
                if (low!= null)low=low.next; 
                a.first=a.first.next;
                i++;}
            else if (med!= null)
            {
                a.first.next=med.next; 
                if(med!=null)med=med.next;
                a.first=a.first.next;
                j++;}
        }
        a.first= a.acessa(0,b);
        
    }



    public  void sort (BagVariant a, int lo, int hi, Node b){
        if (lo>=hi) return;
        int mid = (lo + (hi))/2;
        sort (a, lo, mid,b);
        sort (a, mid+1, hi,b);
        merge (a,lo, mid, hi,b);
    }

    public void sort (){
        Node b=new Node();
        b.next = this.first;
        sort(this,0, this.size()-1, b);
    }
}

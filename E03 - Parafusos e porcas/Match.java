/******************************************************************************
 *
 * MAC0323 ALGORITMOS E ESTRUTURAS DE DADOS II
 * Aluno: Renan Ryu Kajihara
 * Numero USP: 14605762
 * Tarefa: Exercício E03
 * Data: 27/03/2024
 * 
 * Baseado no algoritmo "3-way quicksort do livro de Sedgewick e Wayne"
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/


import pieces.Nut;
import pieces.Bolt;


public class Match { 

    public static void exchint(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
    }

    public static void exchbolt(Bolt[] array, int i, int j) {
        
        Bolt temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void exchnut(Nut[] array, int i, int j) {
        Nut temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    public static void partition(Nut[] a, int[] indices_nuts, int lo, int hi, Bolt[] b, int[] indices_bolts, int[] p){
        if (hi<lo) return;
        int lt=lo, gt=hi;
        Nut v= a[lo];

        int i =lo;
        while (i<=gt){
            int cmp= b[i].compareTo(v);
            if (cmp<0) {
                exchbolt(b,lt,i); 
                exchint(indices_bolts, lt, i); 
                lt++;
                i++;}
            else if (cmp>0) {
                exchbolt (b, i, gt); 
                exchint (indices_bolts, i, gt); 
                gt--;}
            else i++;
        }
        Bolt w= b[lt];
        lt=lo;
        gt=hi;
        i =lo;
        while (i<=gt){
            int cmp= a[i].compareTo(w);
            if (cmp<0) {exchnut(a,lt,i); 
                exchint(indices_nuts, lt, i); 
                lt++;
                i++;}
            else if (cmp>0) {
                exchnut (a, i, gt); 
                exchint (indices_nuts, i, gt); 
                gt--;}
            else i++;
        }
    
    int lt_0= lt;
    for (int c= lt; lt<=gt; lt++)   p[indices_nuts[lt]]= indices_bolts[lt];

    
    partition(a, indices_nuts,lo, lt_0-1, b, indices_bolts,p);
    partition (a, indices_nuts, gt+1, hi, b, indices_bolts, p);

    }

    public static int[] match (NutsAndBolts nab){
        int N=nab.N();
        int[] indices_bolts = new int [N];
        int[] indices_nuts= new int [N];
        Bolt[] bolts = new Bolt [N];
        Nut[] nuts = new Nut [N];
        int[] p= new int[N];
        int i = 0;
        for (int a=0; a<N; a++) {
            indices_nuts[a]=a;
            indices_bolts[a]=a; 
            bolts[a]=nab.bolts(a);
            nuts[a]=nab.nuts(a);
        }
        partition(nuts, indices_nuts, 0, N-1, bolts, indices_bolts,p);
        return p;
    }
}

/******************************************************************************
 *
 * MAC0323 ALGORITMOS E ESTRUTURAS DE DADOS II
 * Aluno: Renan Ryu Kajihara
 * Numero USP: 14605762
 * Tarefa: Exercício E04
 * Data: 11/04/2024
 * 
 * Baseado no algoritmo Heap.java de Sedgewick & Wayne.
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdOut;

public class SortLocal
{
    public static void sortLocal(Comparable[] v, int k) {
		int N= v.length;
		Comparable[] tree = new Comparable[k+1];
		for (int i=0; i<k+1;i++){
			tree[i]=v[i];
		}

		sort(tree);
		v[0] = tree[0];
		int b= 0;
		for (int a = k+1; a<N; a++){
			b++;
			tree[0]= v[a];
			invert_sink(tree, 1, k+1);
			v[b]= tree[0];
		}

		sort(tree);
		for (int a=0; a<k+1; a++){
			v[b]= tree[a];
			b++;
		}

    }

	public static void invert_sink(Comparable [] tree, int i, int n){
		while (2*i <= n){
			int j = 2*i;
			if (j< n && (!(less(tree, j, j+1)))) j++;
			if (less(tree, i, j)) break;
			exch(tree, i, j);
			i=j;
		}
	}
	public static void sort(Comparable[] tree) {
        int n = tree.length;

       
        for (int k = n/2; k >= 1; k--)
            sink(tree, k, n);

        
        int k = n;
        while (k > 1) {
            exch(tree, 1, k--);
            sink(tree, 1, k);
        }
    }

	private static void sink(Comparable[] tree, int k, int n) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(tree, j, j+1)) j++;
            if (!less(tree, k, j)) break;
            exch(tree, k, j);
            k = j;
        }
    }

	private static boolean less(Comparable[] tree, int i, int j) {
        return tree[i-1].compareTo(tree[j-1]) < 0;
    }

    private static void exch(Object[] tree, int i, int j) {
        Object swap = tree[i-1];
        tree[i-1] = tree[j-1];
        tree[j-1] = swap;
    }

    public static void show(Comparable[] v) {
	for (int i = 0; i < v.length; i++) 
	    StdOut.print(v[i] + " ");
	StdOut.println();
    }

    public static boolean isSorted(Comparable[] v) {
	for (int i = 1; i < v.length; i++) 
	    if (v[i - 1].compareTo(v[i]) > 0)
		return false;
	return true;
    }

    public static void main(String[] args)
    {
	int N = Integer.parseInt(args[0]);
	int k = Integer.parseInt(args[1]);
	long seed = Long.parseLong(args[2]);
	Integer[] v = RandomPerm.kPermInteger(N, k);
	show(v);
	sortLocal(v, k);
	StdOut.println("Sorted: ");
	show(v);
	assert isSorted(v);
    }
}

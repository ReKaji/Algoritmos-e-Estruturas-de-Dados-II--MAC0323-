/******************************************************************************
 *
 * MAC0323 ALGORITMOS E ESTRUTURAS DE DADOS II
 * Aluno: Renan Ryu Kajihara
 * Numero USP: 14605762
 * Tarefa: Exercício E02
 * Data: 15/03/2024
 * 
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class CountABC
{
    public static long countABC(String s) {
		return count (s, 0, s.length()-1);
    }

	private static long countab (String s){
		long ab = 0;
		long a = 0;
		for (int i=0; i<s.length(); i++){
			if (s.charAt(i)=='a') a++;
			if (s.charAt(i)=='b') ab += a ;
		}
		return ab;
	}

	private static long counta (String s){
		long a = 0;
		for (int i=0; i<s.length(); i++){
			if (s.charAt(i)=='a') a++;
		}
		return a;
	}

	private static long countbc (String s){
		long bc = 0;
		long c = 0;
		for (int i=s.length()-1; i>=0; i--){
			if (s.charAt(i)=='c') c++;
			if (s.charAt(i)=='b') bc += c ;
		}
		return bc;
	}

	private static long countc (String s){
		long c = 0;
		for (int i=0; i<s.length(); i++){
			if (s.charAt(i)=='c') c++;
		}
		return c;
	}

	private static long countabc (String s,int lo, int mid,int hi){
		long ab= countab(s.substring(lo, mid+1));
		long c = countc (s.substring (mid+1, hi+1));
		long a= counta (s.substring(lo, mid+1));
		long bc= countbc(s.substring(mid+1,hi+1));
		return (ab*c)+(a*bc);
	}

	private static long count(String s, int lo, int hi) {
        long abc = 0;
        if (hi <= lo) return 0;
        int mid = lo + (hi - lo) / 2;
        abc += count(s, lo, mid);
        abc += count(s, mid+1, hi);
        abc += countabc(s, lo, mid, hi);
        return abc;
    }

    public static long countABCPlain(String s) {
	int N = s.length();
	long t = 0;
	for (int i = 0; i < N; i++) 
	    for (int j = i + 1; j < N; j++) 
		for (int k = j + 1; k < N; k++)  
		    if (s.charAt(i) == 'a' && s.charAt(j) == 'b' && s.charAt(k) == 'c')
			t++;
	return t;
    }    
    
    public static void main(String[] args)
    {
	String s = StdIn.readString();
	Stopwatch sw = new Stopwatch();
	StdOut.println(s);
	StdOut.println(countABC(s));
	StdOut.println("time: " + sw.elapsedTime());
	sw = new Stopwatch();
	StdOut.println(countABCPlain(s));
	StdOut.println("time: " + sw.elapsedTime());
    }
}

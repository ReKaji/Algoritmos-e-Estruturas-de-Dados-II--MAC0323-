/******************************************************************************
 *
 * MAC0323 ALGORITMOS E ESTRUTURAS DE DADOS II
 * Aluno: Renan Ryu Kajihara
 * Numero USP: 14605762
 * Tarefa: Exercício E10
 * Data: 22/06/2024
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DijkstraSPX {
    private double[] distTo;          
    private DirectedEdge[] edgeTo;    
    private IndexMinPQ<Double> pq;
    private double[] h;
    private EdgeWeightedDigraph G;
    private EdgeWeightedDigraph G_new;
    private int a;
    private int b;

    public DijkstraSPX(GeoGraph gg, int s, int t){
        
        G = gg.ewDigraph();
        h = new double[G.V()];
    
        a=s;
        b=t;
        
        for (DirectedEdge e : G.edges()){
            if (e.to()==t) h[e.from()]= e.weight();
        }

        G_new= new EdgeWeightedDigraph(G.V());
        
        for (DirectedEdge e : G.edges()){
            DirectedEdge new_edge = new DirectedEdge(e.from(), e.to(), e.weight()-h[e.from()]+h[e.to()]);
            G_new.addEdge(new_edge);
        }
        
        distTo = new double[G_new.V()];
        edgeTo = new DirectedEdge[G_new.V()];

        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        pq = new IndexMinPQ<Double>(G_new.V());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (DirectedEdge e : G_new.adj(v))
                relax(e);
        }
        


    }
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else                pq.insert(w, distTo[w]);
        }
    }

    public double distTo(int v) {
      
        return distTo[v];
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    public boolean hasPathTo(int v) {
     
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public List<DirectedEdge> sptree() {
        List<DirectedEdge> sptree = new ArrayList<>();
        
        for (int v = 0; v < edgeTo.length; v++) {
            DirectedEdge e = edgeTo[v];
            if (e != null) {
                sptree.add(e);
            }
        }
        return sptree;
    }
}
